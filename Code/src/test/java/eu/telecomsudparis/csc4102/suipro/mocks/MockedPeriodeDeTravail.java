// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.beans.PropertyChangeEvent;
import java.time.Instant;

import eu.telecomsudparis.csc4102.suipro.ICorbeille;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.util.IntervalleInstants;

public class MockedPeriodeDeTravail implements IPeriodeDeTravail {

    private IDeveloppeur developpeur;
    private ITache tache;
    private boolean enFonctionnement;

    public int mettreALaCorbeilleCalledTimes;
    public int restaurerCalledTimes;

    public int calculerTempsDeTravailCalledTimes;

    public MockedPeriodeDeTravail(IDeveloppeur developpeur, boolean enFonctionnement) {
        this(enFonctionnement);
        this.developpeur = developpeur;
    }

    public MockedPeriodeDeTravail(ITache tache, boolean enFonctionnement) {
        this(enFonctionnement);
        this.tache = tache;
    }

    public MockedPeriodeDeTravail(boolean enFonctionnement) {
        this.enFonctionnement = enFonctionnement;
        this.mettreALaCorbeilleCalledTimes = 0;
        this.restaurerCalledTimes = 0;
        this.calculerTempsDeTravailCalledTimes = 0;
    }

    @Override
    public boolean estEnFonctionnement() {
        return enFonctionnement;
    }

    @Override
    public void mettreALaCorbeille(ICorbeille corbeille) {
        enFonctionnement = false;
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

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    @Override
    public void restaurer(ICorbeille corbeille) {
        enFonctionnement = true;
        restaurerCalledTimes++;
    }

    @Override
    public double calculerTempsDeTravail() {
        calculerTempsDeTravailCalledTimes++;
        return 0;
    }
}
