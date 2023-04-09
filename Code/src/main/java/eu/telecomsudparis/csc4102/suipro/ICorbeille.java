// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.util.List;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Interface de la corbeille.
 */
public interface ICorbeille {
    void ajouterALaCorbeille(IElementJetable element) throws OperationImpossible;

    void supprimerDeLaCorbeille(IElementJetable element) throws OperationImpossible;

    <T extends IElementJetable> List<T> getElementsJetable(Class<T> type) throws OperationImpossible;

    void viderLaCorbeille();
}
