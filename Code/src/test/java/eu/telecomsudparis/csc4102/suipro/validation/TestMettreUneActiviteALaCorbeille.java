// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestMettreUneActiviteALaCorbeille {
    private SuiPro suiPro;
    private String id;
    private String nom;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        id = "act1";
        nom = "nom";
        suiPro.ajouterUneActivite(id, nom);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        id = null;
        nom = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneActiviteALaCorbeille(null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneActiviteALaCorbeille(""));
    }

    @Test
    void Test2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneActiviteALaCorbeille("act2"));
    }

    @Test
    void Test3() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(id);
        Assertions.assertTrue(suiPro.afficherLesActivitesALaCorbeille().contains(id));
        Assertions.assertTrue(suiPro.afficherLesActivitesALaCorbeille().contains(nom));
        suiPro.mettreUneActiviteALaCorbeille(id);
        // test idempotence
        String result = suiPro.afficherLesActivitesALaCorbeille();
        result.replace(id, "");
        Assertions.assertFalse(result.contains(id));

        Assertions.assertFalse(suiPro.afficherLesActivitesALaCorbeille().contains(id));
    }
}
