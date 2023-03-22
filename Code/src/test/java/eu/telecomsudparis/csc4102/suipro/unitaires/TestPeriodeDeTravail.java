// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.Activite;

class TestPeriodeDeTravail {

	Instant debut;
	Instant fin;
	Instant mauvaiseFin;
	Developpeur developpeur;
	Tache tache;
	Activite activite;

	@BeforeEach
	void setUp() throws OperationImpossible {
		debut = Instant.now();
		fin = debut.plusSeconds(10);
		mauvaiseFin = debut.minusSeconds(10);
		developpeur = new Developpeur("alias", "nomDev", "prénomDev");
		activite = new Activite("nomAct", "idAct");
		tache = new Tache("nomTache", "idTache", activite);
	}

	@AfterEach
	void tearDown() {
	}

	@Nested
	class TestContructeur {
		@Test
		void Test1Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(null, fin, tache, developpeur));
		}

		@Test
		void Test1Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, null, tache, developpeur));
		}

		@Test
		void Test1Jeu3() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, null, developpeur));
		}

		@Test
		void Test1Jeu4() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, tache, null));
		}

		@Test
		void Test3Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, mauvaiseFin, tache, developpeur));
		}

		@Test
		void Test4Jeu1() throws Exception {
			developpeur.mettreALaCorbeille();
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}

		@Test
		void Test4Jeu2() throws Exception {
			tache.mettreALaCorbeille();
			Assertions.assertThrows(IllegalArgumentException.class,
					() -> new PeriodeDeTravail(debut, fin, tache, developpeur));
		}
	}

	@Test
	void TestMettreALaCorbeille() throws Exception {
		PeriodeDeTravail PeriodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur);
		Assertions.assertTrue(PeriodeDeTravail.estActif());
		PeriodeDeTravail.mettreALaCorbeille();
		Assertions.assertFalse(PeriodeDeTravail.estActif());
	}

}
