// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUnDeveloppeur {
	private SuiPro suiPro;
	private String identifiant;
	private String nom;
	private String prenom;

	@BeforeEach
	void setUp() throws Exception {
		suiPro = new SuiPro("projetx");
		identifiant = "developpeur1";
		nom = "nom1";
		prenom = "prenom1";
	}

	@AfterEach
	void tearDown() {
		suiPro = null;
	}

	@Test
	void Test1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, null, prenom));
	}

	@Test
	void Test1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, "", prenom));
	}

	@Test
	void Test2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, nom, null));
	}

	@Test
	void Test2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, nom, ""));
	}

	@Test
	void Test3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(null, nom, prenom));
	}

	@Test
	void Test3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur("", nom, prenom));
	}

	@Test
	void Test4() throws Exception {
		suiPro.ajouterUnDeveloppeur(identifiant, nom, prenom);
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, nom, prenom));
	}

	@Test
	void Test5() throws Exception {
		suiPro.ajouterUnDeveloppeur(identifiant, nom, prenom);
		Assertions.assertTrue(suiPro.afficherLesDeveloppeurs().contains(identifiant));
		Assertions.assertTrue(suiPro.afficherLesDeveloppeurs().contains(nom));
		Assertions.assertTrue(suiPro.afficherLesDeveloppeurs().contains(prenom));
	}

}
