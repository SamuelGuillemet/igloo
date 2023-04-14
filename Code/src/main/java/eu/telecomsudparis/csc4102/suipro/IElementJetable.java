// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.beans.PropertyChangeListener;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette interface définit le concept d'élément jetable. Un élément jetable est un
 * élément pouvant être mis à la corbeille.
 */
public interface IElementJetable extends PropertyChangeListener {
    boolean estEnFonctionnement();

    void mettreALaCorbeille(ICorbeille corbeille) throws OperationImpossible;

    void restaurer(ICorbeille corbeille) throws OperationImpossible;
}
