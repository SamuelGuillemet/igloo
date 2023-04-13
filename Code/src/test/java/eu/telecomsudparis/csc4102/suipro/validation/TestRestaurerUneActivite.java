// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.apache.maven.shared.utils.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestRestaurerUneActivite {
    private SuiPro suiPro;
    private String id;
    private String nom;
    private String devAlias;
    private String devNom;
    private String devPrenom;
    private String tacheId;
    private String tacheNom;
    private Instant debut1;
    private Instant fin1;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        id = "act1";
        nom = "nom";
        suiPro.ajouterUneActivite(id, nom);

        devAlias = "dev1";
        devNom = "nom";
        devPrenom = "prenom";
        suiPro.ajouterUnDeveloppeur(devAlias, devNom, devPrenom);
        tacheId = "tacheId1";
        tacheNom = "tacheNom";
        debut1 = Instant.parse("2019-01-01T00:00:00Z");
        fin1 = Instant.parse("2019-01-02T00:00:00Z");
        suiPro.ajouterUneTache(tacheId, tacheNom, id);
        suiPro.ajouterUnePeriodeDeTravail(tacheId, id, devAlias, debut1, fin1);

        suiPro.restaurerUneActivite(id);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.restaurerUneActivite(null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.restaurerUneActivite(""));
    }

    @Test
    void Test2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.restaurerUneActivite("act2"));
    }

    @Test
    void Test3() throws Exception {
        suiPro.restaurerUneActivite(id);
        Assertions.assertTrue(suiPro.afficherLesActivites().contains(id));
        Assertions.assertTrue(suiPro.afficherLesActivites().contains(nom));

        suiPro.restaurerUneActivite(id);
        String result = suiPro.afficherLesActivites();
        Assertions.assertEquals(StringUtils.countMatches(result, id), 1);
    }

    @Test
    void Test4() throws Exception {
        suiPro.restaurerUneActivite(id);
        Assertions.assertTrue(suiPro.afficherLesTaches(id).contains(tacheId));
        Assertions.assertTrue(suiPro.afficherLesPeriodesDeTravailPourUneTache(id, tacheId).contains(debut1.toString()));
    }
}
