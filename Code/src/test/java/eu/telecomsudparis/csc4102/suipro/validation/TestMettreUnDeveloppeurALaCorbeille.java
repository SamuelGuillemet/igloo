// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.codehaus.plexus.util.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestMettreUnDeveloppeurALaCorbeille {
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
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille(null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille(""));
    }

    @Test
    void Test2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUnDeveloppeurALaCorbeille("dev2"));
    }

    @Test
    void Test3() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);
        Assertions.assertTrue(suiPro.afficherLesDeveloppeurALaCorebille().contains(alias));
        Assertions.assertTrue(suiPro.afficherLesDeveloppeurALaCorebille().contains(nom));
        Assertions.assertTrue(suiPro.afficherLesDeveloppeurALaCorebille().contains(prenom));
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);

        String result = suiPro.afficherLesDeveloppeurALaCorebille();
        Assertions.assertEquals(StringUtils.countMatches(result, alias), 1);
    }

    @Test
    void Test4() throws Exception {
        suiPro.ajouterUnePeriodeDeTravail(tacheId, actId, alias, debut1, fin1);
        suiPro.mettreUnDeveloppeurALaCorbeille(alias);
        Assertions.assertTrue(suiPro.afficherLesPeriodesDeTravailALaCorbeille().contains(debut1.toString()));
    }
}
