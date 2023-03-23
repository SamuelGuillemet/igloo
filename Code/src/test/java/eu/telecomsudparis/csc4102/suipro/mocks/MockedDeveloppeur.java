package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.suipro.IDeveloppeur;
import eu.telecomsudparis.csc4102.suipro.IPeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class MockedDeveloppeur implements IDeveloppeur {
    private boolean estActif;
    private boolean throwError;

    public MockedDeveloppeur(boolean estActif, boolean throwError) {
        this.estActif = estActif;
        this.throwError = throwError;
    }

    public MockedDeveloppeur(boolean estActif) {
        this(estActif, false);
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
    public String getAlias() {
        throw new UnsupportedOperationException("Unimplemented method 'getAlias'");
    }

    @Override
    public String getNom() {
        throw new UnsupportedOperationException("Unimplemented method 'getNom'");
    }

    @Override
    public String getPrenom() {
        throw new UnsupportedOperationException("Unimplemented method 'getPrenom'");
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
}
