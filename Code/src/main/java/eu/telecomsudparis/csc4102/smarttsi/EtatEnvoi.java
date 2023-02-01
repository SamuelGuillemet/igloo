package eu.telecomsudparis.csc4102.smarttsi;

/**
 * Cette énumération modélise l'état d'un envoi.
 * 
 * NB: À compléter. Supprimer ce commentaire ensuite.
 * 
 * @author Denis Conan
 */
public enum EtatEnvoi {
	/**
	 * l'envoi est encore chez le client.
	 */
	CHEZ_LE_CLIENT_EXPEDITEUR("chez le client expéditeur");
	
	/**
	 * le nom de l'état.
	 */
	private String nom;
	
	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'énumérateur.
	 */
	EtatEnvoi(final String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
