// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.IntervalleInstants;
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
			Assertions.assertNotNull(developpeur);

			try {
				developpeur.ajouterPeriodeDeTravail(new MockedPeriodeDeTravail(developpeur));
			} catch (OperationImpossible e) {
				Assertions.fail("Test impossible : impossible d'ajouter une période de travail");
			}

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estActif());

			// Assert that the method mettreALaCorbeille() of MockedPeriodeDeTravail has been called
			Assertions.assertFalse(((MockedPeriodeDeTravail) developpeur.getPeriodesDeTravail().get(0)).estActif());

			developpeur.mettreALaCorbeille();
			Assertions.assertFalse(developpeur.estActif());
		}

	}

	@Nested
	class AjouterPeriodeDeTravail {

		IPeriodeDeTravail periodeDeTravail;
		Developpeur developpeur;

		@BeforeEach
		void setUp() {
			developpeur = new Developpeur("alias", "nom", "prénom");
			periodeDeTravail = new MockedPeriodeDeTravail(developpeur);
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
			periodeDeTravail = new MockedPeriodeDeTravail(new Developpeur("other", "other", "other"));

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
			developpeur.ajouterPeriodeDeTravail(periodeDeTravail);
			Assertions.assertEquals(1, developpeur.getPeriodesDeTravail().size());
			Assertions.assertTrue(developpeur.getPeriodesDeTravail().contains(periodeDeTravail));
		}
	}
}

class MockedPeriodeDeTravail implements IPeriodeDeTravail {

	private IDeveloppeur developpeur;
	private boolean estActif;

	public MockedPeriodeDeTravail(IDeveloppeur developpeur, boolean estActif) {
		this.developpeur = developpeur;
		this.estActif = estActif;
	}

	public MockedPeriodeDeTravail(IDeveloppeur developpeur) {
		this(developpeur, true);
	}

	@Override
	public boolean estActif() {
		return estActif;
	}

	@Override
	public void mettreALaCorbeille() {
		estActif = false;
	}

	@Override
	public boolean invariant() {
		throw new UnsupportedOperationException("Unimplemented method 'invariant'");
	}

	@Override
	public IntervalleInstants getIntervalle() {
		return new IntervalleInstants(Instant.MIN, Instant.MAX);
	}

	@Override
	public IDeveloppeur getDeveloppeur() {
		return developpeur;
	}

	@Override
	public ITache getTache() {
		throw new UnsupportedOperationException("Unimplemented method 'getTache'");
	}

}