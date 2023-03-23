// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Corbeille;
import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestMettreUneTacheALaCorbeille {
    private SuiPro suiPro;
    private String idAct;
    private String idTache;
    private String nom;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        idAct = "act1";
        idTache = "tache1";
        nom = "nom";
        suiPro.ajouterUneActivite(idAct, nom);
        suiPro.ajouterUneTache(idTache, nom, idAct);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        idAct = null;
        idTache = null;
        nom = null;
        Corbeille.getInstance().viderLaCorbeille();
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.mettreUneTacheALaCorbeille(idAct, null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.mettreUneTacheALaCorbeille(idAct, ""));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.mettreUneTacheALaCorbeille(null, idTache));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.mettreUneTacheALaCorbeille("", idTache));
    }

    @Test
    void Test3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneTacheALaCorbeille("act2", idTache));
    }

    @Test
    void Test4() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneTacheALaCorbeille(idAct, "tache2"));
    }

    @Test
    void Test5() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(idAct, idTache);
        Corbeille.getInstance().getElementsJetable(Tache.class).forEach(tache -> {
            Assertions.assertEquals(idTache, tache.getId());
            Assertions.assertEquals(nom, tache.getNom());
        });
        suiPro.mettreUneTacheALaCorbeille(idAct, idTache);
        int size = Corbeille.getInstance().getElementsJetable(Tache.class).size();

        Assertions.assertEquals(1, size);
        Assertions.assertFalse(suiPro.getActivite(idAct).getTache(idTache).estActif());
    }
}
