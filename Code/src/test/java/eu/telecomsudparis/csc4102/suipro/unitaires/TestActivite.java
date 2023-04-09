// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.beans.PropertyChangeEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.Label;
import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
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
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite(null, "id", corbeille));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("", "id", corbeille));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("nom", null, corbeille));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("nom", "", corbeille));
        }

        @Test
        void Test3Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Activite("nom", "id", null));
        }

        @Test
        void Test4Jeu1() throws Exception {
            Activite activite = new Activite("nom", "id", corbeille);
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
            Activite activite = new Activite("nom", "id", corbeille);
            MockedTache tache = new MockedTache(activite);
            Assertions.assertNotNull(activite);
            Assertions.assertTrue(activite.estEnFonctionnement());

            try {
                activite.ajouterTache(tache);
            } catch (OperationImpossible e) {
                Assertions.fail("Test impossible : impossible d'ajouter une tache à une activité");
            }

            activite.mettreALaCorbeille();
            Assertions.assertFalse(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.mettreALaCorbeilleCalledTimes);

            activite.mettreALaCorbeille();
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
            Activite activite = new Activite("nom", "id", corbeille);
            MockedTache tache = new MockedTache(activite);
            Assertions.assertNotNull(activite);
            Assertions.assertTrue(activite.estEnFonctionnement());

            try {
                activite.ajouterTache(tache);
            } catch (OperationImpossible e) {
                Assertions.fail("Test impossible : impossible d'ajouter une tache à une activité");
            }

            activite.mettreALaCorbeille();
            Assertions.assertFalse(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.mettreALaCorbeilleCalledTimes);

            activite.restaurer();
            Assertions.assertTrue(activite.estEnFonctionnement());

            Assertions.assertEquals(1, tache.restaurerCalledTimes);

            activite.restaurer();
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
            activite = new Activite("nom", "id", corbeille);
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
            tache = new MockedTache(new Activite("nom2", "id2", corbeille));

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
    class PropertyChange {
        ITache tache;
        Activite activite;
        PropertyChangeEvent tester;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new Activite("nom", "id", corbeille);
            tache = new MockedTache(activite);
            activite.ajouterTache(tache);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
            tache = null;
            tester = null;
        }

        @Test
        void Test1() throws Exception {
            tester = new PropertyChangeEvent(new Object(), "nom", "nom", "nom2");
            activite.propertyChange(tester);
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test2() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), "nom", "nom", "nom2");
            activite.propertyChange(tester);
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test3() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), Tache.class.getSimpleName(), "nom", "nom2");
            activite.propertyChange(tester);
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test4() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), Tache.class.getSimpleName(), tache, tache);
            activite.propertyChange(tester);
            Assertions.assertTrue(activite.getTaches().contains(tache));
        }

        @Test
        void Test5() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), Tache.class.getSimpleName(), tache, null);
            activite.propertyChange(tester);
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
            activite = new Activite("nom", "id", corbeille);
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
            activite = new Activite("nom", "id", corbeille);
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
            activite.mettreALaCorbeille();
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
