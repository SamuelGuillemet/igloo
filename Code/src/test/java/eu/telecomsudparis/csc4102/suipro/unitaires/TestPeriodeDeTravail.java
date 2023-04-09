// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedCorbeille;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.mocks.MockedTache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.ITache;

class TestPeriodeDeTravail {
	static Instant debut;
	static Instant fin;
	static Instant mauvaiseFin;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		debut = Instant.now();
		fin = debut.plus(Duration.ofHours(1));
		mauvaiseFin = debut.minusSeconds(10);
	}

	@Nested
	class Contructeur {

		IDeveloppeur developpeur;
		ITache tache;
		Corbeille corbeille;

		@BeforeEach
		void setUp() throws Exception {
			corbeille = new Corbeille();
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
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(null, fin, tache, developpeur, corbeille));
		}

		@Test
		void Test2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, null, tache, developpeur, corbeille));
		}

		@Test
		void Test3() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, null, developpeur, corbeille));
		}

		@Test
		void Test4() throws Exception {
			tache = new MockedTache(false, false);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille));
		}

		@Test
		void Test5() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, null, corbeille));
		}

		@Test
		void Test6() throws Exception {
			developpeur = new MockedDeveloppeur(false, false);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille));
		}

		@Test
		void Test7Jeu1() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, debut, tache, developpeur, corbeille));
		}

		@Test
		void Test7Jeu2() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, mauvaiseFin, tache, developpeur, corbeille));
		}

		@Test
		void Test8() throws Exception {
			developpeur = new MockedDeveloppeur(true, true);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille));
		}

		@Test
		void Test9() throws Exception {
			tache = new MockedTache(true, true);
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille));
		}

		@Test
		void Test10() throws Exception {
			Assertions.assertThrows(OperationImpossible.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur, null));
		}

		@Test
		void Test11() throws Exception {
			PeriodeDeTravail PeriodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille);
			Assertions.assertEquals(debut, PeriodeDeTravail.getIntervalle().getInstantDebut());
			Assertions.assertEquals(fin, PeriodeDeTravail.getIntervalle().getInstantFin());
			Assertions.assertEquals(tache, PeriodeDeTravail.getTache());
			Assertions.assertEquals(developpeur, PeriodeDeTravail.getDeveloppeur());
			Assertions.assertTrue(PeriodeDeTravail.estEnFonctionnement());
		}
	}

	@Nested
	class MettreALaCorbeille {
		@Test
		void Test1() throws Exception {
			MockedCorbeille corbeille = new MockedCorbeille();
			ITache tache = new MockedTache(true);
			IDeveloppeur developpeur = new MockedDeveloppeur(true);

			PeriodeDeTravail periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille);
			Assertions.assertNotNull(periodeDeTravail);
			Assertions.assertTrue(periodeDeTravail.estEnFonctionnement());

			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());

			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());

			int size = corbeille.getNbAjout(periodeDeTravail);
			Assertions.assertEquals(1, size);
		}
	}

	@Nested
	class Restaurer {
		MockedCorbeille corbeille;
		MockedTache tache;
		MockedDeveloppeur developpeur;
		PeriodeDeTravail periodeDeTravail;

		@BeforeEach
		void setUp() throws Exception {
			corbeille = new MockedCorbeille();
			tache = new MockedTache(true);
			developpeur = new MockedDeveloppeur(true);

			periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille);

			Assertions.assertNotNull(periodeDeTravail);
			Assertions.assertTrue(periodeDeTravail.estEnFonctionnement());

			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());
		}

		@AfterEach
		void tearDown() {
			corbeille = null;
			tache = null;
			developpeur = null;
			periodeDeTravail = null;
		}

		@Test
		void Test1() throws Exception {
			tache.setEnFonctionnement(false);
			periodeDeTravail.restaurer();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());
		}

		@Test
		void Test2() throws Exception {
			developpeur.setEnFonctionnement(false);
			periodeDeTravail.restaurer();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());
		}

		@Test
		void Test3() throws Exception {
			periodeDeTravail.restaurer();
			Assertions.assertTrue(periodeDeTravail.estEnFonctionnement());

			periodeDeTravail.restaurer();
			Assertions.assertTrue(periodeDeTravail.estEnFonctionnement());

			int size = corbeille.getNbSuppression(periodeDeTravail);
			Assertions.assertEquals(1, size);
		}
	}

	@Nested
	class CalculerTempsDeTravail {

		MockedCorbeille corbeille;
		MockedTache tache;
		MockedDeveloppeur developpeur;
		PeriodeDeTravail periodeDeTravail;

		@BeforeEach
		void setUp() throws Exception {
			corbeille = new MockedCorbeille();
			tache = new MockedTache(true);
			developpeur = new MockedDeveloppeur(true);

			periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille);

			Assertions.assertNotNull(periodeDeTravail);
			Assertions.assertTrue(periodeDeTravail.estEnFonctionnement());
		}

		@AfterEach
		void tearDown() {
			corbeille = null;
			tache = null;
			developpeur = null;
			periodeDeTravail = null;
		}

		@Test
		void Test1() throws Exception {
			periodeDeTravail.mettreALaCorbeille();
			Assertions.assertFalse(periodeDeTravail.estEnFonctionnement());

			double duree = periodeDeTravail.calculerTempsDeTravail();
			Assertions.assertEquals(0.0, duree);
		}

		@Test
		void Test2() throws Exception {
			double duree = periodeDeTravail.calculerTempsDeTravail();
			Assertions.assertEquals(1.0, duree);
		}
	}

}
