package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Cette classe réalise le concept de corbeille. Une corbeille est un singleton
 * référençant une collection d'éléments jetables.
 */
public final class Corbeille {
    /**
     * instance de la corbeille.
     */
    private static Corbeille instance;

    /**
     * éléments jetables de la corbeille.
     */
    private LinkedHashSet<IElementJetable> elementsJetable;

    private Corbeille() {
        this.elementsJetable = new LinkedHashSet<>();
        assert invariant();
    }

    /**
     * @return l'instance de la corbeille
     */
    public static Corbeille getInstance() {
        if (instance == null) {
            instance = new Corbeille();
        }
        return instance;
    }

    /**
     * @return true si l'invariant est respecté, false sinon
     */
    private boolean invariant() {
        return this.elementsJetable != null;
    }

    /**
     * @param elementJetable
     * @throws IllegalArgumentException si l'élément jetable est null
     */
    public void ajouterALaCorbeille(final IElementJetable elementJetable) throws IllegalArgumentException {
        if (elementJetable == null) {
            throw new IllegalArgumentException("L'élément jetable ne peut pas être null.");
        }
        this.elementsJetable.add(elementJetable);
    }

    /**
     * @param elementJetable
     * @throws IllegalArgumentException si l'élément jetable est null
     */
    public void supprimerDeLaCorbeille(final IElementJetable elementJetable) throws IllegalArgumentException {
        if (elementJetable == null) {
            throw new IllegalArgumentException("L'élément jetable ne peut pas être null.");
        }
        this.elementsJetable.remove(elementJetable);
    }

    /**
     * @param type le type des éléments jetables à récupérer
     * @param <T> le type des éléments jetables à récupérer
     * @return la liste des éléments jetables de la corbeille du type spécifié
     * @throws IllegalArgumentException si le type est null
     */
    public <T extends IElementJetable> ArrayList<T> getElementsJetable(final Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type ne peut pas être null.");
        }

        return this.elementsJetable.stream()
                .filter(elementJetable -> type.isInstance(elementJetable))
                .map(elementJetable -> type.cast(elementJetable))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Vide la corbeille.
     */
    public void viderLaCorbeille() {
        this.elementsJetable.clear();
    }
}
