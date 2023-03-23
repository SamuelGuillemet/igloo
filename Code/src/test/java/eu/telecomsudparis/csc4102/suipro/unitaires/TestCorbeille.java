package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedActivite;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;

public class TestCorbeille {
    @AfterEach
    void tearDown() throws Exception {
        Corbeille.getInstance().viderLaCorbeille();
    }

    @Nested
    class GetInstance {

        @Test
        void Test1() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            Assertions.assertNotNull(corbeille);
        }
    }

    @Nested
    class AjouterALaCorbeille {

        @Test
        void Test1() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            Assertions.assertThrows(IllegalArgumentException.class, () -> corbeille.ajouterALaCorbeille(null));
        }

        @Test
        void Test2() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedDeveloppeur developpeur = new MockedDeveloppeur(true, false);
            corbeille.ajouterALaCorbeille(developpeur);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedDeveloppeur.class).size());
        }
    }

    @Nested
    class SupprimerDeLaCorbeille {

        @Test
        void Test1() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            Assertions.assertThrows(IllegalArgumentException.class, () -> corbeille.supprimerDeLaCorbeille(null));
        }

        @Test
        void Test2() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedDeveloppeur developpeur = new MockedDeveloppeur(true, false);
            corbeille.ajouterALaCorbeille(developpeur);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedDeveloppeur.class).size());
            corbeille.supprimerDeLaCorbeille(developpeur);
            Assertions.assertEquals(0, corbeille.getElementsJetable(MockedDeveloppeur.class).size());
        }
    }

    @Nested
    class GetElementsJetable {

        @Test
        void Test1() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            Assertions.assertThrows(IllegalArgumentException.class, () -> corbeille.getElementsJetable(null));
        }

        @Test
        void Test2Jeu1() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedDeveloppeur developpeur = new MockedDeveloppeur(true);
            corbeille.ajouterALaCorbeille(developpeur);
            Assertions.assertEquals(1, corbeille.getElementsJetable(IDeveloppeur.class).size());
        }

        @Test
        void Test2Jeu2() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedActivite activite = new MockedActivite(true);
            corbeille.ajouterALaCorbeille(activite);
            Assertions.assertEquals(1, corbeille.getElementsJetable(IActivite.class).size());
        }

        @Test
        void Test2Jeu3() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedTache tache = new MockedTache(true);
            corbeille.ajouterALaCorbeille(tache);
            Assertions.assertEquals(1, corbeille.getElementsJetable(ITache.class).size());
        }

        @Test
        void Test2Jeu4() throws Exception {
            Corbeille corbeille = Corbeille.getInstance();
            MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(true);
            corbeille.ajouterALaCorbeille(periodeDeTravail);
            Assertions.assertEquals(1, corbeille.getElementsJetable(MockedPeriodeDeTravail.class).size());
        }
    }
}
