package eu.telecomsudparis.csc4102.suipro;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public final class SuiPro implements PropertyChangeListener {
    /**
     * le nom du projet.
     */
    private final String nomDeProjet;

    /**
     * la collection de développeurs. La clef est l'identifiant du développeur.
     */
    private LinkedHashMap<String, Developpeur> developpeurs;

    /**
     * la collection d'activités. La clef est l'identifiant de l'activité.
     */
    private LinkedHashMap<String, Activite> activites;

    /**
     * la collection de labels. La clef est l'identifiant du label.
     */
    private LinkedHashMap<String, Label> labels;

    /**
     * la corbeille.
     */
    private Corbeille corbeille;

    /**
     * construit une façade.
     * 
     * @param nomDeProjet le nom du projet géré par le logiciel.
     */
    public SuiPro(final String nomDeProjet) throws OperationImpossible {
        if (nomDeProjet == null || nomDeProjet.isBlank()) {
            throw new OperationImpossible("le nom du projet ne peut pas être null ou vide.");
        }
        this.nomDeProjet = nomDeProjet;
        developpeurs = new LinkedHashMap<>();
        activites = new LinkedHashMap<>();
        labels = new LinkedHashMap<>();
        corbeille = new Corbeille();

        corbeille.addPropertyChangeListener(this);
    }

    /**
     * vérifie l'invariant de la classe.
     * 
     * @return {@code true} si l'invariant est respecté.
     */
    public boolean invariant() {
        return nomDeProjet != null && !nomDeProjet.isBlank()
                && developpeurs != null
                && activites != null
                && labels != null
                && corbeille != null;
    }

    //#region Ajouts

    /**
     * ajoute un développeur.
     * 
     * @param alias  l'alias.
     * @param nom    le nom.
     * @param prenom le prénom.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterUnDeveloppeur(final String alias, final String nom, final String prenom)
            throws OperationImpossible {
        if (alias == null || alias.isBlank()) {
            throw new OperationImpossible("alias ne peut pas être null ou vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new OperationImpossible("nom ne peut pas être null ou vide");
        }
        if (prenom == null || prenom.isBlank()) {
            throw new OperationImpossible("prenom ne peut pas être null ou vide");
        }
        if (developpeurs.containsKey(alias)) {
            throw new OperationImpossible("développeur déjà dans le système");
        }
        Developpeur dev = new Developpeur(alias, nom, prenom, corbeille);
        corbeille.addPropertyChangeListener(dev);
        developpeurs.put(alias, dev);
        assert invariant();
    }

    /**
     * ajoute une activité.
     * 
     * @param id  l'identifiant de l'activité.
     * @param nom le nom de l'activité.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterUneActivite(final String id, final String nom)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être null ou vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new OperationImpossible("nom ne peut pas être null ou vide");
        }
        if (activites.containsKey(id)) {
            throw new OperationImpossible("activite déjà dans le système");
        }
        Activite activite = new Activite(nom, id, corbeille);
        corbeille.addPropertyChangeListener(activite);
        activites.put(id, activite);
        assert invariant();
    }

    /**
     * ajoute une tâche.
     * 
     * @param id          l'identifiant de la tâche.
     * @param nom         le nom de la tâche.
     * @param activiteId  l'identifiant de l'activité.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterUneTache(final String id, final String nom, final String activiteId)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être null ou vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new OperationImpossible("nom ne peut pas être null ou vide");
        }
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être null ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("activiteId ne correspond à aucune activite");
        }

        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("activite n'est pas active");
        }
        if (activite.getTache(id) != null) {
            throw new OperationImpossible("tache déjà dans le système");
        }
        Tache tache = new Tache(nom, id, activite, corbeille);
        corbeille.addPropertyChangeListener(tache);
        assert invariant();
    }

    /**
     * ajoute une période de travail.
     * 
     * @param tacheId     l'identifiant de la tâche.
     * @param activiteId  l'identifiant de l'activité.
     * @param developpeurId l'identifiant du développeur.
     * @param debut       l'heure de début de la période de travail.
     * @param fin         l'heure de fin de la période de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterUnePeriodeDeTravail(final String tacheId, final String activiteId, final String developpeurId,
            final Instant debut, final Instant fin) throws OperationImpossible {
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être null ou vide");
        }
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être null ou vide");
        }
        if (developpeurId == null || developpeurId.isBlank()) {
            throw new OperationImpossible("developpeurId ne peut pas être null ou vide");
        }
        if (debut == null) {
            throw new OperationImpossible("debut ne peut pas être null");
        }
        if (fin == null) {
            throw new OperationImpossible("fin ne peut pas être null");
        }
        if (debut.compareTo(fin) > 0) {
            throw new OperationImpossible("debut doit être avant fin");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("activiteId ne correspond à aucune activite");
        }
        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("activite n'est pas active");
        }

        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("tacheId ne correspond à aucune tache");
        }
        if (!tache.estEnFonctionnement()) {
            throw new OperationImpossible("tache n'est pas active");
        }
        if (!developpeurs.containsKey(developpeurId)) {
            throw new OperationImpossible(String.format("%s ne correspond à aucun developpeur", developpeurId));
        }
        Developpeur developpeur = developpeurs.get(developpeurId);
        if (!developpeur.estEnFonctionnement()) {
            throw new OperationImpossible("developpeur n'est pas en fonctionnement");
        }

        new PeriodeDeTravail(debut, fin, tache, developpeur, corbeille);

        assert invariant();
    }

    /**
     * ajoute une période de travail.
     * 
     * @param tacheId     l'identifiant de la tâche.
     * @param activiteId  l'identifiant de l'activité.
     * @param developpeurIds liste d'identifiants des développeurs.
     * @param debut       l'heure de début de la période de travail.
     * @param fin         l'heure de fin de la période de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterUnePeriodeDeTravail(final String tacheId, final String activiteId,
            final List<String> developpeurIds, final Instant debut, final Instant fin)
            throws OperationImpossible {
        if (developpeurIds == null || developpeurIds.isEmpty()) {
            throw new OperationImpossible("developpeurIds ne peut pas être null ou vide");
        }
        for (String developpeurId : developpeurIds) {
            ajouterUnePeriodeDeTravail(tacheId, activiteId, developpeurId, debut, fin);
        }
    }

    //#endregion

    //#region Affichages

    /**
     * affiche les développeurs.
     * 
     * @param printType le type d'affichage.
     * @return la chaîne de caractères représentant la liste des développeurs.
     * @throws OperationImpossible
     */
    private String afficherLesDeveloppeurs(final PrintType printType) throws OperationImpossible {
        List<Developpeur> developpeursList = Utils.filterPrintType(developpeurs.values(), printType);

        return Utils.printCollection(developpeursList);
    }

    /**
     * affiche les développeurs.
     * 
     * @return la chaîne de caractères représentant la liste des développeurs.
     * @throws OperationImpossible
     */
    public String afficherLesDeveloppeurs() throws OperationImpossible {
        return afficherLesDeveloppeurs(PrintType.ACTIF);
    }

    /**
     * affiche les activités.
     * 
     * @param printType le type d'affichage.
     * @return la chaîne de caractères représentant la liste des activités.
     * @throws OperationImpossible
     */
    private String afficherLesActivites(final PrintType printType) throws OperationImpossible {
        List<Activite> activitesList = Utils.filterPrintType(activites.values(), printType);

        return Utils.printCollection(activitesList);
    }

    /**
     * affiche les activités.
     * 
     * @return la chaîne de caractères représentant la liste des activités.
     * @throws OperationImpossible
     */
    public String afficherLesActivites() throws OperationImpossible {
        return afficherLesActivites(PrintType.ACTIF);
    }

    /**
     * affiche les tâches.
     * 
     * @param activiteId l'identifiant de l'activité.
     * @param printType  le type d'affichage.
     * @return la chaîne de caractères représentant la liste des tâches.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    private String afficherLesTaches(final String activiteId, final PrintType printType) throws OperationImpossible {
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être null ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("activiteId ne correspond à aucune activite");
        }

        Activite activite = (Activite) activites.get(activiteId);

        List<ITache> tachesList = Utils.filterPrintType(activite.getTaches(), printType);

        return Utils.printCollection(tachesList);
    }

    /**
     * affiche les tâches.
     * 
     * @param activiteId l'identifiant de l'activité.
     * @return la chaîne de caractères représentant la liste des tâches.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public String afficherLesTaches(final String activiteId) throws OperationImpossible {
        return afficherLesTaches(activiteId, PrintType.ACTIF);
    }

    /**
     * affiche les périodes de travail.
     * 
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId    l'identifiant de la tâche.
     * @param printType  le type d'affichage.
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    private String afficherLesPeriodesDeTravailPourUneTache(final String activiteId, final String tacheId,
            final PrintType printType) throws OperationImpossible {
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être null ou vide");
        }
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être null ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("activiteId ne correspond à aucune activite");
        }

        Activite activite = activites.get(activiteId);
        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("tacheId ne correspond à aucune tache");
        }

        List<IPeriodeDeTravail> periodesDeTravailList = Utils.filterPrintType(tache.getPeriodesDeTravail(), printType);

        return Utils.printCollection(periodesDeTravailList);
    }

    /**
     * affiche les périodes de travail.
     * 
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId    l'identifiant de la tâche.
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public String afficherLesPeriodesDeTravailPourUneTache(final String activiteId, final String tacheId)
            throws OperationImpossible {
        return afficherLesPeriodesDeTravailPourUneTache(activiteId, tacheId, PrintType.ACTIF);
    }

    /**
     * affiche les périodes de travail.
     * 
     * @param developpeurId l'identifiant du développeur.
     * @param printType     le type d'affichage.
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    private String afficherLesPeriodesDeTravailPourUnDeveloppeur(final String developpeurId, final PrintType printType)
            throws OperationImpossible {
        if (developpeurId == null || developpeurId.isBlank()) {
            throw new OperationImpossible("developpeurId ne peut pas être null ou vide");
        }
        if (!developpeurs.containsKey(developpeurId)) {
            throw new OperationImpossible("developpeurId ne correspond à aucun developpeur");
        }

        Developpeur developpeur = developpeurs.get(developpeurId);

        List<IPeriodeDeTravail> periodesDeTravailList = Utils.filterPrintType(developpeur.getPeriodesDeTravail(),
                printType);

        return Utils.printCollection(periodesDeTravailList);
    }

    /**
     * affiche les périodes de travail.
     * 
     * @param developpeurId l'identifiant du développeur.
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public String afficherLesPeriodesDeTravailPourUnDeveloppeur(final String developpeurId)
            throws OperationImpossible {
        return afficherLesPeriodesDeTravailPourUnDeveloppeur(developpeurId, PrintType.ACTIF);
    }

    /**
     * affiche les périodes de travail.
     * 
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible
     */
    public String afficherLesDeveloppeurALaCorebille() throws OperationImpossible {
        List<Developpeur> devs = corbeille.getElementsJetable(Developpeur.class);

        return Utils.printCollection(devs);
    }

    /**
     * affiche les activités.
     * @return la chaîne de caractères représentant la liste des activités.
     * @throws OperationImpossible
     */
    public String afficherLesActivitesALaCorbeille() throws OperationImpossible {
        List<Activite> activites = corbeille.getElementsJetable(Activite.class);

        return Utils.printCollection(activites);
    }

    /**
     * affiche les tâches.
     * @return la chaîne de caractères représentant la liste des tâches.
     * @throws OperationImpossible
     */
    public String afficherLesTachesALaCorbeille() throws OperationImpossible {
        List<Tache> taches = corbeille.getElementsJetable(Tache.class);

        return Utils.printCollection(taches);
    }

    /**
     * affiche les périodes de travail.
     * @return la chaîne de caractères représentant la liste des périodes de travail.
     * @throws OperationImpossible
     */
    public String afficherLesPeriodesDeTravailALaCorbeille() throws OperationImpossible {
        List<PeriodeDeTravail> periodesDeTravail = corbeille.getElementsJetable(PeriodeDeTravail.class);

        return Utils.printCollection(periodesDeTravail);
    }

    //#endregion

    //#region MiseALaCorbeille

    /**
     * Met un developpeur à la corbeille.
     * @param id l'identifiant du developpeur.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void mettreUnDeveloppeurALaCorbeille(final String id)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!developpeurs.containsKey(id)) {
            throw new OperationImpossible("Le developpeur n'existe pas");
        }

        Developpeur dev = developpeurs.get(id);
        dev.mettreALaCorbeille();
    }

    /**
     * Met une activité à la corbeille.
     * @param id l'identifiant de l'activité.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void mettreUneActiviteALaCorbeille(final String id)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(id)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(id);
        activite.mettreALaCorbeille();
    }

    /**
     * Met une tâche à la corbeille.
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId l'identifiant de la tâche.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void mettreUneTacheALaCorbeille(final String activiteId, final String tacheId)
            throws OperationImpossible {
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être nul ou vide");
        }
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(activiteId);
        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("La tache n'existe pas");
        }

        tache.mettreALaCorbeille();
    }

    //#endregion

    //#region Restauration

    /**
     * Restaure un developpeur.
     * @param id l'identifiant du developpeur.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void restaurerUnDeveloppeur(final String id)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!developpeurs.containsKey(id)) {
            throw new OperationImpossible("Le developpeur n'existe pas");
        }

        Developpeur dev = developpeurs.get(id);
        dev.restaurer();
    }

    /**
     * Restaure une activité.
     * @param id l'identifiant de l'activité.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void restaurerUneActivite(final String id)
            throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(id)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(id);
        activite.restaurer();
    }

    /**
     * Restaure une tâche.
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId l'identifiant de la tâche.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */

    public void restaurerUneTache(final String activiteId, final String tacheId)
            throws OperationImpossible {
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être nul ou vide");
        }
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("L'activite n'est pas en fonctionnement");
        }

        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("La tache n'existe pas");
        }

        tache.restaurer();
    }

    //#endregion

    //#region Vider la corbeille

    /**
     * Vide la corbeille.
     */
    public void viderLaCorbeille() {
        corbeille.viderLaCorbeille();
    }

    //#endregion

    //#region Calculs

    /**
     * Calcul le temps de travail d'un développeur.
     * 
     * @param id l'identifiant du développeur.
     * @return le temps de travail d'un développeur.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public double calculerTempsDeTravailDeveloppeur(final String id) throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!developpeurs.containsKey(id)) {
            throw new OperationImpossible("Le developpeur n'existe pas");
        }

        Developpeur dev = developpeurs.get(id);
        return dev.calculerTempsDeTravail();
    }

    /**
     * Calcul le temps de travail d'une activité.
     * 
     * @param id l'identifiant de l'activité.
     * @return le temps de travail d'une activité.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public double calculerTempsDeTravailActivite(final String id) throws OperationImpossible {
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("id ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(id)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(id);
        return activite.calculerTempsDeTravail();
    }

    /**
     * Calcul le temps de travail d'une tâche.
     * 
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId l'identifiant de la tâche.
     * @return le temps de travail d'une tâche.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public double calculerTempsDeTravailTache(final String activiteId, final String tacheId)
            throws OperationImpossible {
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être nul ou vide");
        }
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être nul ou vide");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("L'activite n'est pas en fonctionnement");
        }

        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("La tache n'existe pas");
        }

        return tache.calculerTempsDeTravail();
    }

    /** 
     * Calcul le temps de travail du projet.
     * 
     * @return le temps de travail du projet.
     */
    public double calculerTempsDeTravailProjet() {
        return activites.values().stream().mapToDouble(Activite::calculerTempsDeTravail).sum();
    }

    /**
     * Calcul le temps de travail du projet hors d'un label.
     * 
     * @param label le label.
     * @return le temps de travail du projet hors d'un label.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public double calculerTempsDeTravailProjetHorsLabel(final String label) throws OperationImpossible {
        return calculerTempsDeTravailProjetHorsLabels(List.of(label));
    }

    /**
     * Calcul le temps de travail du projet hors de plusieurs labels.
     * 
     * @param labels les labels.
     * @return le temps de travail du projet hors de plusieurs labels.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public double calculerTempsDeTravailProjetHorsLabels(final List<String> labels) throws OperationImpossible {
        if (labels == null || labels.isEmpty()) {
            throw new OperationImpossible("labels ne peut pas être nul ou vide");
        }
        for (String label : labels) {
            if (!this.labels.containsKey(label)) {
                throw new OperationImpossible("Le label n'existe pas");
            }
        }

        List<Label> labelsList = labels.stream().map(this.labels::get).toList();

        // Non Intersection between Labelisable and labels
        Predicate<Labelisable> predicate = l -> l.getLabels().stream().noneMatch(labelsList::contains);

        Stream<Activite> activitesStream = activites.values().stream()
                .filter(predicate);
        Stream<ITache> tachesStream = activitesStream
                .map(Activite::getTaches)
                .flatMap(Collection::stream)
                .filter(predicate);

        return tachesStream.mapToDouble(ITache::calculerTempsDeTravail).sum();
    }

    //#endregion

    //#region Labels

    /**
     * Crée un label.
     * 
     * @param labelName le nom du label.
     * @param labelId l'identifiant du label.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void creerLabel(final String labelName, final String labelId) throws OperationImpossible {
        if (labelName == null || labelName.isBlank()) {
            throw new OperationImpossible("labelName ne peut pas être nul ou vide");
        }
        if (labelId == null || labelId.isBlank()) {
            throw new OperationImpossible("labelId ne peut pas être nul ou vide");
        }
        if (labels.containsKey(labelId)) {
            throw new OperationImpossible("Le label existe déjà");
        }

        Label label = new Label(labelName, labelId);
        labels.put(labelId, label);
    }

    /**
     * Ajoute un label à une activité.
     * 
     * @param labelId l'identifiant du label.
     * @param activiteId l'identifiant de l'activité.
     * 
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void ajouterLabelAActivite(final String labelId, final String activiteId)
            throws OperationImpossible {
        if (labelId == null || labelId.isBlank()) {
            throw new OperationImpossible("labelId ne peut pas être nul ou vide");
        }
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être nul ou vide");
        }
        if (!labels.containsKey(labelId)) {
            throw new OperationImpossible("Le label n'existe pas");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("L'activite n'est pas en fonctionnement");
        }

        Label label = labels.get(labelId);

        activite.ajouterLabel(label);
    }

    /**
     * Ajoute un label à une tâche.
     * 
     * @param labelId l'identifiant du label.
     * @param activiteId l'identifiant de l'activité.
     * @param tacheId l'identifiant de la tâche.
     * 
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table de décision des tests de validation).
     */
    public void ajouterLabelATache(final String labelId, final String activiteId, final String tacheId)
            throws OperationImpossible {
        if (labelId == null || labelId.isBlank()) {
            throw new OperationImpossible("labelId ne peut pas être nul ou vide");
        }
        if (activiteId == null || activiteId.isBlank()) {
            throw new OperationImpossible("activiteId ne peut pas être nul ou vide");
        }
        if (tacheId == null || tacheId.isBlank()) {
            throw new OperationImpossible("tacheId ne peut pas être nul ou vide");
        }
        if (!labels.containsKey(labelId)) {
            throw new OperationImpossible("Le label n'existe pas");
        }
        if (!activites.containsKey(activiteId)) {
            throw new OperationImpossible("L'activite n'existe pas");
        }

        Activite activite = activites.get(activiteId);

        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("L'activite n'est pas en fonctionnement");
        }

        Tache tache = (Tache) activite.getTache(tacheId);

        if (tache == null) {
            throw new OperationImpossible("La tache n'existe pas");
        }

        if (!tache.estEnFonctionnement()) {
            throw new OperationImpossible("La tache n'est pas en fonctionnement");
        }

        Label label = labels.get(labelId);

        tache.ajouterLabel(label);
    }

    //#endregion

    //#region Getters

    /**
     * obtient la liste des développeurs.
     * @return la liste des développeurs.
     */
    @Deprecated(forRemoval = true)
    public List<Developpeur> getDeveloppeurs() {
        return developpeurs.values().stream().toList();
    }

    /**
     * obtient la liste des identifiants des développeurs.
     * @return la liste des identifiants des développeurs.
     */
    @Deprecated(forRemoval = true)
    public List<String> getDeveloppeursIds() {
        return developpeurs.keySet().stream().toList();
    }

    /**
     * obtient un développeur.
     * @param alias l'alias du développeur.
     * @return le développeur.
     */
    @Deprecated(forRemoval = true)
    public Developpeur getDeveloppeur(final String alias) {
        return developpeurs.get(alias);
    }

    /**
     * obtient la liste des activités.
     * @return la liste des activités.
     */
    @Deprecated(forRemoval = true)
    public List<Activite> getActivites() {
        return activites.values().stream().toList();
    }

    /**
     * obtient la liste des identifiants des activités.
     * @return la liste des identifiants des activités.
     */
    @Deprecated(forRemoval = true)
    public List<String> getActivitesIds() {
        return activites.keySet().stream().toList();
    }

    /**
     * obtient une activité.
     * @param id l'identifiant de l'activité.
     * @return l'activité.
     */
    @Deprecated(forRemoval = true)
    public Activite getActivite(final String id) {
        return activites.get(id);
    }

    /**
     * obtient le nom du projet.
     * 
     * @return le nom du projet.
     */
    public String getNomDeProjet() {
        return nomDeProjet;
    }

    /**
     * @deprecated Utiliser le print pour afficher les informations.
     * obtient la corbeille.
     * 
     * @return la corbeille. 
     */
    @Deprecated(forRemoval = true)
    public Corbeille getCorbeille() {
        return corbeille;
    }

    //#endregion

    @Override
    public String toString() {
        return "SuiPro [nomDeProjet=" + nomDeProjet + "]";
    }

    /**
    * This method is called when an event is fire by the Corbeille when a {@link Developpeur} or an {@link Activite} is removed from it.
    * 
    * @param evt {@code source} must be a {@link Corbeille} and {@code propertyName} must be {@link Developpeur} or {@link Activite} string.
    */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getSource().getClass() != Corbeille.class) {
            return;
        }
        if (evt.getNewValue() != null) {
            return;
        }
        if (evt.getPropertyName().equals(Developpeur.class.getSimpleName())) {
            Developpeur dev = (Developpeur) evt.getOldValue();
            developpeurs.remove(dev.getAlias());
        } else if (evt.getPropertyName().equals(Activite.class.getSimpleName())) {
            Activite activite = (Activite) evt.getOldValue();
            activites.remove(activite.getId());
        }
    }
}
