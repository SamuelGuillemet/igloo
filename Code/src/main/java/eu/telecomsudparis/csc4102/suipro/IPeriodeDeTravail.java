// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;

/**
 * Cette interface définit le concept de période de travail. Une période de travail
 * est un élément jetable référençant un développeur, une tâche et un intervalle
 * d'instant.
 */
public interface IPeriodeDeTravail extends IElementJetable {

    IntervalleInstants getIntervalle();

    IDeveloppeur getDeveloppeur();

    ITache getTache();

    double calculerTempsDeTravail();
}
