package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedTache implements ITache {
    private IActivite activite;
    private boolean estActif;
    private boolean throwError;

    public int mettreALaCorbeilleCalledTimes;

    public MockedTache(boolean estActif, boolean throwError) {
        this.estActif = estActif;
        this.throwError = throwError;
    }

    public MockedTache(IActivite activite, boolean estActif) {
        this.activite = activite;
        this.estActif = estActif;
        this.mettreALaCorbeilleCalledTimes = 0;
    }

    public MockedTache(IActivite activite) {
        this(activite, true);
    }

    public MockedTache(boolean estActif) {
        this(estActif, false);
    }

    @Override
    public boolean estActif() {
        return estActif;
    }

    @Override
    public void mettreALaCorbeille() {
        estActif = false;
        mettreALaCorbeilleCalledTimes++;
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
    public ArrayList<IPeriodeDeTravail> getPeriodesDeTravail() {
        throw new UnsupportedOperationException("Unimplemented method 'getPeriodesDeTravail'");
    }

    @Override
    public IActivite getActivite() {
        return activite;
    }

    @Override
    public void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible {
        if (throwError) {
            throw new OperationImpossible("MockedTache: ajouterPeriodeDeTravail");
        }
    }
}