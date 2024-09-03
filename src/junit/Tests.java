package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    void speiseShouldHaveCorrectName() {
        Speise speise = new Speise("Pizza");
        assertEquals("Pizza", speise.getName());
    }

    @Test
    void speiseShouldHaveCorrectPrice() {
        Speise speise = new Speise("Pizza", 8.99);
        assertEquals(8.99, speise.getPrice());
    }

    @Test
    void speisekarteShouldAddSpeise() {
        Speisekarte speisekarte = new Speisekarte();
        Speise speise = new Speise("Pizza", 8.99);
        speisekarte.addSpeise(speise);
        assertTrue(speisekarte.getSpeisen().contains(speise));
    }

    @Test
    void speisekarteShouldRemoveSpeise() {
        Speisekarte speisekarte = new Speisekarte();
        Speise speise = new Speise("Pizza", 8.99);
        speisekarte.addSpeise(speise);
        speisekarte.removeSpeise(speise);
        assertFalse(speisekarte.getSpeisen().contains(speise));
    }

    @Test
    void speisekarteShouldReturnCorrectSpeiseCount() {
        Speisekarte speisekarte = new Speisekarte();
        speisekarte.addSpeise(new Speise("Pizza", 8.99));
        speisekarte.addSpeise(new Speise("Pasta", 7.99));
        assertEquals(2, speisekarte.getSpeisen().size());
    }
}
