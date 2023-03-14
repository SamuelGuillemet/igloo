package eu.telecomsudparis.csc4102.suipro;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Cette classe définit un ordre total sur les périodes de travail. Elle permet
 * de déclarer des collections triées (par exemple, en écrivant
 * <code>SortedSet&lt;PeriodeDeTravail&gt; periodesDeTravail</code>) et de créer des
 * collections triées (par exemple, en écrivant
 * <code>periodesDeTravail = new TreeSet&lt;&gt;(new ComparateurPeriodesDeTravail())</code>).
 */
public class ComparateurPeriodesDeTravail implements Comparator<PeriodeDeTravail>, Serializable {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * compare deux périodes de travail selon (1) l'instant de début de leur
	 * intervalle, puis, en cas d'égalité, selon l'instant de fin de leur
	 * intervalle.
	 * 
	 * NB : on peut ajouter des critères de comparaison. Voir ci dessous par exemple
	 * l'alias du développeur.
	 * 
	 * @param periode1 première période de travail.
	 * @param periode2 seconde pérode de travail.
	 * @return un entier négatif, zéro, ou un entier positif selon que le premier
	 *         objet est « inférieur », « égal », ou « supérieur » au second objet.
	 */
	@Override
	public int compare(final PeriodeDeTravail periode1, final PeriodeDeTravail periode2) {
		if (periode1 == null) {
			throw new IllegalArgumentException("la première période ne peut pas être null");
		}
		if (periode2 == null) {
			throw new IllegalArgumentException("la seconde période ne peut pas être null");
		}
		return Comparator.comparing((PeriodeDeTravail p) -> p.getIntervalle().getInstantDebut())
				.thenComparing((PeriodeDeTravail p) -> p.getIntervalle().getInstantFin())
				// TODO ajouter le critère de comparaison sur l'alias du développeur avec par
				// exemple
				// .thenComparing((PeriodeDeTravail p) -> p.getDeveloppeur().getAlias())
				.compare(periode1, periode2);
	}
}
