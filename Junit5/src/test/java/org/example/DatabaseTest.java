package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    @Test
    void userExists() {
        try {
            System.out.println(("Test para base"));
            Database sql = new Database();
            assertTrue((sql.autenticarUsuario("20605296X")) && !sql.autenticarUsuario("30658921B"));
            assertFalse(sql.autenticarUsuario("pacopaco"));
        }catch (Exception ex){
            fail("Fallo el test");
        }
    }

    @Test
    void getSaldo() {
        try {
            System.out.println("test para base");
            Database sql = new Database();
            assertEquals(0, sql.obtenerSaldo("12345678B"));

        }catch (Exception ex){
            fail("fallo");
        }
    }
}