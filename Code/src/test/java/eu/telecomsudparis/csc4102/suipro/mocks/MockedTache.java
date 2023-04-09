// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import eu.telecomsudparis.csc4102.suipro.IActivite;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.suipro.ITache;
import eu.telecomsudparis.csc4102.suipro.Label;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedTache implements ITache {
    private IActivite activite;
    private boolean enFonctionnement;
    private boolean throwError;

    public int mettreALaCorbeilleCalledTimes;
    public int restaurerCalledTimes;

    public int calculerTempsDeTravailCalledTimes;

    public MockedTache(boolean enFonctionnement, boolean throwError) {
        this.enFonctionnement = enFonctionnement;
        this.throwError = throwError;
    }

    public MockedTache(IActivite activite, boolean enFonctionnement) {
        this.activite = activite;
        this.enFonctionnement = enFonctionnement;
        this.mettreALaCorbeilleCalledTimes = 0;
        this.restaurerCalledTimes = 0;
        this.calculerTempsDeTravailCalledTimes = 0;
    }

    public MockedTache(IActivite activite) {
        this(activite, true);
    }

    public MockedTache(boolean enFonctionnement) {
        this(enFonctionnement, false);
    }

    @Override
    public boolean estEnFonctionnement() {
        return enFonctionnement;
    }

    @Override
    public void mettreALaCorbeille() {
        enFonctionnement = false;
        mettreALaCorbeilleCalledTimes++;
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

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    @Override
    public void restaurer() {
        enFonctionnement = true;
        restaurerCalledTimes++;
    }

    public void setEnFonctionnement(boolean enFonctionnement) {
        this.enFonctionnement = enFonctionnement;
    }

    @Override
    public double calculerTempsDeTravail() {
        calculerTempsDeTravailCalledTimes++;
        return 0;
    }

    @Override
    public void ajouterLabel(Label label) throws OperationImpossible {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterLabel'");
    }

    @Override
    public List<Label> getLabels() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLabels'");
    }
}
