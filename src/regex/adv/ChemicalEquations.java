package regex.adv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChemicalEquations {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("ressources/equations.txt");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFormallyCorrect(line)) {
                    System.out.println("formally correct");
                } else {
                    System.out.println("incorrect");
                }
            }
        }
    }

    public static boolean isFormallyCorrect(String equation) {
        String[] sides = equation.split("->");
        if (sides.length != 2) {
            return false;
        }
        Map<String, Integer> reactantsCount = countAtoms(sides[0]);
        Map<String, Integer> productsCount = countAtoms(sides[1]);
        return reactantsCount.equals(productsCount);
    }

    public static Map<String, Integer> countAtoms(String side) {
        Map<String, Integer> atomCounts = new HashMap<>();
        String[] compounds = side.split("\\+");
        Pattern pattern = Pattern.compile("(\\d*)\\s*([A-Z][a-z]*)(\\d*)");
        for (String compound : compounds) {
            Matcher matcher = pattern.matcher(compound.trim());
            while (matcher.find()) {
                int moleculeCount = matcher.group(1).isEmpty() ? 1 : Integer.parseInt(matcher.group(1));
                String atom = matcher.group(2);
                int atomCount = matcher.group(3).isEmpty() ? 1 : Integer.parseInt(matcher.group(3));
                atomCounts.put(atom, atomCounts.getOrDefault(atom, 0) + moleculeCount * atomCount);
            }
        }
        return atomCounts;
    }
}
