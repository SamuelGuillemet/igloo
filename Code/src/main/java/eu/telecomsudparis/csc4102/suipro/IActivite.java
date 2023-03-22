package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public interface IActivite extends IElementJetable {
    boolean invariant();

    void ajouterTache(ITache tache) throws OperationImpossible;

    String getNom();

    String getId();

    Collection<ITache> getTaches();

    ITache getTache(String id);
}
