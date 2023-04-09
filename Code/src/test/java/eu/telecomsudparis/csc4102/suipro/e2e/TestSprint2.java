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
 * Classe de test de l'intégration du sprint 2.
 * 
 * Scénario de test :
 * ! Ajouts :
 * (1) ✓ ajouter la développeuse Madeleine Braun avec l’alias braun
 * (2) ✓ ajouter la développeuse Carole Bureau-Bonnard avec l’alias bureau-bonnard
 * (3) ✓ ajouter la développeuse Germaine Peyroles avec l’alias peyroles
 * (4) ✓ ajouter la développeuse Yaël Braun-Pivet avec l’alias braun-pivet
 * (5) ✓ ajouter l’activité « Conception détaillée » avec l’identifiant cd
 * (6) ✓ ajouter la tâche « Définition des classes » avec l’identifiant dc à l’activité cd
 * (7) ✓ ajouter la tâche « Maquettage des interfaces » avec l’identifiant mi à l’activité cd
 * (8) ✓ ajouter une période de travail d’une heure à la tâche dc pour chacune des développeuses et aujourd’hui
 * (9) ✗ pour la développeuse bureau-bonnard, ajouter une période de travail à la tâche dc qui s’intersecte avec la période de travail précédemment créée pour cette même développeuse
 * (10) ✓ pour les développeuses braun et bureau-bonnard, ajouter une nouvelle période de travail d’une heure à la tâche mi et demain
 * (11) ✓ pour les développeuses braun-pivet et peyroles, ajouter une nouvelle période de travail d’une heure à la tâche dc et demain
 * (12) ✓ afficher les développeuses
 * • { braun, bureau-bonnard, peyroles, braun-pivet}
 * (13) ✓ afficher les tâches de l’activité cd
 * • {dc, mi}
 * (14) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
 * • 1 pour braun et bureau-bonnard, et 2 pour peyroles et braun-pivet
 * (15) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
 * • 1 pour braun et bureau-bonnard
 * (16) ✓ calculer la durée de travail de chaque développeuse
 * • 2h pour chacune des développeuses
 * (17) ✓ calculer la durée de travail sur chaque tâche de l’activité cd
 * • 6h pour la tâche dc et 2h pour la tâche mi
 * (18) ✓ calculer la durée de travail sur l’activité cd
 * • 8h
 * (19) ✓ calculer la durée de travail sur le projet
 * • 8h
 * ! Mises à la corbeille :
 * (20) ✓ mettre à la corbeille la développeuse bureau-bonnard
 * (21) ✓ afficher les développeuses
 * • {braun, peyroles, braun-pivet}
 * (22) ✓ afficher les développeuses dans la corbeille
 * • {bureau-bonnard}
 * (23) ✓ afficher les périodes de travail de la tâche dc de l’activité cd
 * • 1 pour braun, et 2 pour peyroles et braun-pivet
 * (24) ✓ afficher les périodes de travail de la tâche mi de l’activité cd
 * • 1 pour braun
 * (25) ✓ afficher les périodes de travail dans la corbeille
 * • 2 pour bureau-bonnard
 * (26) ✓ calculer la durée de travail de chaque développeuse
 * • 2h pour braun, braun-pivet et vienot, et 0h pour bureau-bonnard
 * (27) ✓ calculer la durée de travail sur chaque tâche de l’activité cd
 * • 5h pour la tâche dc et 1h pour la tâche mi
 * (28) ✓ calculer la durée de travail sur l’activité cd
 * • 6h
 * (29) ✓ calculer la durée de travail sur le projet
 * • 6h
 * (30) ✗ pour la développeuse bureau-bonnard, qui est dans la corbeille, ajouter une nouvelle période de travail d’une heure à la tâche dc et après-demain
 * (31) ✓ mettre à la corbeille la développeuse bureau-bonnard
 * (32) ✓ afficher les développeuses
 * • {braun, peyroles, braun-pivet}
 * (33) ✓ afficher les développeuses dans la corbeille
 * • {bureau-bonnard}
 * ! Restauration :
 * (34) ✓ restaurer la développeuse bureau-bonnard
 * (35) ✓ afficher les développeuses
 * • {braun, bureau-bonnard, peyroles, braun-pivet}
 * (36) ✓ afficher les développeuses dans la corbeille
 * • {}
 * (37) ✓ calculer la durée de travail sur le projet
 * • 6h
 * (38) ✓ calculer la durée de travail sur l’activité cd
 * • 6h
 * (39) ✓ calculer la durée de travail de la développeuse bureau-bonnard
 * • 0h
 * ! Labellisation
 * (40) ✓ ajouter la tâche « Révision JAVA » avec l’identifiant révision à l’activité cd
 * (41) ✓ pour la développeuse braun-pivet, qui est dans la corbeille, ajouter une nouvelle période de travail d’une heure à la tâche révision et « après-après-demain »
 * (42) ✓ ajouter le label « Remédiation » avec l’identifiant remédiation
 * (43) ✓ calculer la durée de travail sur le projet hors label remédiation
 * • 7h
 * (44) ✓ ajouter le label remédiation à la tâche révision
 * (45) ✓ calculer la durée de travail sur le projet hors label remédiation
 * • 6h
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSprint2 {
    private SuiPro suiPro;

    @BeforeAll
    public void setUp() throws OperationImpossible {
        suiPro = new SuiPro("Sprint2");
    }

    @AfterAll
    public void tearDown() {
        suiPro = null;
    }

    @Test
    @Disabled("Bug inside IntervalleInstants::Constructeur")
    void sprint2() {

        // (1) ✓ ajouter la développeuse Madeleine Braun avec l’alias braun
        try {
            suiPro.ajouterUnDeveloppeur("braun", "Madeleine", "Braun");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (2) ✓ ajouter la développeuse Carole Bureau-Bonnard avec l’alias bureau-bonnard
        try {
            suiPro.ajouterUnDeveloppeur("bureau-bonnard", "Carole", "Bureau-Bonnard");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (3) ✓ ajouter la développeuse Germaine Peyroles avec l’alias peyroles
        try {
            suiPro.ajouterUnDeveloppeur("peyroles", "Germaine", "Peyroles");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (4) ✓ ajouter la développeuse Yaël Braun-Pivet avec l’alias braun-pivet
        try {
            suiPro.ajouterUnDeveloppeur("braun-pivet", "Yaël", "Braun-Pivet");
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

        // (8) ✓ ajouter une période de travail d’une heure à la tâche dc pour chacune des développeuses et aujourd’hui
        try {
            Instant debut = Instant.now();
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd",
                    Arrays.asList("braun", "bureau-bonnard", "peyroles", "braun-pivet"), debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (9) ✗ pour la développeuse bureau-bonnard, ajouter une période de travail à la tâche dc qui s’intersecte avec la période de travail précédemment créée pour cette même développeuse
        try {
            Instant debut = Instant.now();
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", "bureau-bonnard", debut, fin);
            Assertions.fail("Une exception aurait du être levée");
        } catch (OperationImpossible e) {
            //! OK
        }

        // (10) ✓ pour les développeuses braun et bureau-bonnard, ajouter une nouvelle période de travail d’une heure à la tâche mi et demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(1));
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("mi", "cd", Arrays.asList("braun", "bureau-bonnard"), debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (11) ✓ pour les développeuses braun-pivet et peyroles, ajouter une nouvelle période de travail d’une heure à la tâche dc et demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(1));
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", Arrays.asList("braun-pivet", "peyroles"), debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (12) ✓ afficher les développeuses
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertTrue(affichage.contains("braun"));
            Assertions.assertTrue(affichage.contains("bureau-bonnard"));
            Assertions.assertTrue(affichage.contains("peyroles"));
            Assertions.assertTrue(affichage.contains("braun-pivet"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
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

        // (14) ✓ afficher les périodes de travail de la tâche dc de l'activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche dc :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "dc");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun,") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "bureau-bonnard") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "peyroles") == 2);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun-pivet") == 2);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (15) ✓ afficher les périodes de travail de la tâche mi de l'activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche mi :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "mi");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun,") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "bureau-bonnard") == 1);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (16) ✓ calculer la durée de travail de chaque développeuse
        try {
            System.out.println("Calcul de la durée de travail de chaque développeur :");
            List<String> devs = Arrays.asList("braun", "bureau-bonnard", "peyroles", "braun-pivet");
            for (String dev : devs) {
                double duree = suiPro.calculerTempsDeTravailDeveloppeur(dev);
                Assertions.assertTrue(duree == 2.0);
                System.out.println("\t" + dev + " : " + duree + " heures");
            }
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (17) ✓ calculer la durée de travail sur chaque tâche de l’activité cd
        try {
            System.out.println("Calcul de la durée de travail sur chaque tâche de l'activité cd :");

            double duree = suiPro.calculerTempsDeTravailTache("cd", "dc");
            Assertions.assertTrue(duree == 6.0);
            System.out.println("\t" + "dc" + " : " + duree + " heures");

            duree = suiPro.calculerTempsDeTravailTache("cd", "mi");
            Assertions.assertTrue(duree == 2.0);
            System.out.println("\t" + "mi" + " : " + duree + " heures");

            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (18) ✓ calculer la durée de travail sur l’activité cd
        try {
            System.out.println("Calcul de la durée de travail sur l'activité cd :");
            double duree = suiPro.calculerTempsDeTravailActivite("cd");
            Assertions.assertTrue(duree == 8.0);
            System.out.println("\t" + "cd" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (19) ✓ calculer la durée de travail sur le projet
        try {
            System.out.println("Calcul de la durée de travail sur le projet :");
            double duree = suiPro.calculerTempsDeTravailProjet();
            Assertions.assertTrue(duree == 8.0);
            System.out.println("\t" + "projet" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (20) ✓ mettre à la corbeille la développeuse bureau-bonnard
        try {
            suiPro.mettreUnDeveloppeurALaCorbeille("bureau-bonnard");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (21) ✓ afficher les développeuses
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertTrue(affichage.contains("braun"));
            Assertions.assertFalse(affichage.contains("bureau-bonnard"));
            Assertions.assertTrue(affichage.contains("peyroles"));
            Assertions.assertTrue(affichage.contains("braun-pivet"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (22) ✓ afficher les développeuses à la corbeille
        try {
            System.out.println("Affichage des développeurs à la corbeille :");
            String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
            Assertions.assertTrue(affichage.contains("bureau-bonnard"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (23) ✓ afficher les périodes de travail de la tâche dc de l'activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche dc :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "dc");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun,") == 1);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "peyroles") == 2);
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun-pivet") == 2);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (24) ✓ afficher les périodes de travail de la tâche mi de l'activité cd
        try {
            System.out.println("Affichage des périodes de travail de la tâche mi :");
            String affichage = suiPro.afficherLesPeriodesDeTravailPourUneTache("cd", "mi");
            Assertions.assertTrue(StringUtils.countMatches(affichage, "braun,") == 1);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (25) ✓ afficher les périodes de travail à la corbeille
        try {
            System.out.println("Affichage des périodes de travail à la corbeille :");
            String affichage = suiPro.afficherLesPeriodesDeTravailALaCorbeille();
            Assertions.assertTrue(StringUtils.countMatches(affichage, "bureau-bonnard") == 2);
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (26) ✓ calculer la durée de travail de chaque développeuse
        try {
            System.out.println("Calcul de la durée de travail de chaque développeur :");
            double duree;
            List<String> devs = Arrays.asList("braun", "peyroles", "braun-pivet");
            for (String dev : devs) {
                duree = suiPro.calculerTempsDeTravailDeveloppeur(dev);
                Assertions.assertTrue(duree == 2.0);
                System.out.println("\t" + dev + " : " + duree + " heures");
            }
            duree = suiPro.calculerTempsDeTravailDeveloppeur("bureau-bonnard");
            Assertions.assertTrue(duree == 0.0);
            System.out.println("\t" + "bureau-bonnard" + " : " + duree + " heures");

            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (27) ✓ calculer la durée de travail sur chaque tâche de l’activité cd
        try {
            System.out.println("Calcul de la durée de travail sur chaque tâche de l'activité cd :");

            double duree = suiPro.calculerTempsDeTravailTache("cd", "dc");
            Assertions.assertTrue(duree == 5.0);
            System.out.println("\t" + "dc" + " : " + duree + " heures");

            duree = suiPro.calculerTempsDeTravailTache("cd", "mi");
            Assertions.assertTrue(duree == 1.0);
            System.out.println("\t" + "mi" + " : " + duree + " heures");

            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (28) ✓ calculer la durée de travail sur l’activité cd
        try {
            System.out.println("Calcul de la durée de travail sur l'activité cd :");
            double duree = suiPro.calculerTempsDeTravailActivite("cd");
            Assertions.assertTrue(duree == 6.0);
            System.out.println("\t" + "cd" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (29) ✓ calculer la durée de travail sur le projet
        try {
            System.out.println("Calcul de la durée de travail sur le projet :");
            double duree = suiPro.calculerTempsDeTravailProjet();
            Assertions.assertTrue(duree == 6.0);
            System.out.println("\t" + "projet" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (30) ✗ pour la développeuse bureau-bonnard, qui est dans la corbeille, ajouter une nouvelle période de travail sur la tâche dc et après demain
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(2));
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("dc", "cd", "bureau-bonnard", debut, fin);
            Assertions.fail("L'exception OperationImpossible aurait du être levée");
        } catch (OperationImpossible e) {
            //! ok
        }

        // (31) ✓ mettre à la corbeille la développeuse bureau-bonnard
        try {
            suiPro.mettreUnDeveloppeurALaCorbeille("bureau-bonnard");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (32) ✓ afficher les développeuses
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertTrue(affichage.contains("braun"));
            Assertions.assertFalse(affichage.contains("bureau-bonnard"));
            Assertions.assertTrue(affichage.contains("peyroles"));
            Assertions.assertTrue(affichage.contains("braun-pivet"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (33) ✓ afficher les développeuses à la corbeille
        try {
            System.out.println("Affichage des développeurs à la corbeille :");
            String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
            Assertions.assertTrue(affichage.contains("bureau-bonnard"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (34) ✓ restaurer la développeuse bureau-bonnard
        try {
            suiPro.restaurerUnDeveloppeur("bureau-bonnard");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (35) ✓ afficher les développeuses
        try {
            System.out.println("Affichage des développeurs :");
            String affichage = suiPro.afficherLesDeveloppeurs();
            Assertions.assertTrue(affichage.contains("braun"));
            Assertions.assertTrue(affichage.contains("bureau-bonnard"));
            Assertions.assertTrue(affichage.contains("peyroles"));
            Assertions.assertTrue(affichage.contains("braun-pivet"));
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (36) ✓ afficher les développeuses à la corbeille
        try {
            System.out.println("Affichage des développeurs à la corbeille :");
            String affichage = suiPro.afficherLesDeveloppeurALaCorebille();
            Assertions.assertTrue(affichage.isBlank());
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (37) ✓ calculer la durée de travail sur le projet
        try {
            System.out.println("Calcul de la durée de travail sur le projet :");
            double duree = suiPro.calculerTempsDeTravailProjet();
            Assertions.assertTrue(duree == 8.0);
            System.out.println("\t" + "projet" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (38) ✓ calculer la durée de travail sur l’activité cd
        try {
            System.out.println("Calcul de la durée de travail sur l'activité cd :");
            double duree = suiPro.calculerTempsDeTravailActivite("cd");
            Assertions.assertTrue(duree == 8.0);
            System.out.println("\t" + "cd" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (39) ✓ calculer la durée de travail de la développeuse bureau-bonnard
        try {
            System.out.println("Calcul de la durée de travail de la développeuse bureau-bonnard :");
            double duree = suiPro.calculerTempsDeTravailDeveloppeur("bureau-bonnard");
            Assertions.assertTrue(duree == 2.0);
            System.out.println("\t" + "bureau-bonnard" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (40) ✓ ajouter la tâche « Révision JAVA » avec l’identifiant révision à l’activité cd
        try {
            suiPro.ajouterUneTache("révision", "Révision JAVA", "cd");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (41) ✓ pour la développeuse braun-pivet, ajouter une nouvelle période de travail d’une heure à la tâche révision et « après-après-demain »
        try {
            Instant debut = Instant.now().plus(Duration.ofDays(3));
            Instant fin = debut.plus(Duration.ofHours(1));
            suiPro.ajouterUnePeriodeDeTravail("révision", "cd", "braun-pivet", debut, fin);
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (42) ✓ ajouter le label « Remédiation » avec l’identifiant remédiation
        try {
            suiPro.creerLabel("Remédiation", "remédiation");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }

        // (43) ✓ calculer la durée de travail sur le projet hors label remédiation
        try {
            System.out.println("Calcul de la durée de travail sur le projet hors label remédiation :");
            double duree = suiPro.calculerTempsDeTravailProjetHorsLabel("remédiation");
            Assertions.assertTrue(duree == 9.0);
            System.out.println("\t" + "projet" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // (44) ✓ ajouter le label remédiation à la tâche révision
        try {
            suiPro.ajouterLabelATache("remédiation", "cd", "révision");
        } catch (OperationImpossible e) {
            Assertions.fail(e.getMessage());
        }
        // (45) ✓ calculer la durée de travail sur le projet hors label remédiation
        try {
            System.out.println("Calcul de la durée de travail sur le projet hors label remédiation :");
            double duree = suiPro.calculerTempsDeTravailProjetHorsLabel("remédiation");
            Assertions.assertTrue(duree == 8.0);
            System.out.println("\t" + "projet" + " : " + duree + " heures");
            System.out.println("\n");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
