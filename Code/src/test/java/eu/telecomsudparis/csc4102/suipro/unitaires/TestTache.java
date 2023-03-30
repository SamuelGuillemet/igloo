// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedActivite;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;

class TestTache {

    @Nested
    class Contructeur {

        MockedActivite activite;

        @BeforeEach
        void setUp() throws Exception {
            activite = new MockedActivite(true);
        }

        @AfterEach
        void tearDown() throws Exception {
            activite = null;
        }

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
        void Test3() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", "id", null));
        }

        @Test
        void Test4() throws Exception {
            activite = new MockedActivite(false);
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", "id", activite));
        }

        @Test
        void Test5() throws Exception {
            Tache tache = new Tache("nom", "id", activite);
            Assertions.assertNotNull(tache);
            Assertions.assertEquals("nom", tache.getNom());
            Assertions.assertEquals("id", tache.getId());
            Assertions.assertEquals(activite, tache.getActivite());
            Assertions.assertTrue(tache.estActif());
            Assertions.assertEquals(1, activite.ajouterTacheCalledTimes);
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("nom", "id", activite));
        }
    }

    @Nested
    class MettreALaCorbeille {

        @AfterAll
        static void tearDownAfterClass() throws Exception {
            Corbeille.getInstance().viderLaCorbeille();
        }

        @Test
        void Test1() throws Exception {
            IActivite activite = new MockedActivite(true);
            Tache tache = new Tache("nom", "id", activite);
            MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(tache, true);

            Assertions.assertNotNull(tache);
            Assertions.assertTrue(tache.estActif());

            try {
                tache.ajouterPeriodeDeTravail(periodeDeTravail);
            } catch (Exception e) {
                Assertions.fail(e);
                Assertions.fail("Test impossible : impossible d'ajouter une période de travail à la tâche");
            }

            tache.mettreALaCorbeille();
            Assertions.assertFalse(tache.estActif());

            Assertions.assertEquals(1, periodeDeTravail.mettreALaCorbeilleCalledTimes);

            tache.mettreALaCorbeille();
            Assertions.assertFalse(tache.estActif());

            int size = Corbeille.getInstance().getElementsJetable(Tache.class).size();
            Assertions.assertEquals(1, size);
        }
    }

    @Nested
    class AjouterPeriodeDeTravail {

        IPeriodeDeTravail periodeDeTravail;
        IActivite activite;
        Tache tache;

        @BeforeEach
        void setUp() throws Exception {
            activite = new MockedActivite(true);
            tache = new Tache("nom", "id", activite);
            periodeDeTravail = new MockedPeriodeDeTravail(tache, true);
        }

        @AfterEach
        void tearDown() {
            activite = null;
            periodeDeTravail = null;
            tache = null;

            Corbeille.getInstance().viderLaCorbeille();
        }

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(IllegalArgumentException.class, () -> tache.ajouterPeriodeDeTravail(null));
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
            tache.mettreALaCorbeille();
            Assertions.assertFalse(tache.estActif());
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
}
