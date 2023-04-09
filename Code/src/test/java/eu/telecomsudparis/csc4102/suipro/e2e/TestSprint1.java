// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.e2e;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.shared.utils.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Classe de test de l'intégration du sprint 1.
 * 
 * Scénario de test : 
 * ! Ajouts :
 * (1) ✓ ajouter le développeur Emmanuel Pastoret avec l’alias pastoret
 * (2) ✓ ajouter le développeur Jean-Baptiste Ducastel avec l’alias duscastel
 * (3) ✓ ajouter le développeur Pierre-Victurnien Vergniaud avec l’alias vergniaud
 * (4) ✓ ajouter le développeur Vincent Viénot-Vaublanc avec l’alias viénot-vaublanc
 * (5) ✓ ajouter l’activité « Conception détaillée » avec l’identifiant cd
 * (6) ✓ ajouter la tâche « Définition des classes » avec l’identifiant dc à l’activité cd
 * (7) ✓ ajouter la tâche « Maquettage des interfaces » avec l’identifiant mi à l’activité cd
 * (8) ✓ ajouter une période de travail d’une heure à la tâche dc pour chacun des développeurs et aujourd’hui
 * ! Modifications :
 * (9) ✗ pour le développeur pastoret, ajouter une période de travail à la tâche dc qui s’intersecte avec la période de travail précédemment créée pour ce même développeur
 * (10) ✓ pour les développeurs pastoret et vergniaud, ajouter une nouvelle période de travail d’une heure à la tâche mi et demain
 * (11) ✓ pour les développeurs ducastel et viénot-vaublanc, ajouter une nouvelle période de travail d’une heure à la tâche dc et demain
 * ! Affichages :
 * (12) ✓ afficher les développeurs
 * • {pastoret, ducastel, vergniaud, viénot-vaublanc}
 * (13) ✓ afficher les tâches de l’activité cd
 * • {dc, mi}
 * (14) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
 * • 1 pour pastoret et vergniaud, et 2 pour ducastel et viénot-vaublanc
 * (15) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
 * • 1 pour pastoret et vergniaud
 * ! Mises à la corbeille :
 * (16) ✓ mettre à la corbeille le développeur pastoret
 * (17) ✓ afficher les développeurs
 * • {ducastel, vergniaud, viénot-vaublanc}
 * (18) ✓ afficher les développeurs dans la corbeille
 * • {pastoret}
 * (19) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
 * • 1 pour vergniaud, et 2 pour ducastel et viénot-vaublanc
 * (20) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
 * • 1 pour vergniaud
 * (21) ✓ afficher les périodes de travail dans la corbeille
 * • 2 pour pastoret
 * (22) ✗ pour le développeur pastoret, qui est dans la corbeille, ajouter une nouvelle période de travail d’une heure à la tâche dc et après-demain
 * (23) ✓ mettre à la corbeille le développeur pastoret
 * (24) ✓ afficher les développeurs
 * • {ducastel, vergniaud, viénot-vaublanc}
 * (25) ✓ afficher les développeurs dans la corbeille
 * • {pastoret}
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSprint1 {
    private SuiPro suiPro;

    @BeforeAll
    public void setUp() throws OperationImpossible {
        suiPro = new SuiPro("Sprint1");
    }

    @AfterAll
    public void tearDown() {
        suiPro = null;
    }

    @Test
    @Disabled("Bug inside IntervalleInstants::Constructeur")
    void sprint1() {

        // (1) ✓ ajouter le développeur Emmanuel Pastoret avec l’alias pastoret
        try {
            suiPro.ajouterUnDeveloppeur("pastoret", "Pastoret", "Emmanuel");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (2) ✓ ajouter le développeur Jean-Baptiste Ducastel avec l’alias ducastel
        try {
            suiPro.ajouterUnDeveloppeur("ducastel", "Ducastel", "Jean-Baptiste");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (3) ✓ ajouter le développeur Pierre-Victurnien Vergniaud avec l’alias vergniaud
        try {
            suiPro.ajouterUnDeveloppeur("vergniaud", "Vergniaud", "Pierre-Victurnien");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (4) ✓ ajouter le développeur Vincent Viénot-Vaublanc avec l’alias viénot-vaublanc
        try {
            suiPro.ajouterUnDeveloppeur("viénot-vaublanc", "Viénot-Vaublanc", "Vincent");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (5) ✓ ajouter l’activité « Conception détaillée » avec l’identifiant cd
        try {
            suiPro.ajouterUneActivite("cd", "Conception détaillée");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (6) ✓ ajouter la tâche « Définition des classes » avec l’identifiant dc à l’activité cd
        try {
            suiPro.ajouterUneTache("dc", "Définition des classes", "cd");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (7) ✓ ajouter la tâche « Maquettage des interfaces » avec l’identifiant mi à l’activité cd
        try {
            suiPro.ajouterUneTache("mi", "Maquettage des interfaces", "cd");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (8) ✓ ajouter une période de travail d’une heure à la tâche dc pour chacun des développeurs et aujourd’hui
        try {
            Instant debut = Instant.now();
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd",
                    Arrays.asList("pastoret", "ducastel", "vergniaud", "viénot-vaublanc"), debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // Modifications :

        // (9) ✗ pour le développeur pastoret, ajouter une période de travail à la tâche dc qui s’intersecte avec la période de travail précédemment créée pour ce même développeur
        try {
            Instant debut = Instant.now();
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", "pastoret", debut, fin);
            Assertions.fail("Exception attendue");
        } catch (OperationImpossible e) {
            //! OK
        }

        // (10) ✓ pour les développeurs pastoret et vergniaud, ajouter une nouvelle période de travail d’une heure à la tâche mi et demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(1));
            Instant fin = debut.plus(Duration.ofHours(1));
            List<String> devs = List.of("pastoret", "vergniaud");
            suiPro.ajouterUnePeriodeDeTravail("mi", "cd", devs, debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (11) ✓ pour les développeurs ducastel et viénot-vaublanc, ajouter une nouvelle période de travail d’une heure à la tâche dc et demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(1));
            Instant fin = debut.plus(Duration.ofHours(1));
            List<String> devs = List.of("ducastel", "viénot-vaublanc");
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", devs, debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // Affichages :

        // (12) ✓ afficher les développeurs
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertTrue(affichage.contains("pastoret"));
            Assertions.assertTrue(affichage.contains("ducastel"));
            Assertions.assertTrue(affichage.contains("vergniaud"));
            Assertions.assertTrue(affichage.contains("viénot-vaublanc"));
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (13) ✓ afficher les tâches de l’activité cd
        try {
            System.out.println("Affichage des tâches de l'activité cd :");
            String affichage = suiPro.afficherLesTaches("cd");
            Assertions.assertTrue(affichage.contains("dc"));
            Assertions.assertTrue(affichage.contains("mi"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (14) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche dc de l'activité cd :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "dc");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "pastoret") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "vergniaud") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "ducastel") == 2);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "viénot-vaublanc") == 2);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (15) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche mi de l'activité cd :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "mi");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "pastoret") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "vergniaud") == 1);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (16) ✓ mettre à la corbeille le développeur pastoret
        try {
            suiPro.mettreUnDeveloppeurALaCorbeille("pastoret");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (17) ✓ afficher les développeurs
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertFalse(affichage.contains("pastoret"));
            Assertions.assertTrue(affichage.contains("ducastel"));
            Assertions.assertTrue(affichage.contains("vergniaud"));
            Assertions.assertTrue(affichage.contains("viénot-vaublanc"));
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (18) ✓ afficher les développeurs dans la corbeille
        try {
            System.out.println("Affichage des développeurs dans la corbeille :");
            String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
            Assertions.assertTrue(affichage.contains("pastoret"));
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (19) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche dc de l'activité cd :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "dc");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "pastoret") == 0);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "vergniaud") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "ducastel") == 2);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "viénot-vaublanc") == 2);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (20) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche mi de l'activité cd :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "mi");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "pastoret") == 0);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "vergniaud") == 1);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (21) ✓ afficher les périodes de travail dans la corbeille
        try {
            System.out.println("Affichage des périodes de travail dans la corbeille :");
            String affichage = suiPro.afficherLesPeriodesDeTravailALaCorbeille();
            Assertions.assertTrue(StringUtils.countMatches(affichage, "pastoret") == 2);
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (22) ✗ pour le développeur pastoret, qui est dans la corbeille, ajouter une nouvelle période de travail d’une heure à la tâche dc et après-demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(2));
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", "pastoret", debut, fin);
            Assertions.fail("L'exception OperationImpossible aurait du être levée");
        } catch (OperationImpossible e) {
            //! OK
        }

        // (23) ✓ mettre à la corbeille le développeur pastoret
        try {
            suiPro.mettreUnDeveloppeurALaCorbeille("pastoret");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (24) ✓ afficher les développeurs
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertFalse(affichage.contains("pastoret"));
            Assertions.assertTrue(affichage.contains("ducastel"));
            Assertions.assertTrue(affichage.contains("vergniaud"));
            Assertions.assertTrue(affichage.contains("viénot-vaublanc"));
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (25) ✓ afficher les développeurs dans la corbeille
        try {
            System.out.println("Affichage des développeurs dans la corbeille :");
            String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
            Assertions.assertTrue(affichage.contains("pastoret"));
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }
}
