package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le concept de période de travail. Une période de travail est
 * un élément jetable référençant un développeur, une tâche et un intervalle
 * d'instant.
 */
public final class PeriodeDeTravail extends ElementJetable implements IPeriodeDeTravail {
	/**
	 * l'intervalle de temps de la période de travail.
	 */
	private final IntervalleInstants intervalle;

	/**
	 * le développeur qui a travaillé sur la tâche.
	 */
	private final IDeveloppeur developpeur;

	/**
	 * la tâche sur laquelle le développeur a travaillé.
	 */
	private final ITache tache;

	/**
	 * @param debut
	 * @param fin
	 * @param tache
	 * @param developpeur
	 * @throws OperationImpossible
	 */
	public PeriodeDeTravail(final Instant debut, final Instant fin, final ITache tache, final IDeveloppeur developpeur)
			throws OperationImpossible {
		super();
		if (debut == null) {
			throw new IllegalArgumentException("debut ne peut pas être null");
		}
		if (fin == null) {
			throw new IllegalArgumentException("fin ne peut pas être null");
		}
		if (tache == null) {
			throw new IllegalArgumentException("tache ne peut pas être null");
		}
		if (developpeur == null) {
			throw new IllegalArgumentException("developpeur ne peut pas être null");
		}

		//! Because of a bug inside the IntervalleInstants class,
		//! we need to check if `debut` is before `fin` manually.
		if (debut.isAfter(fin) || debut.equals(fin)) {
			throw new IllegalArgumentException("debut ne peut pas être après fin");
		}
		this.intervalle = new IntervalleInstants(debut, fin);

		if (!developpeur.estActif()) {
			throw new OperationImpossible("le développeur n'est pas actif");
		}
		this.developpeur = developpeur;
		this.developpeur.ajouterPeriodeDeTravail(this);

		if (!tache.estActif()) {
			throw new OperationImpossible("la tâche n'est pas actif");
		}
		this.tache = tache;
		this.tache.ajouterPeriodeDeTravail(this);

		assert invariant();
	}

	/**
	 * @return l'invariant de la classe.
	 */
	public boolean invariant() {
		return intervalle != null
				&& developpeur != null
				&& tache != null;
	}

	//#region Getters

	/**
	 * @return l'intervalle de temps de la période de travail.
	 */
	public IntervalleInstants getIntervalle() {
		return intervalle;
	}

	/**
	 * @return le développeur qui a travaillé sur la tâche.
	 */
	public IDeveloppeur getDeveloppeur() {
		return developpeur;
	}

	/**
	 * @return la tâche sur laquelle le développeur a travaillé.
	 */
	public ITache getTache() {
		return tache;
	}
	//#endregion

	@Override
	protected void specificMettreALaCorbeille() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PeriodeDeTravail [" + intervalle.getInstantDebut() + "->" + intervalle.getInstantFin()
				+ " | Durée:" + intervalle.calculerDuree() + "]");
		builder.append("\n\t↳ ");
		builder.append(developpeur.toString());
		builder.append("\n\t↳ ");
		builder.append(tache.toString());
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((developpeur == null) ? 0 : developpeur.hashCode());
		result = prime * result + ((intervalle == null) ? 0 : intervalle.hashCode());
		result = prime * result + ((tache == null) ? 0 : tache.hashCode());
		return result;
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
		PeriodeDeTravail other = (PeriodeDeTravail) obj;
		if (!developpeur.equals(other.developpeur)) {
			return false;
		} else if (!intervalle.equals(other.intervalle)) {
			return false;
		} else if (!tache.equals(other.tache)) {
			return false;
		}
		return true;
	}
}
