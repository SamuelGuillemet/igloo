// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.beans.PropertyChangeEvent;
import eu.telecomsudparis.csc4102.suipro.IElementJetable;

public class MockedElementJetable implements IElementJetable {

    public MockedElementJetable() {
    }

    @Override
    public boolean estEnFonctionnement() {
        throw new UnsupportedOperationException("Unimplemented method 'estEnFonctionnement'");
    }

    @Override
    public void mettreALaCorbeille() {
        throw new UnsupportedOperationException("Unimplemented method 'mettreALaCorbeille'");
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    @Override
    public void restaurer() {
        throw new UnsupportedOperationException("Unimplemented method 'restaurer'");
    }
}
