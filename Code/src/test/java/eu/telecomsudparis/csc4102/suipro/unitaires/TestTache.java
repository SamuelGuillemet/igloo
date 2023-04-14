// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.beans.PropertyChangeEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedActivite;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedCorbeille;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.Label;
import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;

class TestTache {

    @Nested
    class Contructeur {

        MockedActivite activite;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new MockedActivite(true);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
        }

        @Test
        void Test1Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache(null, "id", activite));
        }

        @Test
        void Test1Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("", "id", activite));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("nom", null, activite));
        }

        @Test
        void Test2Jeu2() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("nom", "", activite));
        }

        @Test
        void Test3() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("nom", "id", null));
        }

        @Test
        void Test4() throws Exception {
            activite = new MockedActivite(false);
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("nom", "id", activite));
        }

        @Test
        void Test5() throws Exception {
            Tache tache = new Tache("nom", "id", activite);
            Assertions.assertNotNull(tache);
            Assertions.assertEquals("nom", tache.getNom());
            Assertions.assertEquals("id", tache.getId());
            Assertions.assertEquals(activite, tache.getActivite());
            Assertions.assertTrue(tache.estEnFonctionnement());
            Assertions.assertEquals(1, activite.ajouterTacheCalledTimes);
            Assertions.assertThrows(OperationImpossible.class, () -> new Tache("nom", "id", activite));
        }
    }

    @Nested
    class MettreALaCorbeille {

        @Test
        void Test1() throws Exception {
            MockedCorbeille corbeille = new MockedCorbeille();
            IActivite activite = new MockedActivite(true);
            Tache tache = new Tache("nom", "id", activite);
            MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(tache, true);

            Assertions.assertNotNull(tache);
            Assertions.assertTrue(tache.estEnFonctionnement());

            try {
                tache.ajouterPeriodeDeTravail(periodeDeTravail);
            } catch (Exception e) {
                Assertions.fail(e);
                Assertions.fail("Test impossible : impossible d'ajouter une période de travail à la tâche");
            }

            tache.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(tache.estEnFonctionnement());

            Assertions.assertEquals(1, periodeDeTravail.mettreALaCorbeilleCalledTimes);

            tache.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(tache.estEnFonctionnement());

            int size = corbeille.getNbAjout(tache);
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class Restaurer {
        MockedCorbeille corbeille;
        MockedActivite activite;
        MockedPeriodeDeTravail periodeDeTravail;
        Tache tache;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new MockedCorbeille();
            activite = new MockedActivite(true);

            tache = new Tache("nom", "id", activite);

            periodeDeTravail = new MockedPeriodeDeTravail(tache, true);

            Assertions.assertNotNull(tache);
            Assertions.assertTrue(tache.estEnFonctionnement());

            try {
                tache.ajouterPeriodeDeTravail(periodeDeTravail);
            } catch (Exception e) {
                Assertions.fail(e);
                Assertions.fail("Test impossible : impossible d'ajouter une période de travail à la tâche");
            }

            tache.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(tache.estEnFonctionnement());
        }

        @AfterEach
        void tearDown() throws Exception {
            corbeille = null;
            activite = null;
            tache = null;
        }

        @Test
        void Test1() throws Exception {
            activite.setEnFonctionnement(false);
            tache.restaurer(corbeille);
            Assertions.assertFalse(activite.estEnFonctionnement());
        }

        @Test
        void Test2() throws Exception {
            tache.restaurer(corbeille);
            Assertions.assertTrue(tache.estEnFonctionnement());

            Assertions.assertEquals(1, periodeDeTravail.restaurerCalledTimes);

            tache.restaurer(corbeille);
            Assertions.assertTrue(tache.estEnFonctionnement());

            int size = corbeille.getNbSuppression(tache);
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class AjouterPeriodeDeTravail {

        IPeriodeDeTravail periodeDeTravail;
        IActivite activite;
        Tache tache;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            activite = new MockedActivite(true);
            tache = new Tache("nom", "id", activite);
            periodeDeTravail = new MockedPeriodeDeTravail(tache, true);
        }

        @AfterEach
        void tearDown() {
            activite = null;
            periodeDeTravail = null;
            tache = null;
            corbeille = null;
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> tache.ajouterPeriodeDeTravail(null));
        }

        @Test
        void Test2() throws Exception {
            periodeDeTravail = new MockedPeriodeDeTravail(tache, false);
            Assertions.assertThrows(OperationImpossible.class,
                    () -> tache.ajouterPeriodeDeTravail(periodeDeTravail));
        }

        @Test
        void Test3() throws Exception {
            periodeDeTravail = new MockedPeriodeDeTravail(new Tache("nom2", "id2", activite), true);
            Assertions.assertThrows(OperationImpossible.class,
                    () -> tache.ajouterPeriodeDeTravail(periodeDeTravail));
        }

        @Test
        void Test4() throws Exception {
            tache.ajouterPeriodeDeTravail(periodeDeTravail);
            Assertions.assertThrows(OperationImpossible.class,
                    () -> tache.ajouterPeriodeDeTravail(periodeDeTravail));
        }

        @Test
        void Test5() throws Exception {
            tache.mettreALaCorbeille(corbeille);
            Assertions.assertFalse(tache.estEnFonctionnement());
            Assertions.assertThrows(OperationImpossible.class,
                    () -> tache.ajouterPeriodeDeTravail(periodeDeTravail));
        }

        @Test
        void Test6() throws Exception {
            tache.ajouterPeriodeDeTravail(periodeDeTravail);
            Assertions.assertEquals(1, tache.getPeriodesDeTravail().size());
            Assertions.assertTrue(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }
    }

    @Nested
    class PropertyChange {
        IPeriodeDeTravail periodeDeTravail;
        Tache tache;
        PropertyChangeEvent tester;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            tache = new Tache("nom", "id", new MockedActivite(true));
            periodeDeTravail = new MockedPeriodeDeTravail(tache, true);
            tache.ajouterPeriodeDeTravail(periodeDeTravail);
        }

        @AfterEach
        void tearDown() {
            periodeDeTravail = null;
            tache = null;
            tester = null;
        }

        @Test
        void Test1() throws Exception {
            tester = new PropertyChangeEvent(new Object(), "nom", "nom", "nom2");
            tache.propertyChange(tester);
            Assertions.assertTrue(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }

        @Test
        void Test2() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), "nom", "nom", "nom2");
            tache.propertyChange(tester);
            Assertions.assertTrue(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }

        @Test
        void Test3() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), "nom", "nom2");
            tache.propertyChange(tester);
            Assertions.assertTrue(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }

        @Test
        void Test4() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), periodeDeTravail,
                    periodeDeTravail);
            tache.propertyChange(tester);
            Assertions.assertTrue(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }

        @Test
        void Test5() throws Exception {
            tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), periodeDeTravail,
                    null);
            tache.propertyChange(tester);
            Assertions.assertFalse(tache.getPeriodesDeTravail().contains(periodeDeTravail));
        }
    }

    @Nested
    class CalculerTempsDeTravail {
        MockedPeriodeDeTravail periodeDeTravail;
        Tache tache;
        Corbeille corbeille;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            tache = new Tache("nom", "id", new MockedActivite(true));
            periodeDeTravail = new MockedPeriodeDeTravail(tache, true);
        }

        @AfterEach
        void tearDown() {
            periodeDeTravail = null;
            tache = null;
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertEquals(0, tache.calculerTempsDeTravail());
        }

        @Test
        void Test2() throws Exception {
            tache.ajouterPeriodeDeTravail(periodeDeTravail);
            tache.calculerTempsDeTravail();
            Assertions.assertEquals(1, periodeDeTravail.calculerTempsDeTravailCalledTimes);
        }
    }

    @Nested
    class AjouterLabel {
        Tache tache;
        Corbeille corbeille;
        Label label;

        @BeforeEach
        void setUp() throws Exception {
            corbeille = new Corbeille();
            tache = new Tache("nom", "id", new MockedActivite(true));
            label = new Label("label", "labelName");
        }

        @AfterEach
        void tearDown() throws Exception {
            tache = null;
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> tache.ajouterLabel(null));
        }

        @Test
        void Test5puis4() throws Exception {
            tache.mettreALaCorbeille(corbeille);
            Assertions.assertThrows(OperationImpossible.class, () -> tache.ajouterLabel(label));
        }

        @Test
        void Test3() throws Exception {
            tache.ajouterLabel(label);
            Assertions.assertTrue(tache.getLabels().contains(label));
            Assertions.assertThrows(OperationImpossible.class, () -> tache.ajouterLabel(label));
        }
    }
}
