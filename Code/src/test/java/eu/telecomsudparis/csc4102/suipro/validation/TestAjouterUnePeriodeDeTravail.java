// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUneTache {
    private SuiPro suiPro;
    private String idTache;
    private String idDev;
    private String idActivite;

    private Instant debut;
    private Instant fin;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projetx");
        idTache = "tache1";
        idDev = "dev1";
        idActivite = "activite1";
        suiPro.ajouterUnDeveloppeur(idDev, "nom", "prenom");
        suiPro.ajouterUneActivite(idActivite, "nomActivite");
        suiPro.ajouterUneTache(idTache, "nomTache", idActivite);
        debut = Instant.now();
        fin = debut.plusSeconds(3600);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        idTache = null;
        idDev = null;
        idActivite = null;
    }

    @Test
    void Test1Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(null, idActivite, idDev, debut, fin));
    }

    @Test
    void Test1Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail("", idActivite, idDev, debut, fin));
    }

    @Test
    void Test2Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, null, idDev, debut, fin));
    }

    @Test
    void Test2Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, "", idDev, debut, fin));
    }

    @Test
    void Test3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, "", debut, fin));
    }

    @Test
    void Test4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, null, fin));
    }

    @Test
    void Test4Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, null));
    }

    @Test
    void Test5Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, fin, debut));
    }

    @Test
    void Test5Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, debut));
    }

    @Test
    void Test6Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail("tache2", idActivite, idDev, debut, fin));
    }

    @Test
    void Test6Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, "activite2", idDev, debut, fin));
    }

    @Test
    void Test6Jeu3() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, "dev2", debut, fin));
    }

    @Test
    void Test7Jeu1() throws Exception {
        Assertions.assertDoesNotThrow(() -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test7Jeu2() throws Exception {
        Assertions.assertDoesNotThrow(() -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test8Jeu1() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(idDev);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test8Jeu2() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(idActivite);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test8Jeu3() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(idActivite, idTache);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }
}

// missing test for multiple developers