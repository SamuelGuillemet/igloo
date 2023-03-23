package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class SuiPro {
	/**
	 * le nom du projet.
	 */
	private final String nomDeProjet;

	/**
	 * la collection de développeurs. La clef est l'identifiant du développeur.
	 */
	private LinkedHashMap<String, Developpeur> developpeurs;

	private LinkedHashMap<String, Activite> activites;

	private Corbeille corbeille;

	/**
	 * construit une façade.
	 * 
	 * @param nomDeProjet le nom du projet géré par le logiciel.
	 */
	public SuiPro(final String nomDeProjet) {
		this.nomDeProjet = nomDeProjet;
		developpeurs = new LinkedHashMap<>();
		activites = new LinkedHashMap<>();
		corbeille = Corbeille.getInstance();
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
		developpeurs.put(alias, new Developpeur(alias, nom, prenom));
		assert invariant();
	}

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
		activites.put(id, new Activite(nom, id));
		assert invariant();
	}

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

		if (!activite.estActif()) {
			throw new OperationImpossible("activite n'est pas active");
		}
		if (activite.getTache(id) != null) {
			throw new OperationImpossible("tache déjà dans le système");
		}
		new Tache(nom, id, activite);
		assert invariant();
	}

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

		if (!activite.estActif()) {
			throw new OperationImpossible("activite n'est pas active");
		}

		Tache tache = (Tache) activite.getTache(tacheId);

		if (tache == null) {
			throw new OperationImpossible("tacheId ne correspond à aucune tache");
		}
		if (!tache.estActif()) {
			throw new OperationImpossible("tache n'est pas active");
		}
		if (!developpeurs.containsKey(developpeurId)) {
			throw new OperationImpossible(String.format("%s ne correspond à aucun developpeur", developpeurId));
		}
		Developpeur developpeur = developpeurs.get(developpeurId);
		if (!developpeur.estActif()) {
			throw new OperationImpossible("developpeur n'est pas actif");
		}

		new PeriodeDeTravail(debut, fin, tache, developpeur);

		assert invariant();
	}

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

	public String afficherLesDeveloppeurs(final PrintType printType) {
		List<Developpeur> developpeursList = Utils.filterPrintType(developpeurs.values(), printType);

		return Utils.printCollection(developpeursList);
	}

	public String afficherLesDeveloppeurs() {
		return afficherLesDeveloppeurs(PrintType.ACTIF);
	}

	public String afficherLesActivites(final PrintType printType) {
		List<Activite> activitesList = Utils.filterPrintType(activites.values(), printType);

		return Utils.printCollection(activitesList);
	}

	public String afficherLesActivites() {
		return afficherLesActivites(PrintType.ACTIF);
	}

	public String afficherLesTaches(final String activiteId, final PrintType printType) throws OperationImpossible {
		if (activiteId == null || activiteId.isBlank()) {
			throw new IllegalArgumentException("activiteId ne peut pas être null ou vide");
		}
		if (!activites.containsKey(activiteId)) {
			throw new OperationImpossible("activiteId ne correspond à aucune activite");
		}

		Activite activite = (Activite) activites.get(activiteId);

		List<ITache> tachesList = Utils.filterPrintType(activite.getTaches(), printType);

		return Utils.printCollection(tachesList);
	}

	public String afficherLesTaches(final String activiteId) throws OperationImpossible {
		return afficherLesTaches(activiteId, PrintType.ACTIF);
	}

	public String afficherLesPeriodesDeTravailPourUneTache(final String activiteId, final String tacheId,
			final PrintType printType) throws OperationImpossible {
		if (activiteId == null || activiteId.isBlank()) {
			throw new IllegalArgumentException("activiteId ne peut pas être null ou vide");
		}
		if (tacheId == null || tacheId.isBlank()) {
			throw new IllegalArgumentException("tacheId ne peut pas être null ou vide");
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

	public String afficherLesPeriodesDeTravailPourUneTache(final String activiteId, final String tacheId)
			throws OperationImpossible {
		return afficherLesPeriodesDeTravailPourUneTache(activiteId, tacheId, PrintType.ACTIF);
	}

	public String afficherLesPeriodesDeTravailPourUnDeveloppeur(final String developpeurId, final PrintType printType)
			throws OperationImpossible {
		if (developpeurId == null || developpeurId.isBlank()) {
			throw new IllegalArgumentException("developpeurId ne peut pas être null ou vide");
		}
		if (!developpeurs.containsKey(developpeurId)) {
			throw new OperationImpossible("developpeurId ne correspond à aucun developpeur");
		}

		Developpeur developpeur = developpeurs.get(developpeurId);

		List<IPeriodeDeTravail> periodesDeTravailList = Utils.filterPrintType(developpeur.getPeriodesDeTravail(),
				printType);

		return Utils.printCollection(periodesDeTravailList);
	}

	public String afficherLesPeriodesDeTravailPourUnDeveloppeur(final String developpeurId)
			throws OperationImpossible {
		return afficherLesPeriodesDeTravailPourUnDeveloppeur(developpeurId, PrintType.ACTIF);
	}

	public String afficherLesDeveloppeurALaCorebille() {
		ArrayList<Developpeur> devs = corbeille.getElementsJetable(Developpeur.class);

		return Utils.printCollection(devs);
	}

	public String afficherLesActivitesALaCorbeille() {
		ArrayList<Activite> activites = corbeille.getElementsJetable(Activite.class);

		return Utils.printCollection(activites);
	}

	public String afficherLesTachesALaCorbeille() {
		ArrayList<Tache> taches = corbeille.getElementsJetable(Tache.class);

		return Utils.printCollection(taches);
	}

	public String afficherLesPeriodesDeTravailALaCorbeille() {
		ArrayList<PeriodeDeTravail> periodesDeTravail = corbeille.getElementsJetable(PeriodeDeTravail.class);

		return Utils.printCollection(periodesDeTravail);
	}

	//#endregion

	//#region MiseALaCorbeille

	public void mettreUnDeveloppeurALaCorbeille(final String id) throws OperationImpossible {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("id ne peut pas être nul ou vide");
		}
		if (!developpeurs.containsKey(id)) {
			throw new OperationImpossible("Le developpeur n'existe pas");
		}

		Developpeur dev = developpeurs.get(id);
		dev.mettreALaCorbeille();
	}

	public void mettreUneActiviteALaCorbeille(final String id) throws OperationImpossible {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("id ne peut pas être nul ou vide");
		}
		if (!activites.containsKey(id)) {
			throw new OperationImpossible("L'activite n'existe pas");
		}

		Activite activite = activites.get(id);
		activite.mettreALaCorbeille();
	}

	public void mettreUneTacheALaCorbeille(final String activiteId, final String tacheId) throws OperationImpossible {
		if (activiteId == null || activiteId.isBlank()) {
			throw new IllegalArgumentException("activiteId ne peut pas être nul ou vide");
		}
		if (tacheId == null || tacheId.isBlank()) {
			throw new IllegalArgumentException("tacheId ne peut pas être nul ou vide");
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

	public List<Developpeur> getDeveloppeurs() {
		return developpeurs.values().stream().toList();
	}

	public List<String> getDeveloppeursIds() {
		return developpeurs.keySet().stream().toList();
	}

	/**
	 * obtient le nom du projet.
	 * 
	 * @return le nom du projet.
	 */
	public String getNomDeProjet() {
		return nomDeProjet;
	}

	@Override
	public String toString() {
		return "SuiPro [nomDeProjet=" + nomDeProjet + "]";
	}
}
