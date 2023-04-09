//CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.util.List;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette interface introduit le concept de labelisable. Un labelisable est un
 * élément pouvant être associé à un ou plusieurs labels.
 */
public interface Labelisable {
    void ajouterLabel(Label label) throws OperationImpossible;

    List<Label> getLabels();
}
