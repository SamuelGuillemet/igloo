// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedActivite;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedElementJetable;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestCorbeille {
    Corbeille corbeille;

    @BeforeEach
    void setUp() throws Exception {
        corbeille = new Corbeille();
    }

    @AfterEach
    void tearDown() throws Exception {
        corbeille = null;
    }

    @Nested
    class AjouterALaCorbeille {

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> corbeille.ajouterALaCorbeille(null));
        }

        @Test
        void Test2() throws Exception {
            MockedElementJetable elem = new MockedElementJetable();
            corbeille.ajouterALaCorbeille(elem);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedElementJetable.class).size());
            corbeille.ajouterALaCorbeille(elem);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedElementJetable.class).size());
        }
    }

    @Nested
    class SupprimerDeLaCorbeille {

        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> corbeille.supprimerDeLaCorbeille(null));
        }

        @Test
        void Test2() throws Exception {
            MockedElementJetable elem = new MockedElementJetable();
            corbeille.ajouterALaCorbeille(elem);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedElementJetable.class).size());
            corbeille.supprimerDeLaCorbeille(elem);
            Assertions.assertEquals(0, corbeille.getElementsJetable(MockedElementJetable.class).size());
            corbeille.supprimerDeLaCorbeille(elem);
            Assertions.assertEquals(0, corbeille.getElementsJetable(MockedElementJetable.class).size());
        }
    }

    @Nested
    class GetElementsJetable {
        @Test
        void Test1() throws Exception {
            Assertions.assertThrows(OperationImpossible.class, () -> corbeille.getElementsJetable(null));
        }

        @Test
        void Test2Jeu1() throws Exception {
            MockedDeveloppeur developpeur = new MockedDeveloppeur(true);
            corbeille.ajouterALaCorbeille(developpeur);
            Assertions.assertEquals(1, corbeille.getElementsJetable(IDeveloppeur.class).size());
        }

        @Test
        void Test2Jeu2() throws Exception {
            MockedActivite activite = new MockedActivite(true);
            corbeille.ajouterALaCorbeille(activite);
            Assertions.assertEquals(1, corbeille.getElementsJetable(IActivite.class).size());
        }

        @Test
        void Test2Jeu3() throws Exception {
            MockedTache tache = new MockedTache(true);
            corbeille.ajouterALaCorbeille(tache);
            Assertions.assertEquals(1, corbeille.getElementsJetable(ITache.class).size());
        }

        @Test
        void Test2Jeu4() throws Exception {
            MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(true);
            corbeille.ajouterALaCorbeille(periodeDeTravail);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedPeriodeDeTravail.class).size());
        }
    }

    @Nested
    class ViderLaCorbeille {

        @Test
        void Test1() throws Exception {
            MockedElementJetable elem = new MockedElementJetable();
            corbeille.subscribe(elem);
            corbeille.ajouterALaCorbeille(elem);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedElementJetable.class).size());
            corbeille.viderLaCorbeille();
            Assertions.assertEquals(0, corbeille.getElementsJetable(MockedElementJetable.class).size());

            Thread.sleep(100);

            Assertions.assertEquals(elem, elem.onNextElement);
        }
    }
}
