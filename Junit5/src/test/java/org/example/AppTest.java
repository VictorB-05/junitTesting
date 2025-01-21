package org.example;

import org.junit.jupiter.api.Test;
import org.example.App;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void esDniValido() {
        System.out.println("DNI test");
        assertTrue(App.esDniValido("12345678A"));
        assertFalse(App.esDniValido("12345678AP"));
        assertFalse(App.esDniValido("12345678"));
        assertFalse(App.esDniValido("12345678A0"));
        assertFalse(App.esDniValido("123456789"));

    }
}