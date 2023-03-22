package eu.telecomsudparis.csc4102.suipro;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;

public interface IPeriodeDeTravail extends IElementJetable {

    IntervalleInstants getIntervalle();

    IDeveloppeur getDeveloppeur();

    ITache getTache();
}
