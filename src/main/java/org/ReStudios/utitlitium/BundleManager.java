package org.ReStudios.utitlitium;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

@SuppressWarnings({"resource", "unused"})
public class BundleManager {
    private static NFile resourcesRoot;
    private static boolean jar;

    private final Class<?> clazz;
    private ArrayList<String> files;
    private ArrayList<String> directories;

    public BundleManager() { // new BundleManager(Main.class)
        try {
            this.clazz = Class.forName(Utilitium.getCaller(1).getClassName());
        } catch (ClassNotFoundException ignored) {
            throw new RuntimeException("Unable to initialize BundleManager. Use other constructor");
        }
        init();
    }

    /**
     * Forcing a class
     * @param clazz Class
     */
    public BundleManager(Class<?> clazz) {
        this.clazz = clazz;
        init();
    }
    private void init(){
        try {
            Resources entries = getFilesAndDirectories(clazz);
            this.files = new ArrayList<>(entries.getFiles());
            this.directories = new ArrayList<>(entries.getDirectories());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get resources in directory
     * @param directory Empty or "/" for root
     * @return Resources in directory
     */
    public Resources getResources(String directory){
        directory = preparePath(directory);
        Resources resources = new Resources(clazz);
        for (String s : files) {
            if(s.equalsIgnoreCase(directory)) {
                resources.getFiles().add(s);
                return resources;
            }
        }
        for (String file : files) {
            if(file.startsWith(directory+File.separator) || directory.isEmpty()){
                resources.getFiles().add(file);
            }
        }
        for (String dir : directories) {
            if(dir.startsWith(directory)){
                resources.getDirectories().add(dir);
            }
        }
        return resources;
    }

    /**
     * Get files in directory (Not recursive. For recursion, use the getResources method)
     * @param directory Directory
     * @return List of files (and directories)
     * @throws IOException I/O Exception
     */
    public List<String> getFiles(String directory) throws IOException {
        return readDirectory(readFile(directory));
    }

    /**
     * Read the contents of the file. In the case of a folder,
     * it will list the files in that folder (new line - file)
     * @param absolutePath Path
     * @return UTF-8 content
     * @throws IOException I/O Exception
     */
    public String readFile(String absolutePath) throws IOException {
        InputStream is = fileInputStream(absolutePath);
        return DataChannel.readInputStream(is);
    }

    /**
     * Checking for the existence of a file/folder
     * @param absolutePath Path
     * @return true - if a file or folder with the same name exists
     */
    public boolean has(String absolutePath){
        return fileInputStream(absolutePath) != null;
    }

    /**
     * Open input stream
     * @param absolutePath Path
     * @return Input stream
     */
    public InputStream fileInputStream(String absolutePath){
        if(absolutePath == null || absolutePath.trim().isEmpty()) return null;
        absolutePath = absolutePath.startsWith("/") ? absolutePath : "/"+absolutePath;
        return clazz.getResourceAsStream(absolutePath);
    }

    /**
     * Extract a file or directory from inside
     *
     * @param source Path inside
     * @param destination Path to extract
     * @throws IOException I/O Exception
     */
    public void extract(String source, NFile destination, boolean capsule) throws IOException {
        if(!destination.isDirectory()) {
            throw new RuntimeException("Destination directory is not a directory");
        }
        Resources resources = getResources(source);
        Resources absolute = resources.clone().absolute(destination.getAbsolutePath()+File.separator);

        String preparedName = preparePath(source);

        for (String directory : absolute.getDirectories()) {
            if(!capsule && directory.startsWith(preparedName)){
                directory = directory.substring(preparedName.length());
            }
            if(directory.startsWith(File.separator)) directory = directory.substring(1);
            NFile d = new NFile(directory);
            createDir(d);
        }
        for (String file : resources.getFiles()) {
            String o = file;
            if(file.equals(preparedName) && !capsule){
                file = ArrayUtils.getLastItem(file.replace(File.separator,"/").split("/"));
            }
            if(file == null) continue;
            if(!capsule && file.startsWith(preparedName)){
                file = file.substring(preparedName.length());
            }
            if(file.startsWith(File.separator)) file = file.substring(1);
            o = o.replace(File.separator, "/");
            NFile dest = destination.child(file);
            if(dest.exists())continue;
            createDir(dest.getParentFile());
            OutputStream os = dest.openOutput();
            InputStream is = resources.openStream(o);
            DataChannel channel = new DataChannel(os, is);
            channel.transfer();
            channel.close();
        }
    }

    /** Utility methods **/
    private void createDir(NFile d) throws IOException {
        if(d.isDirectory()) return;
        boolean mk = d.mkdirs();
        if(!mk) {
            throw new IOException("Failed to create folder: "+d);
        }
    }
    private static boolean isJar(Class<?> clazz){
        URL resource = clazz.getResource("/");
        if(resource == null) return true;
        return resource.toString().startsWith("jar:");
    }
    private static Resources getFilesAndDirectories
            (Class<?> clazz) throws IOException {
        NFile di = resourcesRoot = new NFile(clazz.getProtectionDomain().getCodeSource().getLocation().getFile());
        jar = isJar(clazz);
        if(!jar){
            ArrayList<String> pFiles = new ArrayList<>();
            ArrayList<String> pDirectories = new ArrayList<>();
            List<NFile> files = di.allFiles(true);
            for (NFile file : files) {
                if(file.isFile()){
                    if(file.getExtension().equalsIgnoreCase(".class")){
                        continue;
                    }
                    pFiles.add(file.relative(di));
                }else{
                    if(file.getName().equalsIgnoreCase("meta-inf")){
                        continue;
                    }
                    pDirectories.add(file.relative(di));
                }
            }
            return new Resources(pFiles, pDirectories, clazz);
        }else{
            JarFile jarStream = new JarFile(di.getAbsolutePath());
            return getEntries(
                    jarStream.stream().collect(Collectors.toList()), "", jarStream, clazz
            );
        }
    }
    private static Resources getEntries(
            List<JarEntry> rootEntries, String dir, JarFile file, Class<?> clazz
    ) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        ArrayList<String> directories = new ArrayList<>();

        for (JarEntry rootEntry : rootEntries) {
            String name = ArrayUtils.getLastItem(rootEntry.getName().replace(File.separator, "/").split("/"));
            if(name == null) continue;
            name = name.trim();
            String finalName = dir.isEmpty() ? rootEntry.getName() : dir+File.separator+rootEntry.getName();
            finalName = finalName.replace("/", File.separator);
            if(!rootEntry.isDirectory()){
                if(name.toLowerCase().endsWith(".class")) continue;
                if(name.equalsIgnoreCase("MANIFEST.MF")) continue;
                files.add(finalName);
            }else{
                if(name.equalsIgnoreCase("meta-inf")) continue;
                directories.add(finalName);
                ArrayList<String> subFiles = readDirectory(rootEntry, file);
                ArrayList<JarEntry> entries = new ArrayList<>();
                for (String subFile : subFiles) {
                    entries.add(file.getJarEntry(dir+File.separator+subFile));
                }
                Resources sub = getEntries(entries, finalName, file, clazz);
                files.addAll(sub.getFiles());
                directories.addAll(sub.getDirectories());
            }
        }
        return new Resources(files, directories, clazz);
    }
    private static ArrayList<String> readDirectory(JarEntry entry, JarFile jar) throws IOException {
        InputStream is = jar.getInputStream(entry);
        String content = DataChannel.readInputStream(is);
        return readDirectory(content);
    }
    private static ArrayList<String> readDirectory(String content) {
        ArrayList<String> files = new ArrayList<>();
        for (String line : content.split("\n")) {
            line = line.replace("\r", "");
            line = line.trim();
            if(line.isEmpty()) continue;
            files.add(line);
        }
        return files;
    }
    private static String preparePath(String directory){
        directory = directory.replace("/", File.separator);
        directory = directory.startsWith(File.separator) ? directory.substring(1) : directory;
        directory = directory.endsWith(File.separator) ? directory.substring(0, directory.length()-1) : directory;
        return directory;
    }

    /**
     * Resources class
     */
    public static class Resources {
        private List<String> files;
        private List<String> directories;
        private final Class<?> clazz;

        public Resources(List<String> files, List<String> directories, Class<?> clazz) {
            this.files = files;
            this.directories = directories;
            this.clazz = clazz;
        }

        public Resources(Class<?> clazz) {
            files = new ArrayList<>();
            directories = new ArrayList<>();
            this.clazz = clazz;
        }

        public List<String> getFiles() {
            return files;
        }

        public List<String> getDirectories() {
            return directories;
        }

        @Override
        public String toString() {
            return "Resources{" + "files=" + files +
                    ", directories=" + directories +
                    '}';
        }

        public Resources absolute(String s) {
            files = files.stream().map(s1 -> s+s1).collect(Collectors.toList());
            directories = directories.stream().map(s1 -> s+s1).collect(Collectors.toList());

            return this;
        }

        @SuppressWarnings({"MethodDoesntCallSuperMethod", "CloneDoesntDeclareCloneNotSupportedException"})
        @Override
        protected Resources clone() {
            List<String> files = new ArrayList<>(this.files);
            List<String> directories = new ArrayList<>(this.directories);
            return new Resources(files, directories, this.clazz);
        }

        public InputStream openStream(String file) throws IOException {
            if(clazz == null && resourcesRoot == null) throw new IOException("Failed to open stream");
            if(clazz != null && jar){
                file = file.replace(File.separator, "/");
                file = !file.startsWith("/") ? "/"+file : file;
                return clazz.getResourceAsStream(file);
            }
            return resourcesRoot.child(file).openInput();
        }

        public Resources relative(String i) {
            files = files.stream().map(s1 -> s1.startsWith(i) ? s1.substring((i+File.separator).length()) : s1).collect(Collectors.toList());
            directories = directories.stream().map(s1 -> s1.startsWith(i) ? s1.substring((i).length()) : s1).collect(Collectors.toList());
            return this;
        }
    }
}
