// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
	class TestContructeur {
		@Test
		void Test1Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", null, "prénom"));
		}

		@Test
		void Test1Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "", "prénom"));
		}

		@Test
		void Test2Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", null));
		}

		@Test
		void Test2Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", ""));
		}

		@Test
		void Test3Jeu1() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur(null, "nom", "prénom"));
		}

		@Test
		void Test3Jeu2() throws Exception {
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("", "nom", "prénom"));
		}

		@Test
		void Test5() throws Exception {
			Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
			Assertions.assertNotNull(developpeur);
			Assertions.assertEquals("alias", developpeur.getAlias());
			Assertions.assertEquals("nom", developpeur.getNom());
			Assertions.assertEquals("prénom", developpeur.getPrenom());
		}
	}
}
