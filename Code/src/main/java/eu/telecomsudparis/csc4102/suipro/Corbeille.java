package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Corbeille {
    private static Corbeille instance;

    private LinkedHashSet<IElementJetable> elementsJetable;

    private Corbeille() {
        this.elementsJetable = new LinkedHashSet<>();
        assert invariant();
    }

    public static Corbeille getInstance() {
        if (instance == null) {
            instance = new Corbeille();
        }
        return instance;
    }

    private boolean invariant() {
        return this.elementsJetable != null;
    }

    public void ajouterALaCorbeille(final IElementJetable elementJetable) throws IllegalArgumentException {
        Objects.requireNonNull(elementJetable);
        this.elementsJetable.add(elementJetable);
    }

    public void supprimerDeLaCorbeille(final IElementJetable elementJetable) throws IllegalArgumentException {
        Objects.requireNonNull(elementJetable);
        this.elementsJetable.remove(elementJetable);
    }

    public <T extends IElementJetable> ArrayList<T> getElementsJetable(final Class<T> type) {
        Objects.requireNonNull(type);

        return this.elementsJetable.stream()
                .filter(elementJetable -> type.isInstance(elementJetable))
                .map(elementJetable -> type.cast(elementJetable))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
