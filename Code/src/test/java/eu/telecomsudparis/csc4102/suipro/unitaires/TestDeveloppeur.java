// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Developpeur;

class TestDeveloppeur {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurDeveloppeurTest1Jeu1() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur(null, "nom", "prénom"));
	}

	@Test
	void constructeurDeveloppeurTest1Jeu2() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("", "nom", "prénom"));
	}

	@Test
	void constructeurDeveloppeurTest2Jeu1() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", null, "prenom"));
	}

	@Test
	void constructeurDeveloppeurTest2Jeu2() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "", "prenom"));
	}

	@Test
	void constructeurDeveloppeurTest3Jeu1() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", null));
	}

	@Test
	void constructeurDeveloppeurTest3Jeu2() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", ""));
	}

	void constructeurDeveloppeurTest4() throws Exception {
		Developpeur developpeur = new Developpeur("identifiant", "nom", "prénom");
		Assertions.assertNotNull(developpeur);
		Assertions.assertEquals("alias", developpeur.getAlias());
	}
}
