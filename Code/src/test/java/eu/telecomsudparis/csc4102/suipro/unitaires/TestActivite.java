// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.Label;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedActivite;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedCorbeille;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;

class TestActivite {

    @Nested
    class Contructeur {
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
        }

        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite(null, "id"));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("", "id"));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("nom", null));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("nom", ""));
        }

        @Test
        void Test3Jeu1() throws Exception {
            Activite activite = new Activite("nom", "id");
            Assertions.assertNotNull(activite);
            Assertions.assertEquals("nom", activite.getNom());
            Assertions.assertEquals("id", activite.getId());
        }
    }

    @Nested
    class MettreALaCorbeille {

        @Test
        void TestMettreALaCorbeille() throws Exception {
            MockedCorbeille corbeille = new MockedCorbeille();
            Activite activite = new Activite("nom", "id");
            MockedTache tache = new MockedTache(activite);
            Assertions.assertNotNull(activite);
            Assertions.assertTrue(activite.estEnFonctionnement());

            try {
                activite.ajouterTache(tache);
            } catch (OperationImpossible e) {
                Assertions.fail("Test impossible : impossible d'ajouter une tache à une activité");
            }

            Assertions.assertThrows(OperationImpossible.class, () -> activite.mettreALaCorbeille(null));

            activite.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.mettreALaCorbeilleCalledTimes);

            activite.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(activite.estEnFonctionnement());

            int size = corbeille.getNbAjout(activite);
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class Restaurer {

        @Test
        void TestRestaurer() throws Exception {
            MockedCorbeille corbeille = new MockedCorbeille();
            Activite activite = new Activite("nom", "id");
            MockedTache tache = new MockedTache(activite);
            Assertions.assertNotNull(activite);
            Assertions.assertTrue(activite.estEnFonctionnement());

            try {
                activite.ajouterTache(tache);
            } catch (OperationImpossible e) {
                Assertions.fail("Test impossible : impossible d'ajouter une tache à une activité");
            }

            Assertions.assertThrows(OperationImpossible.class, () -> activite.mettreALaCorbeille(null));

            activite.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.mettreALaCorbeilleCalledTimes);

            activite.restaurer(corbeille);
            Assertions.assertTrue(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.restaurerCalledTimes);

            activite.restaurer(corbeille);
            Assertions.assertTrue(activite.estEnFonctionnement());

            int size = corbeille.getNbSuppression(activite);
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class AjouterUneTache {
        ITache tache;
        Activite activite;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new Activite("nom", "id");
            tache = new MockedTache(activite);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
            tache = null;
            corbeille = null;
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(null));
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
            activite.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(activite.estEnFonctionnement());
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterTache(tache));
        }

        @Test
        void Test6() throws Exception {
            activite.ajouterTache(tache);
            Assertions.assertEquals(1, activite.getTaches().size());
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }
    }

    @Nested
    class OnNext {
        ITache tache;
        Activite activite;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new Activite("nom", "id");
            corbeille.subscribe(activite);
            tache = new MockedTache(activite);
            activite.ajouterTache(tache);

            Thread.sleep(50);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
            tache = null;
            corbeille = null;
        }

        @Test
        void Test1() throws Exception {
            activite.onNext(null);
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test2() throws Exception {
            activite.onNext(new MockedActivite(true));
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test3() throws Exception {
            activite.onNext(tache);

            Assertions.assertFalse(activite.getTaches().contains(tache));
        }
    }

    @Nested
    class CalculerTempsDeTravail {
        MockedTache tache;
        Activite activite;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new Activite("nom", "id");
            tache = new MockedTache(activite);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
            tache = null;
        }

        @Test
        void Test1() throws Exception {
            double time = activite.calculerTempsDeTravail();
            Assertions.assertEquals(0, time);

        }

        @Test
        void Test2() throws Exception {
            activite.ajouterTache(tache);
            activite.calculerTempsDeTravail();
            Assertions.assertEquals(1, tache.calculerTempsDeTravailCalledTimes);
        }
    }

    @Nested
    class AjouterLabel {
        Activite activite;
        Corbeille corbeille;
        Label label;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new Activite("nom", "id");
            label = new Label("label", "labelName");
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterLabel(null));
        }

        @Test
        void Test5puis4() throws Exception {
            activite.mettreALaCorbeille(corbeille);
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterLabel(label));
        }

        @Test
        void Test3() throws Exception {
            activite.ajouterLabel(label);
            Assertions.assertTrue(activite.getLabels().contains(label));
            Assertions.assertThrows(OperationImpossible.class, () -> activite.ajouterLabel(label));
        }
    }
}
