// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUneActivite {
    private SuiPro suiPro;
    private String identifiant;
    private String nom;

    @BeforeEach
    void setUp() throws Exception {
        suiPro = new SuiPro("projetx");
        identifiant = "activite1";
        nom = "nom1";
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        identifiant = null;
        nom = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneActivite(identifiant, null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneActivite(identifiant, ""));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneActivite(null, nom));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneActivite("", nom));
    }

    @Test
    void Test3() throws Exception {
        suiPro.ajouterUneActivite(identifiant, nom);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneActivite(identifiant, nom));
    }

    @Test
    void Test4() throws Exception {
        suiPro.ajouterUneActivite(identifiant, nom);
        Assertions.assertEquals(suiPro.getActivite(identifiant).getId(), identifiant);
        Assertions.assertEquals(suiPro.getActivite(identifiant).getNom(), nom);
    }

}
