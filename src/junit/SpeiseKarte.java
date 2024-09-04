package junit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpeiseKarte {
    static List<Speise> speisen = Speise.allMeals;

    public static void main(String[] args) {
        System.out.println("Alle Speisen alphabetisch sortiert:\n" + getAllNames());
        System.out.println("\nSpeisen billiger als 7 €:\n" + getCheapOnes());
        System.out.println("\nDie 2 teuersten Speisen:\n" + getMostExpensive());
        System.out.println("\nAlle Speisen ohne \"L\" Allergen:\n" + getSaveOnes("L"));
        System.out.println("\nDurchschnittspreis der Hauptspeisen:\n" + getAverageMain());
        System.out.println("\nMap zu Kategorien:\n" + getAsMap());
        System.out.println("\nMap zu Kalorien der Kategorien:\n" + getCaloriesSum());
        System.out.println("\nVegetarische Speisen nach Kalorien sortiert:\n" + getVegetarianMeals());
        System.out.println("\nSpeise mit den meisten Kalorien:\n" + getTheFattener());
    }

    public static List<String> getAllNames() {
        return speisen.stream().map(Speise::getName).sorted().collect(Collectors.toList());
    }

    public static List<Speise> getCheapOnes() {
        return speisen.stream().filter(x -> x.getPreis() < 7).collect(Collectors.toList());
    }

    public static String getMostExpensive() {
        return speisen.stream().sorted((x, y) -> Double.compare(y.getPreis(), x.getPreis())).limit(2).map(x -> x.getName() + " (" + x.getPreis() + ")").collect(Collectors.joining(" , "));
    }

    public static List<String> getSaveOnes(String allergen) {
        return speisen.stream().filter(x -> !x.getAllergene().contains(allergen)).map(x -> x.getName() + " (" + x.getAllergene() + ")").collect(Collectors.toList());
    }

    public static double getAverageMain() {
        return speisen.stream().filter(x -> x.getKategorie().equals("Hauptspeise")).collect(Collectors.summarizingDouble(Speise::getPreis)).getAverage();
    }

    public static Map<String, List<Speise>> getAsMap() {
        return speisen.stream().map(Speise::getKategorie).distinct().collect(Collectors.toMap(x -> x, x -> speisen.stream().filter(y -> y.getKategorie().equals(x)).collect(Collectors.toList())));
    }

    public static Map<String, Integer> getCaloriesSum() {
        return speisen.stream().map(Speise::getKategorie).distinct().collect(Collectors.toMap(x -> x, x -> speisen.stream().filter(y -> y.getKategorie().equals(x)).mapToInt(Speise::getKalorien).sum()));
    }

    public static List<String> getVegetarianMeals() {
        return speisen.stream().filter(Speise::isVegetarisch).sorted((x, y) -> y.getKalorien() - x.getKalorien()).map(x -> x.getName() + " : " + x.getKalorien()).collect(Collectors.toList());
    }

    public static Speise getTheFattener() {
        return speisen.stream().min((x, y) -> y.getKalorien() - x.getKalorien()).orElse(null);
    }
}
