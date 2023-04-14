// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUnePeriodeDeTravail {
    private SuiPro suiPro;
    private String idTache;
    private String idDev;
    private String idActivite;

    static private Instant debut;
    static private Instant fin;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        debut = Instant.now();
        fin = debut.plus(Duration.ofHours(1));
    }

    @BeforeEach
    void setUp() throws OperationImpossible {
        idTache = "tache1";
        idDev = "dev1";
        idActivite = "activite1";
        suiPro = new SuiPro("projetx");
        suiPro.ajouterUnDeveloppeur(idDev, "nom", "prenom");
        suiPro.ajouterUneActivite(idActivite, "nomActivite");
        suiPro.ajouterUneTache(idTache, "nomTache", idActivite);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void Test1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, null, fin));
    }

    @Test
    void Test2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, null));
    }

    @Test
    void Test3Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, fin, debut));
    }

    @Test
    void Test3Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, debut));
    }

    @Test
    void Test4Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, null, idDev, debut, fin));
    }

    @Test
    void Test4Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, "", idDev, debut, fin));
    }

    @Test
    void Test5() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, "activite2", idDev, debut, fin));
    }

    @Test
    void Test6() throws Exception {
        suiPro.mettreUneActiviteALaCorbeille(idActivite);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test7Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(null, idActivite, idDev, debut, fin));
    }

    @Test
    void Test7Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail("", idActivite, idDev, debut, fin));
    }

    @Test
    void Test8() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail("tache2", idActivite, idDev, debut, fin));
    }

    @Test
    void Test9() throws Exception {
        suiPro.mettreUneTacheALaCorbeille(idActivite, idTache);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test10Jeu1() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, (String) null, debut, fin));
    }

    @Test
    void Test10Jeu2() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, "", debut, fin));
    }

    @Test
    void Test11() throws Exception {
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, "dev2", debut, fin));
    }

    @Test
    void Test12() throws Exception {
        suiPro.mettreUnDeveloppeurALaCorbeille(idDev);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }

    @Test
    void Test14Puis13() throws Exception {
        suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin);
        Assertions.assertThrows(OperationImpossible.class,
                () -> suiPro.ajouterUnePeriodeDeTravail(idTache, idActivite, idDev, debut, fin));
    }
}
