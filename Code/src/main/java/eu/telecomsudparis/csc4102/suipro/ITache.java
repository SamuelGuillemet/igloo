package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public interface ITache extends IElementJetable {

    String getNom();

    String getId();

    ArrayList<IPeriodeDeTravail> getPeriodesDeTravail();

    IActivite getActivite();

    void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible;
}
