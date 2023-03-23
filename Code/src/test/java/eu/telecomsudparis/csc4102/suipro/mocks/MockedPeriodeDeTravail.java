package eu.telecomsudparis.csc4102.suipro.mocks;

import java.time.Instant;

import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.IntervalleInstants;

public class MockedPeriodeDeTravail implements IPeriodeDeTravail {

    private IDeveloppeur developpeur;
    private ITache tache;
    private boolean estActif;

    public int mettreALaCorbeilleCalledTimes;

    public MockedPeriodeDeTravail(IDeveloppeur developpeur, boolean estActif) {
        this(estActif);
        this.developpeur = developpeur;
    }

    public MockedPeriodeDeTravail(ITache tache, boolean estActif) {
        this(estActif);
        this.tache = tache;
    }

    public MockedPeriodeDeTravail(boolean estActif) {
        this.estActif = estActif;
        this.mettreALaCorbeilleCalledTimes = 0;
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
    public IntervalleInstants getIntervalle() {
        return new IntervalleInstants(Instant.MIN, Instant.MAX);
    }

    @Override
    public IDeveloppeur getDeveloppeur() {
        return developpeur;
    }

    @Override
    public ITache getTache() {
        return tache;
    }
}
