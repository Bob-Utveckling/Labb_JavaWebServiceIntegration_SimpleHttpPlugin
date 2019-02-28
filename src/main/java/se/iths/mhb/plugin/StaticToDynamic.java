package se.iths.mhb.plugin;

public class StaticToDynamic {

    public static String replaceLine(String staticString,
                       String searchFor,
                       String replaceWith) {

        return (staticString.replace(searchFor, replaceWith));
    }
}
