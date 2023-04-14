// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterLabelATache {
    private SuiPro suiPro;
    private String alias;
    private String nom;
    private String prenom;
    private String actId;
    private String atcNom;
    private String tacheId;
    private String tacheNom;
    private Instant debut1;
    private Instant fin1;
    private String labId;
    private String labNom;
    private String labId2;
    private String labNom2;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        alias = "dev1";
        nom = "nom";
        prenom = "prenom";
        suiPro.ajouterUnDeveloppeur(alias, nom, prenom);

        actId = "atcId1";
        atcNom = "actNom";
        tacheId = "tacheId1";
        tacheNom = "tacheNom";
        debut1 = Instant.parse("2019-01-01T00:00:00Z");
        fin1 = Instant.parse("2019-01-02T00:00:00Z");
        suiPro.ajouterUneActivite(actId, atcNom);
        suiPro.ajouterUneTache(tacheId, tacheNom, actId);
        suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, alias, debut1, fin1);

        suiPro.mettreUnDeveloppeurALaCorbeille(alias);

        labId = "idlabel1";
        labNom = "nomlabel1";
        labId2 = "idlabel2";
        labNom2 = "nomlabel2";

        suiPro.creerLabel(labNom, labId);
        suiPro.creerLabel(labNom2, labId2);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(null, actId, tacheId));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache("", actId, tacheId));
    }

    @Test
    void Test1Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache("idInexistant", actId, tacheId));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, null, tacheId));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, "", tacheId));
    }

    @Test
    void Test2Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, "idInexistant", tacheId));
    }

    @Test
    void Test3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, actId, null));
    }

    @Test
    void Test3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, actId, ""));
    }

    @Test
    void Test3Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterLabelATache(labId, actId, "idInexistant"));
    }
}
