package eu.telecomsudparis.csc4102.suipro;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, Developpeur> developpeurs;

	/**
	 * construit une façade.
	 * 
	 * @param nomDeProjet le nom du projet géré par le logiciel.
	 */
	public SuiPro(final String nomDeProjet) {
		this.nomDeProjet = nomDeProjet;
		developpeurs = new HashMap<>();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomDeProjet != null && !nomDeProjet.isBlank() && developpeurs != null;
	}

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
		if (developpeurs.get(alias) != null) {
			throw new OperationImpossible("développeur déjà dans le système");
		}
		developpeurs.put(alias, new Developpeur(alias, nom, prenom));
		assert invariant();
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
