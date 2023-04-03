// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;

class TestActivite {

    @Nested
    class Contructeur {
        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite(null, "id"));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("", "id"));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("nom", null));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Activite("nom", ""));
        }

        @Test
        void Test4Jeu1() throws Exception {
            Activite activite = new Activite("nom", "id");
            Assertions.assertNotNull(activite);
            Assertions.assertEquals("nom", activite.getNom());
            Assertions.assertEquals("id", activite.getId());
        }
    }

    @Nested
    class MettreALaCorbeille {

        @AfterAll
        static void tearDownAfterClass() throws Exception {
            Corbeille.getInstance().viderLaCorbeille();
        }

        @Test
        void TestMettreALaCorbeille() throws Exception {
            Activite activite = new Activite("nom", "id");
            MockedTache tache = new MockedTache(activite);
            Assertions.assertNotNull(activite);
            Assertions.assertTrue(activite.estActif());

            try {
                activite.ajouterTache(tache);
            } catch (OperationImpossible e) {
                Assertions.fail("Test impossible : impossible d'ajouter une tache à une activité");
            }

            activite.mettreALaCorbeille();
            Assertions.assertFalse(activite.estActif());

            Assertions.assertEquals(1, tache.mettreALaCorbeilleCalledTimes);

            activite.mettreALaCorbeille();
            Assertions.assertFalse(activite.estActif());

            int size = Corbeille.getInstance().getElementsJetable(Activite.class).size();
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class AjouterUneTache {
        ITache tache;
        Activite activite;

        @BeforeEach
        void setUp() throws Exception {
            activite = new Activite("nom", "id");
            tache = new MockedTache(activite);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
            tache = null;
            Corbeille.getInstance().viderLaCorbeille();
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> activite.ajouterTache(null));
        }

        @Test
        void Test2() throws Exception {
            tache = new MockedTache(activite, false);

            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(tache));
        }

        @Test
        void Test3() throws Exception {
            tache = new MockedTache(new Activite("nom2", "id2"));

            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(tache));
        }

        @Test
        void Test4() throws Exception {
            activite.ajouterTache(tache);
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(tache));
        }

        @Test
        void Test5() throws Exception {
            activite.mettreALaCorbeille();
            Assertions.assertFalse(activite.estActif());
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(tache));
        }

        @Test
        void Test6() throws Exception {
            activite.ajouterTache(tache);
            Assertions.assertEquals(1, activite.getTaches().size());
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }
    }

}
