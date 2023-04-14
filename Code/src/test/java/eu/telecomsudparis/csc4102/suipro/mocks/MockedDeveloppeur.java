// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import eu.telecomsudparis.csc4102.suipro.ICorbeille;
import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedDeveloppeur implements IDeveloppeur {
    private boolean enFonctionnement;
    private boolean throwError;

    public MockedDeveloppeur(boolean enFonctionnement, boolean throwError) {
        this.enFonctionnement = enFonctionnement;
        this.throwError = throwError;
    }

    public MockedDeveloppeur(boolean enFonctionnement) {
        this(enFonctionnement, false);
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
    public ArrayList<IPeriodeDeTravail> getPeriodesDeTravail() {
        throw new UnsupportedOperationException("Unimplemented method 'getPeriodesDeTravail'");
    }

    @Override
    public void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible {
        if (throwError) {
            throw new OperationImpossible("MockedDeveloppeur: ajouterPeriodeDeTravail");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    @Override
    public void restaurer(ICorbeille corbeille) {
        throw new UnsupportedOperationException("Unimplemented method 'restaurer'");
    }

    public void setEnFonctionnement(boolean enFonctionnement) {
        this.enFonctionnement = enFonctionnement;
    }

    @Override
    public String getAlias() {
        throw new UnsupportedOperationException("Unimplemented method 'getAlias'");
    }

    @Override
    public double calculerTempsDeTravail() {
        throw new UnsupportedOperationException("Unimplemented method 'calculerTempsDeTravail'");
    }
}
