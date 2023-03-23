// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestMettreUnDeveloppeurALaCorbeille {
    private SuiPro suiPro;
    private String alias;
    private String nom;
    private String prenom;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        alias = "dev1";
        nom = "nom";
        prenom = "prenom";
        suiPro.ajouterUnDeveloppeur(alias, nom, prenom);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        alias = null;
        nom = null;
        prenom = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille(null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille(""));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille("dev2"));
    }

    @Test
    void Test3Jeu1() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille(alias));
    }
}