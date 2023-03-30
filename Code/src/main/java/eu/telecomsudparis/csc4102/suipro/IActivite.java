// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette interface définit le concept d'activité. Une activité est un élément jetable
 * référençant une collection de tâches.
 */
public interface IActivite extends IElementJetable {

    void ajouterTache(ITache tache) throws OperationImpossible;

    String getNom();

    String getId();

    Collection<ITache> getTaches();

    ITache getTache(String id);
}
