package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public interface IDeveloppeur extends IElementJetable {
    void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible;

    String getAlias();

    String getNom();

    String getPrenom();

    ArrayList<IPeriodeDeTravail> getPeriodesDeTravail();
}
