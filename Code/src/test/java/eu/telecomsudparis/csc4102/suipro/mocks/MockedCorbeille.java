// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.mocks;

import java.util.HashMap;
import java.util.List;

import eu.telecomsudparis.csc4102.suipro.ICorbeille;
import eu.telecomsudparis.csc4102.suipro.IElementJetable;

public class MockedCorbeille implements ICorbeille {
    private HashMap<IElementJetable, int[]> ajoutElementJetables;
    private HashMap<IElementJetable, int[]> suppressionElementJetables;

    public MockedCorbeille() {
        ajoutElementJetables = new HashMap<>();
        suppressionElementJetables = new HashMap<>();
    }

    public void ajouterALaCorbeille(final IElementJetable elementJetable) {
        ajoutElementJetables.put(elementJetable, new int[] { 1 });
    }

    public void supprimerDeLaCorbeille(final IElementJetable elementJetable) {
        suppressionElementJetables.put(elementJetable, new int[] { 1 });
    }

    public int getNbAjout(final IElementJetable elementJetable) {
        return ajoutElementJetables.get(elementJetable)[0];
    }

    public int getNbSuppression(final IElementJetable elementJetable) {
        return suppressionElementJetables.get(elementJetable)[0];
    }

    @Override
    public <T extends IElementJetable> List<T> getElementsJetable(Class<T> type) {
        throw new UnsupportedOperationException("Unimplemented method 'getElementsJetable'");
    }

    @Override
    public void viderLaCorbeille() {
        throw new UnsupportedOperationException("Unimplemented method 'viderLaCorbeille'");
    }

}
