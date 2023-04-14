// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Flow.Subscription;

import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IElementJetable;
import eu.telecomsudparis.csc4102.suipro.ILabel;
import eu.telecomsudparis.csc4102.suipro.ICorbeille;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedActivite implements IActivite {

    private LinkedHashMap<String, ITache> taches;
    private boolean enFonctionnement;
    public int ajouterTacheCalledTimes;

    public MockedActivite(boolean enFonctionnement) {
        this.enFonctionnement = enFonctionnement;
        this.ajouterTacheCalledTimes = 0;
        this.taches = new LinkedHashMap<>();
    }

    @Override
    public boolean estEnFonctionnement() {
        return enFonctionnement;
    }

    @Override
    public void mettreALaCorbeille(ICorbeille corbeille) {
        throw new UnsupportedOperationException("Unimplemented method 'mettreALaCorbeille'");
    }

    @Override
    public void ajouterTache(ITache tache) throws OperationImpossible {
        this.taches.put(tache.getId(), tache);
        ajouterTacheCalledTimes++;
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

    @Override
    public void restaurer(ICorbeille corbeille) {
        throw new UnsupportedOperationException("Unimplemented method 'restaurer'");
    }

    public void setEnFonctionnement(boolean enFonctionnement) {
        this.enFonctionnement = enFonctionnement;
    }

    @Override
    public double calculerTempsDeTravail() {
        throw new UnsupportedOperationException("Unimplemented method 'calculerTempsDeTravail'");
    }

    @Override
    public void ajouterLabel(ILabel label) throws OperationImpossible {
        throw new UnsupportedOperationException("Unimplemented method 'ajouterLabel'");
    }

    @Override
    public List<ILabel> getLabels() {
        throw new UnsupportedOperationException("Unimplemented method 'getLabels'");
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Unimplemented method 'onComplete'");
    }

    @Override
    public void onError(Throwable arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }

    @Override
    public void onNext(IElementJetable arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'onNext'");
    }

    @Override
    public void onSubscribe(Subscription arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'onSubscribe'");
    }
}
