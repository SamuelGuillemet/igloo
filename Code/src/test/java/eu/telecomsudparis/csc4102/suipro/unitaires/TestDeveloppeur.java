// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.beans.PropertyChangeEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedCorbeille;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestDeveloppeur {

	@Nested
	class Constructeur {

		@Test
		void Test1Jeu1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur(null, "nom", "prénom"));
		}

		@Test
		void Test1Jeu2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur("", "nom", "prénom"));
		}

		@Test
		void Test2Jeu1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur("alias", null, "prenom"));
		}

		@Test
		void Test2Jeu2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur("alias", "", "prenom"));
		}

		@Test
		void Test3Jeu1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur("alias", "nom", null));
		}

		@Test
		void Test3Jeu2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> new Developpeur("alias", "nom", ""));
		}

		@Test
		void Test4() throws Exception {
			Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
			Assertions.assertNotNull(developpeur);
			Assertions.assertEquals("alias", developpeur.getAlias());
		}
	}

	@Nested
	class MettreALaCorbeille {

		@Test
		void Test1() throws Exception {
			MockedCorbeille corbeille = new MockedCorbeille();
			Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
			developpeur.setCorbeille(corbeille);
			MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
			Assertions.assertNotNull(developpeur);
			Assertions.assertTrue(developpeur.estEnFonctionnement());

			try {
				developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			} catch (OperationImpossible e) {
				Assertions.fail("Test impossible : impossible d'ajouter une période de travail");
			}

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estEnFonctionnement());

			// Assert that the method mettreALaCorbeille() of MockedPeriodeDeTravail has been called
			Assertions.assertEquals(1, periodeDeTravail.mettreALaCorbeilleCalledTimes);

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estEnFonctionnement());

			int size = corbeille.getNbAjout(developpeur);
			Assertions.assertEquals(1, size);
		}
	}

	@Nested
	class Restaurer {
		@Test
		void Test1() throws Exception {
			MockedCorbeille corbeille = new MockedCorbeille();
			Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
			developpeur.setCorbeille(corbeille);
			MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
			Assertions.assertNotNull(developpeur);
			Assertions.assertTrue(developpeur.estEnFonctionnement());

			try {
				developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			} catch (OperationImpossible e) {
				Assertions.fail("Test impossible : impossible d'ajouter une période de travail");
			}

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estEnFonctionnement());

			developpeur.restaurer();
			Assertions.assertTrue(developpeur.estEnFonctionnement());

			Assertions.assertEquals(1, periodeDeTravail.restaurerCalledTimes);

			developpeur.restaurer();
			Assertions.assertTrue(developpeur.estEnFonctionnement());

			int size = corbeille.getNbSuppression(developpeur);
			Assertions.assertEquals(1, size);
		}
	}

	@Nested
	class AjouterPeriodeDeTravail {
		IPeriodeDeTravail periodeDeTravail;
		Developpeur developpeur;
		Corbeille corbeille;

		@BeforeEach
		void setUp() throws OperationImpossible {
			developpeur = new Developpeur("alias", "nom", "prénom");
			periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
			corbeille = new Corbeille();
			developpeur.setCorbeille(corbeille);
		}

		@AfterEach
		void tearDown() {
			periodeDeTravail = null;
			developpeur = null;
			corbeille = null;
		}

		@Test
		void Test1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class, () -> developpeur.ajouterPeriodeDeTravail(null));
		}

		@Test
		void Test2() throws Exception {
			periodeDeTravail = new MockedPeriodeDeTravail(developpeur, false);

			Assertions.assertThrows(OperationImpossible.class,
					() -> developpeur.ajouterPeriodeDeTravail(periodeDeTravail));
		}

		@Test
		void Test3() throws Exception {
			periodeDeTravail = new MockedPeriodeDeTravail(new Developpeur("other", "other", "other"), true);

			Assertions.assertThrows(OperationImpossible.class,
					() -> developpeur.ajouterPeriodeDeTravail(periodeDeTravail));
		}

		@Test
		void Test4() throws Exception {
			developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			Assertions.assertThrows(OperationImpossible.class,
					() -> developpeur.ajouterPeriodeDeTravail(periodeDeTravail));
		}

		@Test
		void Test5() throws Exception {
			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estEnFonctionnement());
			Assertions.assertThrows(OperationImpossible.class,
					() -> developpeur.ajouterPeriodeDeTravail(periodeDeTravail));
		}

		@Test
		void Test6() throws Exception {
			developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			Assertions.assertEquals(1, developpeur.getPeriodesDeTravail().size());
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}
	}

	@Nested
	class PropertyChange {
		IPeriodeDeTravail periodeDeTravail;
		Developpeur developpeur;
		PropertyChangeEvent tester;

		@BeforeEach
		void setUp() throws OperationImpossible {
			developpeur = new Developpeur("alias", "nom", "prénom");
			periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
			developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
		}

		@AfterEach
		void tearDown() {
			periodeDeTravail = null;
			developpeur = null;
			tester = null;
		}

		@Test
		void Test1() throws Exception {
			tester = new PropertyChangeEvent(new Object(), "nom", "nom", "nom2");
			developpeur.propertyChange(tester);
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}

		@Test
		void Test2() throws Exception {
			tester = new PropertyChangeEvent(new Corbeille(), "nom", "nom", "nom2");
			developpeur.propertyChange(tester);
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}

		@Test
		void Test3() throws Exception {
			tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), "nom", "nom2");
			developpeur.propertyChange(tester);
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}

		@Test
		void Test4() throws Exception {
			tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), periodeDeTravail,
					periodeDeTravail);
			developpeur.propertyChange(tester);
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}

		@Test
		void Test5() throws Exception {
			tester = new PropertyChangeEvent(new Corbeille(), PeriodeDeTravail.class.getSimpleName(), periodeDeTravail,
					null);
			developpeur.propertyChange(tester);
			Assertions.assertFalse(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}
	}
}
