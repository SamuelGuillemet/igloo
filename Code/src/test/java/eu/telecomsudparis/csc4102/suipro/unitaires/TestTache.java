// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.suipro.Activite;

class TestTache {
    private Activite activite;

    @BeforeEach
    void setUp() {
        activite = new Activite("activite1", "id1");
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    class TestContructeur {
        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache(null, "id", activite));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("", "id", activite));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", null, activite));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", "", activite));
        }

        @Test
        void Test3Jeu1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", "id", null));
        }

        @Test
        void Test4Jeu1() throws Exception {
            Tache tache = new Tache("nom", "id", activite);
            Assertions.assertNotNull(tache);
            Assertions.assertEquals("nom", tache.getNom());
            Assertions.assertEquals("id", tache.getId());
            Assertions.assertEquals(activite, tache.getActivite());
        }
    }

}
