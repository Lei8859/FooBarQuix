package com.example.FooBarQuix.sevice;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FooBarQuixServiceTest {

    private final FooBarQuixService service = new FooBarQuixService();

    @Test
    void testNombreSansRegle() {
        assertEquals("1", service.transformerNombre(1));
        assertEquals("2", service.transformerNombre(2));
    }

    @Test
    void testDivisiblePar3() {
        assertEquals("FOO", service.transformerNombre(9));
        assertEquals("FOOFOO", service.transformerNombre(3)); // Divisible par 3 et contient 3
    }

    @Test
    void testDivisiblePar5() {
        assertEquals("BAR", service.transformerNombre(10));
        assertEquals("BARBAR", service.transformerNombre(5)); // Divisible par 5 et contient 5
    }

    @Test
    void testContient7() {
        assertEquals("QUIX", service.transformerNombre(7));
        assertEquals("FOOQUIX", service.transformerNombre(37)); // Contient 3 et 7
    }

    @Test
    void testDivisiblePar3Et5() {
        assertEquals("FOOBARBAR", service.transformerNombre(15)); // 15 -> FOO (div3) + BARBAR (div5 + contient5)
        assertEquals("FOOBAR", service.transformerNombre(30)); // 30 -> FOO (div3) + BAR (div5)
    }

    @Test
    void testCasCompliques() {
        assertEquals("FOOBAR", service.transformerNombre(51)); // 51 -> FOO (div3) + BAR (contient5)
        assertEquals("BARFOO", service.transformerNombre(53)); // 53 -> BAR (contient5) + FOO (contient3)
        assertEquals("FOOFOOFOO", service.transformerNombre(33)); // 33 -> FOO (div3) + FOOFOO (contient 3 deux fois)
    }
}
