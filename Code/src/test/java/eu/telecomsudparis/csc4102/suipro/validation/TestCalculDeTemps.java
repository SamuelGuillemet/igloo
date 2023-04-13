// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCalculDeTemps {
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
    private String labId;
    private String labNom;
    private String labId2;
    private String labNom2;

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

        labId = "idLabel1";
        labNom = "nomLabel1";
        suiPro.creerLabel(labNom, labId);
        suiPro.ajouterLabelAActivite(labId, actId);

        labId2 = "idLabel2";
        labNom2 = "nomLabel2";
        suiPro.creerLabel(labNom2, labId2);
        suiPro.ajouterLabelAActivite(labId2, actId2);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1() throws OperationImpossible {
        Assertions.assertEquals(48, suiPro.calculerTempsDeTravailProjet());
    }

    @Test
    void Test2() throws OperationImpossible {
        Assertions.assertEquals(24, suiPro.calculerTempsDeTravailActivite(actId));
    }

    @Test
    void Test3() throws OperationImpossible {
        Assertions.assertEquals(24, suiPro.calculerTempsDeTravailTache(actId, tacheId));
    }

    @Test
    void Test4() throws OperationImpossible {
        Assertions.assertEquals(24, suiPro.calculerTempsDeTravailDeveloppeur(devId));
    }

    @Test
    void Test5() throws OperationImpossible {
        Assertions.assertEquals(24, suiPro.calculerTempsDeTravailProjetHorsLabel(labId));
    }

    @Test
    void Test6() throws OperationImpossible {
        Assertions.assertEquals(0, suiPro.calculerTempsDeTravailProjetHorsLabels(List.of(labId, labId2)));
    }
}
