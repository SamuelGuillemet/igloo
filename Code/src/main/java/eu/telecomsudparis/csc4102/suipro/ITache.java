// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette interface définit le concept de tâche. Une tâche est un élément jetable
 * référençant une activité et une collection de périodes de travail.
 */
public interface ITache extends IElementJetable, Labelisable {

    String getId();

    Collection<IPeriodeDeTravail> getPeriodesDeTravail();

    IActivite getActivite();

    void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible;

    double calculerTempsDeTravail();
}
