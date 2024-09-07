package regex.adv;

public class SearchAndReplace {
    public static String singleSpace(String text) {
        return text.replaceAll("\\s+", " ");
    }

    public static String capitalize(String text) {
        String[] words = text.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            } else {
                capitalized.append(" ");
            }
        }

        return capitalized.toString().trim();
    }

    public static String splitIntoLines(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (line.length() + word.length() + 1 > 40) {
                result.append(line.toString().trim()).append("\n");
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }
        if (!line.isEmpty()) {
            result.append(line.toString().trim());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String input = "Dies    ist ein   Beispieltext, der   umgeformt werden soll.";
        System.out.println(singleSpace(input));
        System.out.println(capitalize(input));
        System.out.println(splitIntoLines(input));
    }
}
