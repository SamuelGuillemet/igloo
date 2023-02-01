package eu.telecomsudparis.csc4102.smarttsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit la façade du système.
 * 
 * @author Denis Conan
 */
public class SmartTSI {
	/**
	 * la collection des envois.
	 */
	private Map<String, Envoi> envois;

	/**
	 * construit une façade.
	 */
	public SmartTSI() {
		envois = new HashMap<>();
	}

	/**
	 * déposer un envoi. Cette opération est effectuée par un client.
	 * 
	 * NB: À compléter. Supprimer ce commentaire ensuite.
	 * 
	 * @param identifiantEnvoi l'identifiant de l'envoi.
	 * @param origine          l'origine de l'envoi.
	 * @param destination      la destination de l'envoi.
	 * @throws OperationImpossible l'opération ne peut pas être effectuée.
	 */
	public void deposerUnEnvoi(final String identifiantEnvoi, final String origine, final String destination)
			throws OperationImpossible {
		if (identifiantEnvoi == null || identifiantEnvoi.isBlank()) {
			throw new OperationImpossible("l'identifiant de l'envoi ne peut pas être null ou vide");
		}
		if (origine == null || origine.isBlank()) {
			throw new OperationImpossible("l'origine ne peut pas être null ou vide");
		}
		if (destination == null || destination.isBlank()) {
			throw new OperationImpossible("la destination ne peut pas être null ou vide");
		}
		if (envois.containsKey(identifiantEnvoi)) {
			throw new OperationImpossible("il existe déjà un envoi avec le même identifiant");
		}
		Envoi envoi = new Envoi(identifiantEnvoi, origine, Datutil.maintenant(), destination);
		envois.put(identifiantEnvoi, envoi);
	}

	/**
	 * liste les envois.
	 * 
	 * @return les identifiants des envois existants.
	 **/
	public List<String> listeLesEnvois() {
		return new ArrayList<>(this.envois.keySet());
	}
}
