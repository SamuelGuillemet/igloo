// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUneTache {
    private SuiPro suiPro;
    private String idTache;
    private String nomTache;
    private String idActivite;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        suiPro.ajouterUneActivite("activite1", "activite1");
        idActivite = "activite1";
        idTache = "tache1";
        nomTache = "nomTache1";
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        idTache = null;
        nomTache = null;
        idActivite = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, null, idActivite));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, "", idActivite));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(null, nomTache, idActivite));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache("", nomTache, idActivite));
    }

    @Test
    void Test3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, nomTache, null));
    }

    @Test
    void Test3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, nomTache, ""));
    }

    @Test
    void Test4() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, nomTache, "wrongID"));
    }

    @Test
    void Test5() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(idActivite);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, nomTache, idActivite));
    }

    @Test
    void Test6() throws Exception {
        suiPro.ajouterUneTache(idTache, nomTache, idActivite);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUneTache(idTache, nomTache, idActivite));
    }

    // @Test
    // void Test7() throws Exception {
    // suiPro.ajouterUneTache(idTache, nomTache, idActivite);
    // Assertions.assertEquals(suiPro.getTache(idTache).getNom(), nomTache);
    // Assertions.assertEquals(suiPro.getTache(idTache).getId(), idTache);
    // Assertions.assertEquals(suiPro.getTache(idTache).getIdActivite(),
    // idActivite);
    // }
}
