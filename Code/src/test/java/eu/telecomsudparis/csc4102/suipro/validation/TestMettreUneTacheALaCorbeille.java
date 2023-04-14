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

class TestMettreUneTacheALaCorbeille {
    private SuiPro suiPro;
    private String idAct;
    private String idTache;
    private String nom;
    private String devAlias;
    private String devNom;
    private String devPrenom;
    private Instant debut1;
    private Instant fin1;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        idAct = "act1";
        idTache = "tache1";
        nom = "nom";
        suiPro.ajouterUneActivite(idAct, nom);
        suiPro.ajouterUneTache(idTache, nom, idAct);

        devAlias = "dev1";
        devNom = "nom";
        devPrenom = "prenom";
        suiPro.ajouterUnDeveloppeur(devAlias, devNom, devPrenom);

        debut1 = Instant.parse("2019-01-01T00:00:00Z");
        fin1 = Instant.parse("2019-01-02T00:00:00Z");
        suiPro.ajouterUnePeriodeDeTravail(idTache, idAct, devAlias, debut1, fin1);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneTacheALaCorbeille(idAct, null));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneTacheALaCorbeille(idAct, ""));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.mettreUneTacheALaCorbeille(null, idTache));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
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
        Assertions.assertTrue(suiPro.afficherLesTachesALaCorbeille().contains(idTache));
        Assertions.assertTrue(suiPro.afficherLesTachesALaCorbeille().contains(nom));

        suiPro.mettreUneTacheALaCorbeille(idAct, idTache);
        String result = suiPro.afficherLesTachesALaCorbeille();
        Assertions.assertEquals(StringUtils.countMatches(result, idTache), 1);

        Assertions.assertFalse(suiPro.afficherLesTaches(idAct).contains(idTache));
    }

    @Test
    void Test6() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(idAct, idTache);
        Assertions.assertTrue(suiPro.afficherLesPeriodesDeTravailALaCorbeille().contains(debut1.toString()));
    }
}
