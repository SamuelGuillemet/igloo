// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAfficherUnDeveloppeur {
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
        devId = "devId1";
        devNom = "devNom";
        devPrenom = "devPrenom";
        devId2 = "devId2";
        actId = "atcId1";
        atcNom = "actNom";
        actId2 = "atcId2";
        tacheId = "tacheId1";
        tacheId2 = "tacheId2";
        tacheNom = "tacheNom";
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
    }

    @Test
    void TestDeveloppeur() throws Exception {
        String affichage = suiPro.afficherLesDeveloppeurs();
        Assertions.assertTrue(affichage.contains(devId));
        Assertions.assertTrue(affichage.contains(devNom));
        Assertions.assertTrue(affichage.contains(devPrenom));
        Assertions.assertTrue(affichage.contains(devId2));
    }

    @Test
    void TestDeveloppeurCorbeilleJeu1() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(devId);
        String affichage = suiPro.afficherLesDeveloppeurs();
        Assertions.assertFalse(affichage.contains(devId));
        Assertions.assertTrue(affichage.contains(devId2));
    }

    @Test
    void TestDeveloppeurCorbeilleJeu2() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(devId);
        String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
        Assertions.assertTrue(affichage.contains(devId));
        Assertions.assertFalse(affichage.contains(devId2));
    }
}