// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;
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

        suiPro.getCorbeille().getElementsJetable(Activite.class).forEach(act -> {
            Assertions.assertEquals(id, act.getId());
            Assertions.assertEquals(nom, act.getNom());
        });
        suiPro.mettreUneActiviteALaCorbeille(id);

        int size = suiPro.getCorbeille().getElementsJetable(Activite.class).size();
        Assertions.assertEquals(1, size);

        Assertions.assertFalse(suiPro.getActivite(id).estEnFonctionnement());
    }
}
