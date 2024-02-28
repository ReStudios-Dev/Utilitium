package org.ReStudios.utitlitium;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Colorizium {

    /**
     * Apply color provider to string and return it
     * @param string Input plain string
     * @param prefix Prefix for selectors
     * @param provider Provider. null for default
     * @param suffix Suffix for selectors
     * @return Colorized and styled string
     */
    public static String apply(String string, String prefix, AColorProvider provider, String suffix){
        if(provider == null) provider = new ColorProvider();
        ArrayList<Line> colors = provider.getColors();
        for (Line color : colors) {
            String find = prefix+color.string+suffix;
            String replacement = "";
            if(color instanceof ColorLine){
                ColorLine line = (ColorLine)color;
                Color c = line.color;
                replacement = rgb(line.foreground, c.getRed(), c.getGreen(), c.getBlue());
            }else if(color instanceof StyleLine){
                StyleLine line = (StyleLine)color;
                Style style = line.style;
                switch (style){
                    case BOLD:
                        replacement = bold();
                        break;
                    case ITALIC:
                        replacement = italic();
                        break;
                    case UNDERLINE:
                        replacement = underline();
                        break;
                    case STRIKETHROUGH:
                        replacement = strikethrough();
                        break;
                    case FRAMED:
                        replacement = framed();
                        break;
                }
            }else if(color instanceof ResetLine){
                replacement = reset();
            }
            string = string.replace(find, replacement);
        }
        return string;
    }

    /**
     * RGB to ANSI FOREGROUND color (24 bit)
     * <p>
     * You can use it to output colored text to console using System.out
     * Example: System.out.println( rgb(255, 0, 0) + "this is red text" );
     * </p>
     * @param r red color (0-255)
     * @param g green color (0-255)
     * @param b blue color (0-255)
     * @return ANSI color escape code
     */
    public static String rgb(int r, int g, int b){
        return rgb(true, r, g, b);
    }

    /**
     * RGB to ANSI BACKGROUND color (24 bit)
     * <p>
     * You can use it to output colored text to console using System.out
     * Example: System.out.println( bg_rgb(255, 0, 0) + "this is text with red background" );
     * </p>
     * @param r red color (0-255)
     * @param g green color (0-255)
     * @param b blue color (0-255)
     * @return ANSI color escape code
     */
    public static String bg_rgb(int r, int g, int b){
        return rgb(false, r, g, b);
    }

    /**
     * RGB to ANSI color (24 bit)
     * <p>
     * You can use it to output colored text to console using System.out
     * Example: System.out.println( rgb(true, 0, 255, 0) + "this is green text" );
     * </p>
     * @param foreground is foreground (text color or background color)
     * @param r red color (0-255)
     * @param g green color (0-255)
     * @param b blue color (0-255)
     * @return ANSI color escape code
     */
    public static String rgb(boolean foreground, int r, int g, int b){
        r = MathUtils.clamp(0, 255, r);
        g = MathUtils.clamp(0, 255, g);
        b = MathUtils.clamp(0, 255, b);
        return "\u001B["+(foreground?"3":"4")+"8;2;R;G;Bm"
                .replace("R", String.valueOf(r))
                .replace("B", String.valueOf(b))
                .replace("G", String.valueOf(g));
    }

    /**
     * ANSI reset color escape code
     * <p>
     * You can use it to reset all colors and text styles
     * Example: System.out.println( rgb(true, 0, 255, 0) + "this is green text" + reset() + " this is white text" );
     * </p>
     * @return ANSI escape code
     */
    public static String reset(){
        return "\u001B[0m";
    }

    /**
     * ANSI bold text style escape code
     * <p>
     * You can use it to apply bold style to text
     * Example: System.out.println( rgb(true, 0, 255, 0) + bold() + "this is bold green text" );
     * </p>
     * @return ANSI escape code
     */
    public static String bold(){
        return "\u001B[1m";
    }

    /**
     * ANSI italic text style escape code
     * <p>
     * You can use it to apply italic style to text
     * Example: System.out.println( rgb(true, 0, 255, 0) + italic() + "this is italic green text" );
     * </p>
     * @return ANSI escape code
     */
    public static String italic(){
        return "\u001B[3m";
    }

    /**
     * ANSI underline text style escape code
     * <p>
     * You can use it to apply underline style to text
     * Example: System.out.println( rgb(true, 0, 255, 0) + underline() + "this is underline green text" );
     * </p>
     * @return ANSI escape code
     */
    public static String underline(){
        return "\u001B[4m";
    }

    /**
     * ANSI strikethrough text style escape code
     * <p>
     * You can use it to apply strikethrough style to text
     * Example: System.out.println( rgb(true, 0, 255, 0) + strikethrough() + "this is strikethrough green text" );
     * </p>
     * @return ANSI escape code
     */
    public static String strikethrough(){
        return "\u001B[9m";
    }

    /**
     * ANSI framed text style escape code
     * <p>
     * You can use it to apply framed style to text
     * Example: System.out.println( rgb(true, 0, 255, 0) + framed() + "this is framed green text" );
     * </p>
     * @return ANSI escape code
     */
    public static String framed(){
        return "\u001B[51m";
    }

    /**
     * Abstract color provider
     * <p>
     * You can extend it to customize the color scheme
     * </p>
     */
    public static abstract class AColorProvider {

        /**
         * Get list of color lines.
         * @see org.ReStudios.utitlitium.Colorizium.Line
         * @return list of color lines
         */
        public abstract ArrayList<Line> getColors();
    }

    /**
     * Style enum
     */
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH, FRAMED
    }

    /**
     * Abstract item for color provider list
     * <p>
     * It contains selector to replace. It can be extended by:
     * ResetLine - for reset colors (doesn't contain any variable)
     * ColorLine - for special color (contain java.awt.Color and boolean for background or foreground)
     * StyleLine - for style like bold, italic etc (contain only Style enum)
     * </p>
     */
    public static abstract class Line {
        /**
         * Selector.
         * For example selector "red" for prefix "&" and suffix "", will replace "&red" with special value
         */
        public final String string;

        public Line(String string) {
            this.string = string;
        }
    }

    /**
     * Reset all colors and styles. Text after this line will be default (usually plain white)
     */
    public static class ResetLine extends Line {
        public ResetLine(String string) {
            super(string);
        }
    }

    /**
     * Make all next characters with special color of foreground or background
     */
    public static class ColorLine extends Line{
        public final boolean foreground;
        public final Color color;

        public ColorLine(String string, boolean foreground, Color color) {
            super(string);
            this.foreground = foreground;
            this.color = color;
        }
    }

    /**
     * Make all next characters with special style: bold, italic, underline, strikethrough or framed
     */
    public static class StyleLine extends Line {
        public final Style style;

        public StyleLine(String string, Style style) {
            super(string);
            this.style = style;
        }
    }

    /**
     * Color provider. Contain pairs of selectors and lines (replacement for selector)
     */
    public static class ColorProvider extends AColorProvider{
        private final ArrayList<Line> colors;

        /**
         * Default color provider.
         * <p>
         * Example for this provider (prefix - '%', suffix - '%'):
         * "%6% orange text %3% %l%, and here bold aqua. %r% here is default plain"
         * </p>
         */
        public ColorProvider() {
            colors = new ArrayList<>();
            colors.add(new ColorLine("0", true, Color.decode("#000000"))); // black
            colors.add(new ColorLine("1", true, Color.decode("#0000AA"))); // dark blue
            colors.add(new ColorLine("2", true, Color.decode("#00AA00"))); // dark green
            colors.add(new ColorLine("3", true, Color.decode("#00AAAA"))); // dark aqua
            colors.add(new ColorLine("4", true, Color.decode("#AA0000"))); // dark red
            colors.add(new ColorLine("5", true, Color.decode("#AA00AA"))); // dark purple
            colors.add(new ColorLine("6", true, Color.decode("#FFAA00"))); // dark yellow (gold)
            colors.add(new ColorLine("7", true, Color.decode("#AAAAAA"))); // gray
            colors.add(new ColorLine("8", true, Color.decode("#555555"))); // dark gray
            colors.add(new ColorLine("9", true, Color.decode("#5555FF"))); // blue
            colors.add(new ColorLine("a", true, Color.decode("#55FF55"))); // green
            colors.add(new ColorLine("b", true, Color.decode("#55FFFF"))); // aqua
            colors.add(new ColorLine("c", true, Color.decode("#FF5555"))); // red
            colors.add(new ColorLine("d", true, Color.decode("#FF55FF"))); // purple
            colors.add(new ColorLine("e", true, Color.decode("#FF55FF"))); // yellow
            colors.add(new ColorLine("f", true, Color.decode("#FFFFFF"))); // white

            colors.add(new StyleLine("l", Style.BOLD)); // bold
            colors.add(new StyleLine("o", Style.ITALIC)); // italic
            colors.add(new StyleLine("n", Style.UNDERLINE)); // underline
            colors.add(new StyleLine("m", Style.STRIKETHROUGH)); // strikethrough
            colors.add(new StyleLine("k", Style.FRAMED)); // framed

            colors.add(new ResetLine("r")); // reset
        }

        @Override
        public ArrayList<Line> getColors() {
            return colors;
        }
    }
}
