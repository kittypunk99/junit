package junit;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assert(speise.getName().equals("essen"));
        assert(speise.getKategorie().equals("suppe"));
        assert(!speise.isVegetarisch());
        assert(speise.getPreis() == 3.0);
        assert(speise.getKalorien() == 200);
        assert(speise.getAllergene().equals("ACFGLM"));
        assert(speise.toString().equals("essen"));
    }

    @Test
    public void testSpeiseKarte() {
        assertDoesNotThrow(() -> {
            SpeiseKarte.main(null);
        });
    }

}
