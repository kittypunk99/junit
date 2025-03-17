package junit;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpeise {

    @Test
    public void testConstructors() {
        assertDoesNotThrow(() -> {
            new Speise("essen;suppe;nein;3.0;200;ACFGLM");//sinnvoller Test für Speise
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;-3.0;200;ACFGLM");//preis ist negativ

        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;asdf;3.0;200;ACFGLM;");//vegetarisch ist kein boolean
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;3.0;-200;ACFGLM");//kalorien ist negativ
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;3.0;200;ACFGLM; ");//zu viele Attribute
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;3.0;200");//zu wenig Attribute
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;3.0;200;xyz");//sinnlose Allergene
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;a;200;ACFGLM");//Preis keine Zahl
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;nein;3.0;a;ACFGLM");//Kalorien keine Zahl
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Speise("essen;suppe;;3.0;200;ACFGLM");// leer
        });
    }

    @Test
    public void testGetters() {
        Speise speise = new Speise("essen;suppe;nein;3.0;200;ACFGLM");
        assertEquals ("essen", speise.getName());
        assertEquals ("suppe", speise.getKategorie());
        assertFalse (speise.isVegetarisch());
        assertEquals (3.0, speise.getPreis());
        assertEquals (200, speise.getKalorien());
        assertEquals ("ACFGLM", speise.getAllergene());
        assertEquals ("essen", speise.toString());
    }

    @Test
    void allNamesShouldBeSortedAlphabetically() {
        List<String> names = SpeiseKarte.getAllNames();
        assertEquals(List.of("Beef Tartare", "Chili sin carne mit geraeuchertem Tofu", "Fritattensuppe", "Gebackene Apfelspalten", "Geroestete Knoedel mit Ei & Salat", "Gulaschsuppe", "Kaesespaetzle mit gemischtem Salat", "Knoblauchcremesuppe", "Lauchpancakes mit Kaesesauce", "Pute natur mit Reis", "Rindercarpaccio mit Fladenbrot", "Schokoladepalatschinken mit Schlagobers & Nuessen", "Schokomousse-Trilogie", "Schweinsbraten mit Krautsalat und Serviettenknoedel", "Steirischer Backhendlsalat", "Topfenknoedel mit Zwetschkenroester", "Weinbergschnecken", "Wiener Schnitzel vom Schwein mit Salat", "Zander vom Grill", "Zwiebelrostbraten"), names);
    }

    @Test
    void cheapOnesShouldReturnMealsCheaperThan7() {
        List<Speise> cheapMeals = SpeiseKarte.getCheapOnes();
        assertTrue(cheapMeals.stream().allMatch(meal -> meal.getPreis() < 7));
    }

    @Test
    void mostExpensiveShouldReturnTwoMostExpensiveMeals() {
        String mostExpensive = SpeiseKarte.getMostExpensive();
        assertEquals("Zwiebelrostbraten (15.5) , Zander vom Grill (14.9)", mostExpensive);
    }

    @Test
    void saveOnesShouldReturnMealsWithoutSpecifiedAllergen() {
        List<String> saveMeals = SpeiseKarte.getSaveOnes("L");
        assertTrue(saveMeals.stream().allMatch(meal -> !meal.contains("L")));
    }

    @Test
    void averageMainShouldReturnCorrectAveragePrice() {
        double averagePrice = SpeiseKarte.getAverageMain();
        assertEquals(11.1, averagePrice, 0.01);
    }

    @Test
    void asMapShouldReturnMealsGroupedByCategory() {
        Map<String, List<Speise>> mealsMap = SpeiseKarte.getAsMap();
        assertEquals(4, mealsMap.size());
        assertTrue(mealsMap.containsKey("Suppe"));
        assertTrue(mealsMap.containsKey("Vorspeise"));
        assertTrue(mealsMap.containsKey("Hauptspeise"));
        assertTrue(mealsMap.containsKey("Nachspeise"));
    }

    @Test
    void caloriesSumShouldReturnCorrectCaloriesSumForEachCategory() {
        Map<String, Integer> caloriesMap = SpeiseKarte.getCaloriesSum();
        assertEquals(4, caloriesMap.size());
        assertEquals(1172, caloriesMap.get("Suppe"));
        assertEquals(1090, caloriesMap.get("Vorspeise"));
        assertEquals(7208, caloriesMap.get("Hauptspeise"));
        assertEquals(1339, caloriesMap.get("Nachspeise"));
    }

    @Test
    void vegetarianMealsShouldBeSortedByCaloriesDescending() {
        List<String> vegetarianMeals = SpeiseKarte.getVegetarianMeals();
        assertEquals(List.of("Kaesespaetzle mit gemischtem Salat : 1025", "Lauchpancakes mit Kaesesauce : 948", "Schokomousse-Trilogie : 457", "Gebackene Apfelspalten : 350", "Schokoladepalatschinken mit Schlagobers & Nuessen : 310", "Geroestete Knoedel mit Ei & Salat : 280", "Chili sin carne mit geraeuchertem Tofu : 250", "Topfenknoedel mit Zwetschkenroester : 222"), vegetarianMeals);
    }

    @Test
    void theFattenerShouldReturnMealWithMostCalories() {
        Speise fattener = SpeiseKarte.getTheFattener();
        assertEquals("Kaesespaetzle mit gemischtem Salat", fattener.getName());
    }

}
