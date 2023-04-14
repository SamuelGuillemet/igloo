// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestViderLaCorbeille {
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
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesDeveloppeurs().contains(alias));
        Assertions.assertFalse(suiPro.afficherLesDeveloppeurALaCorebille().contains(alias));
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, alias, debut1, fin1));
    }

    @Test
    void Test1Jeu2() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailALaCorbeille().contains(alias));
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailPourUneTache(actId, tacheId).contains(alias));
    }

    @Test
    void Test2Jeu1() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(actId);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesActivites().contains(actId));
        Assertions.assertFalse(suiPro.afficherLesActivitesALaCorbeille().contains(actId));
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, alias, debut1, fin1));
    }

    @Test
    void Test2Jeu2() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(actId);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailALaCorbeille().contains(tacheId));
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailPourUnDeveloppeur(alias).contains(alias));
    }

    @Test
    void Test3Jeu1() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(actId, tacheId);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesTaches(actId).contains(tacheId));
        Assertions.assertFalse(suiPro.afficherLesTachesALaCorbeille().contains(tacheId));
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, alias, debut1, fin1));
    }

    @Test
    void Test3Jeu2() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(actId, tacheId);
        suiPro.viderLaCorbeille();
        Thread.sleep(50);
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailALaCorbeille().contains(tacheId));
        Assertions.assertFalse(suiPro.afficherLesPeriodesDeTravailPourUnDeveloppeur(alias).contains(alias));
    }
}
