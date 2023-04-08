package eu.telecomsudparis.csc4102.suipro;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept de développeur. Un développeur est un élément
 * jetable référençant une collection de période de travail.
 * 
 * @author Denis Conan
 */
public final class Developpeur extends ElementJetable implements IDeveloppeur {
	/**
	 * l'alias du développeur.
	 */
	private final String alias;
	/**
	 * le nom du développeur.
	 */
	private final String nom;
	/**
	 * le prénom du développeur.
	 */
	private final String prenom;

	/**
	 * les périodes de travail du développeur.
	 */
	private List<IPeriodeDeTravail> periodesDeTravail;

	/**
	 * construit un développeur.
	 * 
	 * @param alias  l'alias.
	 * @param nom    le nom.
	 * @param prenom le prénom.
	 * @throws OperationImpossible
	 */
	@Deprecated
	public Developpeur(final String alias, final String nom, final String prenom) throws OperationImpossible {
		super();
		if (alias == null || alias.isBlank()) {
			throw new OperationImpossible("alias ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new OperationImpossible("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new OperationImpossible("prenom ne peut pas être null ou vide");
		}
		this.alias = alias;
		this.nom = nom;
		this.prenom = prenom;
		this.periodesDeTravail = new ArrayList<IPeriodeDeTravail>();
	}

	public Developpeur(final String alias, final String nom, final String prenom, final ICorbeille corbeille)
			throws OperationImpossible {
		this(alias, nom, prenom);
		if (corbeille == null) {
			throw new OperationImpossible("corbeille ne peut pas être null");
		}
		this.setCorbeille(corbeille);

		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return alias != null && !alias.isBlank()
				&& nom != null && !nom.isBlank()
				&& prenom != null && !prenom.isBlank()
				&& periodesDeTravail != null
				&& super.invariant();
	}

	/**
	 * ajoute une période de travail.
	 * 
	 * @param periodeDeTravail la période de travail.
	 * @throws OperationImpossible
	 */
	public void ajouterPeriodeDeTravail(final IPeriodeDeTravail periodeDeTravail) throws OperationImpossible {
		if (periodeDeTravail == null) {
			throw new OperationImpossible("periodeDeTravail ne peut pas être null");
		}
		if (!periodeDeTravail.estEnFonctionnement()) {
			throw new OperationImpossible("La période de travail ne peut pas être ajoutée car elle n'est pas active");
		}
		if (periodeDeTravail.getDeveloppeur() != this) {
			throw new OperationImpossible(
					"La période de travail ne peut pas être ajoutée car elle est déjà associée à un développeur");
		}
		for (IPeriodeDeTravail p : periodesDeTravail) {
			if (p.getIntervalle().intervalleInstantsSIntersectent(periodeDeTravail.getIntervalle())) {
				throw new OperationImpossible(
						"La période de travail ne peut pas être ajoutée car elle chevauche une autre période de travail");
			}
		}
		if (!this.estEnFonctionnement()) {
			throw new OperationImpossible(
					"La période de travail ne peut pas être ajoutée car le développeur n'est pas en fonctionnement");
		}

		periodesDeTravail.add(periodeDeTravail);

		assert invariant();
	}

	//#region getters
	/**
	 * obtient l'alias.
	 * 
	 * @return l'alias.
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * obtient le nom.
	 * 
	 * @return le nom.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * obtient le prenom.
	 * 
	 * @return le prenom.
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * obtient la liste des périodes de travail.
	 * 
	 * @return la liste des périodes de travail.
	 */
	public Collection<IPeriodeDeTravail> getPeriodesDeTravail() {
		return periodesDeTravail.stream().toList();
	}

	//#endregion

	@Override
	protected void specificMettreALaCorbeille() throws OperationImpossible {
		for (IPeriodeDeTravail p : periodesDeTravail) {
			p.mettreALaCorbeille();
		}
	}

	@Override
	protected void specificRestaurer() throws OperationImpossible {
		for (IPeriodeDeTravail p : periodesDeTravail) {
			p.restaurer();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Developpeur other = (Developpeur) obj;
		return Objects.equals(alias, other.alias);
	}

	@Override
	public String toString() {
		return "Developpeur [alias=" + alias + ", nom=" + nom + ", prenom=" + prenom + "enFonctionnement="
				+ estEnFonctionnement() + "]";
	}

	/**
	 * This method is called when an event is fire by the Corbeille when a PeriodeDeTravail is removed from it.
	 * 
	 * @param evt {@code source} must be a {@link Corbeille} and {@code propertyName} must be {@link PeriodeDeTravail} string.
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if (evt.getSource().getClass() != Corbeille.class) {
			return;
		}
		if (!evt.getPropertyName().equals(PeriodeDeTravail.class.getSimpleName())) {
			return;
		}
		if (evt.getNewValue() != null) {
			return;
		}
		IPeriodeDeTravail periodeDeTravail = (IPeriodeDeTravail) evt.getOldValue();
		periodesDeTravail.remove(periodeDeTravail);
	}
}
