// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAfficher {
    private SuiPro suiPro;
    private String devId;
    private String devNom;
    private String devPrenom;
    private String atcId;
    private String atcNom;
    private String tacheId;
    private String tacheNom;
    private Instant debut1;
    private Instant fin1;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        devId = "dev1";
        devNom = "nom";
        devPrenom = "prenom";
        atcId = "atc1";
        atcNom = "nom";
        tacheId = "tache1";
        tacheNom = "nom";
        debut1 = Instant.parse("2019-01-01T00:00:00Z");
        fin1 = Instant.parse("2019-01-02T00:00:00Z");
        suiPro.ajouterUnDeveloppeur(devId, devNom, devPrenom);
        suiPro.ajouterUneActivite(atcId, atcNom);
        suiPro.ajouterUneTache(tacheId, tacheNom, atcId);
        suiPro.ajouterUnePeriodeDeTravail(tacheId, atcId, devId, debut1, fin1);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        devId = null;
        devNom = null;
        devPrenom = null;
        atcId = null;
        atcNom = null;
        tacheId = null;
        tacheNom = null;
    }

    @Test
    void TestDeveloppeur() throws Exception {
        String affichage = suiPro.afficherLesDeveloppeurs();
        Assertions.assertTrue(affichage.contains(devId));
        Assertions.assertTrue(affichage.contains(devNom));
        Assertions.assertTrue(affichage.contains(devPrenom));
    }

    @Test
    void TestActivite() throws Exception {
        String affichage = suiPro.afficherLesActivites();
        Assertions.assertTrue(affichage.contains(atcId));
        Assertions.assertTrue(affichage.contains(atcNom));
    }

    @Test
    void TestTache() throws Exception {
        String affichage = suiPro.afficherLesTaches(atcId);
        Assertions.assertTrue(affichage.contains(tacheId));
        Assertions.assertTrue(affichage.contains(tacheNom));
        Assertions.assertTrue(affichage.contains(atcId));
    }

    @Test
    void TestPeriodeDeTravail() throws Exception {
        String affichage = suiPro.afficherLesPeriodesDeTravailPourUnDeveloppeur(devId);
        Assertions.assertTrue(affichage.contains(tacheId));
        Assertions.assertTrue(affichage.contains(atcId));
        Assertions.assertTrue(affichage.contains(devId));
        Assertions.assertTrue(affichage.contains(debut1.toString()));
        Assertions.assertTrue(affichage.contains(fin1.toString()));
    }

    @Test
    void TestPeriodeDeTravailPourUneTache() throws Exception {
        String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache(atcId, tacheId);
        Assertions.assertTrue(affichage.contains(tacheId));
        Assertions.assertTrue(affichage.contains(atcId));
        Assertions.assertTrue(affichage.contains(devId));
        Assertions.assertTrue(affichage.contains(debut1.toString()));
        Assertions.assertTrue(affichage.contains(fin1.toString()));
    }

    @Test
    void TestTacheArgument1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesTaches(null));
    }

    @Test
    void TestTacheArgument2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesTaches(""));
    }

    @Test
    void TestTacheArgument3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesTaches("wrongID"));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument1() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache(null, tacheId));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument2() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache("", tacheId));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache("wrongID", tacheId));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument4() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache(atcId, null));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument5() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache(atcId, ""));
    }

    @Test
    void TestPeriodeDeTravailPourUneTacheArgument6() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.afficherLesPeriodesDeTravailPourUneTache(atcId, "wrongID"));
    }
}
