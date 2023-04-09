// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAfficherUneTache {
    private SuiPro suiPro;
    private String devId;
    private String devId2;
    private String devNom;
    private String devPrenom;
    private String actId;
    private String atcNom;
    private String actId2;
    private String tacheId;
    private String tacheNom;
    private String tacheId2;
    private Instant debut1;
    private Instant fin1;
    private Instant debut2;
    private Instant fin2;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        devId = "dev1";
        devNom = "nom";
        devPrenom = "prenom";
        devId2 = "dev2";
        actId = "atc1";
        atcNom = "nom";
        actId2 = "atc2";
        tacheId = "tache1";
        tacheId2 = "tache2";
        tacheNom = "nom";
        debut1 = Instant.parse("2019-01-01T00:00:00Z");
        fin1 = Instant.parse("2019-01-02T00:00:00Z");
        debut2 = Instant.parse("2019-01-03T00:00:00Z");
        fin2 = Instant.parse("2019-01-04T00:00:00Z");
        suiPro.ajouterUnDeveloppeur(devId, devNom, devPrenom);
        suiPro.ajouterUnDeveloppeur(devId2, devNom, devPrenom);
        suiPro.ajouterUneActivite(actId, atcNom);
        suiPro.ajouterUneActivite(actId2, atcNom);
        suiPro.ajouterUneTache(tacheId, tacheNom, actId);
        suiPro.ajouterUneTache(tacheId2, tacheNom, actId2);
        suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, devId, debut1, fin1);
        suiPro.ajouterUnePeriodeDeTravail(tacheId2, actId2, devId2, debut2, fin2);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        devId = null;
        devNom = null;
        devPrenom = null;
        devId2 = null;
        actId = null;
        atcNom = null;
        actId2 = null;
        tacheId = null;
        tacheNom = null;
        tacheId2 = null;
        debut1 = null;
        fin1 = null;
        debut2 = null;
        fin2 = null;
    }

    @Test
    void TestTacheJeu1() throws Exception {
        String affichage = suiPro.afficherLesTaches(actId);
        Assertions.assertTrue(affichage.contains(tacheId));
        Assertions.assertTrue(affichage.contains(tacheNom));
        Assertions.assertTrue(affichage.contains(actId));
    }

    @Test
    void TestTacheJeu2() throws Exception {
        String affichage = suiPro.afficherLesTaches(actId);
        Assertions.assertFalse(affichage.contains(tacheId2));
    }

    @Test
    void TestTacheArgumentJeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesTaches(null));
    }

    @Test
    void TestTacheArgumentJeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesTaches(""));
    }

    @Test
    void TestTacheArgumentJeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesTaches("wrongID"));
    }
}