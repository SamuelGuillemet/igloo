// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.Corbeille;

class TestPeriodeDeTravail {
	static Instant debut;
	static Instant fin;
	static Instant mauvaiseFin;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		debut = Instant.now();
		fin = debut.plusSeconds(10);
		mauvaiseFin = debut.minusSeconds(10);
	}

	@Nested
	class Contructeur {

		IDeveloppeur developpeur;
		ITache tache;

		@BeforeEach
		void setUp() {
			developpeur = new MockedDeveloppeur(true, false);
			tache = new MockedTache(true, false);
		}

		@AfterEach
		void tearDown() {
			developpeur = null;
			tache = null;
		}

		@Test
		void Test1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(null, fin, tache, developpeur));
		}

		@Test
		void Test2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, null, tache, developpeur));
		}

		@Test
		void Test3() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, null, developpeur));
		}

		@Test
		void Test4() throws Exception {
			tache = new MockedTache(false, false);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}

		@Test
		void Test5() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, tache, null));
		}

		@Test
		void Test6() throws Exception {
			developpeur = new MockedDeveloppeur(false, false);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}

		@Test
		void Test7Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, debut, tache, developpeur));
		}

		@Test
		void Test7Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, mauvaiseFin, tache, developpeur));
		}

		@Test
		void Test8() throws Exception {
			developpeur = new MockedDeveloppeur(true, true);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}

		@Test
		void Test9() throws Exception {
			tache = new MockedTache(true, true);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}

		@Test
		void Test10() throws Exception {
			PeriodeDeTravail PeriodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur);
			Assertions.assertEquals(debut, PeriodeDeTravail.getIntervalle().getInstantDebut());
			Assertions.assertEquals(fin, PeriodeDeTravail.getIntervalle().getInstantFin());
			Assertions.assertEquals(tache, PeriodeDeTravail.getTache());
			Assertions.assertEquals(developpeur, PeriodeDeTravail.getDeveloppeur());
			Assertions.assertTrue(PeriodeDeTravail.estActif());
		}
	}

	@Nested
	class MettreALaCorbeille {
		@BeforeEach
		void setUp() {
			Corbeille.getInstance().viderLaCorbeille();
		}

		@AfterAll
		static void tearDownAfterClass() throws Exception {
			Corbeille.getInstance().viderLaCorbeille();
		}

		@Test
		void Test1() throws Exception {
			ITache tache = new MockedTache(true, false);
			IDeveloppeur developpeur = new MockedDeveloppeur(true, false);

			PeriodeDeTravail periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur);
			Assertions.assertNotNull(periodeDeTravail);
			Assertions.assertTrue(periodeDeTravail.estActif());

			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estActif());

			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estActif());

			int size = Corbeille.getInstance().getElementsJetable(PeriodeDeTravail.class).size();
			Assertions.assertEquals(1, size);
		}
	}

}
