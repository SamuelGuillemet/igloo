package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class PeriodeDeTravail extends ElementJetable implements IPeriodeDeTravail {
	private final IntervalleInstants intervalle;

	private final IDeveloppeur developpeur;
	private final ITache tache;

	public PeriodeDeTravail(final Instant debut, final Instant fin, final Tache tache, final Developpeur developpeur)
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
		if (debut.isAfter(fin)) {
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

	public boolean invariant() {
		return intervalle != null
				&& developpeur != null
				&& tache != null;
	}

	//#region Getters
	public IntervalleInstants getIntervalle() {
		return intervalle;
	}

	public IDeveloppeur getDeveloppeur() {
		return developpeur;
	}

	public ITache getTache() {
		return tache;
	}
	//#endregion

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

}
