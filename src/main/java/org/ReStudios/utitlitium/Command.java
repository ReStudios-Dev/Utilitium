package org.ReStudios.utitlitium;

import java.util.Arrays;
import java.util.HashMap;
@SuppressWarnings("unused")
public abstract class Command {
    private final HashMap<String,Command> subCommands;
    private boolean isSub;
    private String[] currentArgs;
    protected Command parent;
    private String alias;

    private int minArguments = 0;


    public Command(){
        subCommands = new HashMap<>();
    }
    public Command(String alias){
        subCommands = new HashMap<>();
        this.alias = alias;
    }

    public int getMinArguments() { return minArguments; }

    public Command setMinArguments(int minArguments) { this.minArguments = minArguments; return this; }

    public Command addSubCommand(Command command){
        if (command.alias == null || command.alias.isEmpty() || command.alias.equalsIgnoreCase(" ")){
            throw new IllegalArgumentException("Command's alias is null. It is root command. Please, provide alias to this command.");
        }
        subCommands.put(command.alias,command);
        command.isSub = true;
        command.parent = this;
        return this;
    }

    public Command getRoot(){
        if (isSub){
            return parent.getRoot();
        }
        return this;
    }

    public String[] getRootArgs(){
        if (isSub){
            return parent.getRootArgs();
        }
        else {
            return currentArgs;
        }
    }

    public String[] getParentArgs(){
        if (!isSub) { return currentArgs;}
        else {  return parent.currentArgs; }
    }

    public abstract boolean executeCommand(Object sender,String[] args);

    @SuppressWarnings("all")
    public final boolean onCommand(String[] args){
        return onCommand(null,args);
    }


    public final boolean onCommand(Object sender,String[] args){
        currentArgs = args;
        for (String arg : args) {
            if (subCommands.containsKey(arg)) {
                return subCommands.get(arg).onCommand(sender, substring(args, 1, args.length));
            }
        }
        if (getMinArguments() > args.length){
            new IllegalArgumentException("Not enough arguments: "+args.length+". Minimum is "+getMinArguments()).printStackTrace();
            return false;
        }
        return executeCommand(sender,args);
    }

    public static <T> T[] substring(T[] x, int beginIndex, int endIndex){
        return Arrays.copyOfRange(x,beginIndex,endIndex);
    }

}
