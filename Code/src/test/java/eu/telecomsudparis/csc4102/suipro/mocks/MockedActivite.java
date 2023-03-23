package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.Collection;
import java.util.LinkedHashMap;

import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedActivite implements IActivite {

    private LinkedHashMap<String, ITache> taches;
    private boolean estActif;
    public int ajouterTacheCalledTimes;

    public MockedActivite(boolean estActif) {
        this.estActif = estActif;
        this.ajouterTacheCalledTimes = 0;
        this.taches = new LinkedHashMap<>();
    }

    @Override
    public boolean estActif() {
        return estActif;
    }

    @Override
    public void mettreALaCorbeille() {
        throw new UnsupportedOperationException("Unimplemented method 'mettreALaCorbeille'");
    }

    @Override
    public void ajouterTache(ITache tache) throws OperationImpossible {
        this.taches.put(tache.getId(), tache);
        ajouterTacheCalledTimes++;
    }

    @Override
    public String getNom() {
        throw new UnsupportedOperationException("Unimplemented method 'getNom'");
    }

    @Override
    public String getId() {
        return "id";
    }

    @Override
    public Collection<ITache> getTaches() {
        throw new UnsupportedOperationException("Unimplemented method 'getTaches'");
    }

    @Override
    public ITache getTache(String id) {
        return taches.get(id);
    }

}
