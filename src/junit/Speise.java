
import java.util.Arrays;
import java.util.List;


public class Speise {

    private String name;
    private String kategorie;
    private boolean vegetarisch;
    private double preis;
    private int kalorien;
    private String allergene;

    public static final List<Speise> allMeals = Arrays.asList(
            new Speise("Fritattensuppe;Suppe;nein;4.2;360;ACFGLM"),
            new Speise("Gulaschsuppe;Suppe;nein;4.9;312;ALO"),
            new Speise("Knoblauchcremesuppe;Suppe;nein;4.7;500;AGLM"),
            new Speise("Beef Tartare;Vorspeise;nein;13.9;425;ACM"),
            new Speise("Weinbergschnecken;Vorspeise;nein;11.9;330;AGR"),
            new Speise("Rindercarpaccio mit Fladenbrot;Vorspeise;nein;9.9;435;ACGNP"),
            new Speise("Schweinsbraten mit Krautsalat und Serviettenknoedel;Hauptspeise;nein;11.7;781;ACG"),
            new Speise("Wiener Schnitzel vom Schwein mit Salat;Hauptspeise;nein;9.6;518;ACG"),
            new Speise("Pute natur mit Reis;Hauptspeise;nein;9.2;223;AG"),
            new Speise("Zander vom Grill;Hauptspeise;nein;14.9;353;ADG"),
            new Speise("Zwiebelrostbraten;Hauptspeise;nein;15.5;336;ALM"),
            new Speise("Steirischer Backhendlsalat;Hauptspeise;nein;9.7;700;ACGM"),
            new Speise("Kaesespaetzle mit gemischtem Salat;Hauptspeise;ja;8.5;1025;CGM"),
            new Speise("Chili sin carne mit geraeuchertem Tofu;Hauptspeise;ja;7.2;250;ACFGN"),
            new Speise("Lauchpancakes mit Kaesesauce;Hauptspeise;ja;7.9;948;ACGL"),
            new Speise("Geroestete Knoedel mit Ei & Salat;Hauptspeise;ja;6.9;280;ACG"),
            new Speise("Schokoladepalatschinken mit Schlagobers & Nuessen;Nachspeise;ja;4.9;310;ACEG"),
            new Speise("Gebackene Apfelspalten;Nachspeise;ja;5.9;350;ACFHN"),
            new Speise("Topfenknoedel mit Zwetschkenroester;Nachspeise;ja;4.5;222;ACG"),
            new Speise("Schokomousse-Trilogie;Nachspeise;ja;8.5;457;CEFG")
    );

    public Speise(String allInfo) {
        String[] details = allInfo.split(";");
        name = details[0];
        kategorie = details[1];
        vegetarisch = details[2].equals("ja");
        preis = Double.parseDouble(details[3]);
        kalorien = Integer.parseInt(details[4]);
        allergene = details[5];
    }

    public String getName() {
        return name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public boolean isVegetarisch() {
        return vegetarisch;
    }

    public double getPreis() {
        return preis;
    }

    public int getKalorien() {
        return kalorien;
    }

    public String getAllergene() {
        return allergene;
    }

     @Override
    public String toString() {
        return name;
    }

}
