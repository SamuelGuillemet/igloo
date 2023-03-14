package eu.telecomsudparis.csc4102.suipro;

import java.util.Objects;

/**
 * Cette classe réalise le concept de développeur. Un développeur est un élément
 * jetable référençant une collection de période de travail.
 * 
 * @author Denis Conan
 */
public class Developpeur {
	/**
	 * l'alias du développeur.
	 */
	private final String alias;
	/**
	 * le nom du développeur.
	 */
	private String nom;
	/**
	 * le prénom du développeur.
	 */
	private String prenom;

	/**
	 * construit un développeur.
	 * 
	 * @param alias  l'alias.
	 * @param nom    le nom.
	 * @param prenom le prénom.
	 */
	public Developpeur(final String alias, final String nom, final String prenom) {
		super();
		if (alias == null || alias.isBlank()) {
			throw new IllegalArgumentException("alias ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new IllegalArgumentException("prenom ne peut pas être null ou vide");
		}
		this.alias = alias;
		this.nom = nom;
		this.prenom = prenom;
		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return alias != null && !alias.isBlank() && nom != null && !nom.isBlank() && prenom != null
				&& !prenom.isBlank();
	}

	/**
	 * obtient l'alias.
	 * 
	 * @return l'alias.
	 */
	public String getAlias() {
		return alias;
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
		return "Developpeur [alias=" + alias + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
