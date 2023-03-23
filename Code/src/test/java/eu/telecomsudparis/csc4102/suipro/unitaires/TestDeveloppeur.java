// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestDeveloppeur {

	@Nested
	class Constructeur {

		@Test
		void Test1Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur(null, "nom", "prénom"));
		}

		@Test
		void Test1Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("", "nom", "prénom"));
		}

		@Test
		void Test2Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", null, "prenom"));
		}

		@Test
		void Test2Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "", "prenom"));
		}

		@Test
		void Test3Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", null));
		}

		@Test
		void Test3Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", ""));
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
			Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
			MockedPeriodeDeTravail periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
			Assertions.assertNotNull(developpeur);
			Assertions.assertTrue(developpeur.estActif());

			try {
				developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			} catch (OperationImpossible e) {
				Assertions.fail("Test impossible : impossible d'ajouter une période de travail");
			}

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estActif());

			// Assert that the method mettreALaCorbeille() of MockedPeriodeDeTravail has been called
			Assertions.assertEquals(1, periodeDeTravail.mettreALaCorbeilleCalledTimes);

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estActif());

			int size = Corbeille.getInstance().getElementsJetable(Developpeur.class).size();
			Assertions.assertEquals(1, size);
		}

	}

	@Nested
	class AjouterPeriodeDeTravail {

		IPeriodeDeTravail periodeDeTravail;
		Developpeur developpeur;

		@BeforeEach
		void setUp() {
			developpeur = new Developpeur("alias", "nom", "prénom");
			periodeDeTravail = new MockedPeriodeDeTravail(developpeur, true);
		}

		@AfterEach
		void tearDown() {
			periodeDeTravail = null;
			developpeur = null;
		}

		@Test
		void Test1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> developpeur.ajouterPeriodeDeTravail(null));
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
			Assertions.assertFalse(developpeur.estActif());
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
}
