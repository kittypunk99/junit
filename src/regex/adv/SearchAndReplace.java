package regex.adv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAndReplace {
    public static String singleSpace(String text) {
        return text.replaceAll("\\s+", " ");
    }

    public static String capitalize(String text) {
        Pattern pattern = Pattern.compile("\\b(\\w)");
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
                matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static String splitIntoLines(String text) {
        return text.replaceAll("(.{1,40})(\\s+|$)", "$1\n").trim();
    }

    public static void main(String[] args) {
        String input = "Dies    ist ein   Beispieltext, der   umgeformt werden soll.";
        System.out.println(singleSpace(input));
        System.out.println(capitalize(input));
        System.out.println(splitIntoLines(input));
    }
}
