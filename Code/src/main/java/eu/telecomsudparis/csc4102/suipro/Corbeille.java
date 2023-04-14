package eu.telecomsudparis.csc4102.suipro;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscriber;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept de corbeille. Une corbeille est un ensemble
 * d'éléments jetables.
 */
public final class Corbeille implements ICorbeille {
    /**
     * éléments jetables de la corbeille.
     */
    private final LinkedHashSet<IElementJetable> elementsJetable;

    /**
     * support de gestion des écouteurs de propriétés.
     */
    private final SubmissionPublisher<IElementJetable> producteur;

    /**
     * Constructeur.
     */
    public Corbeille() {
        this.elementsJetable = new LinkedHashSet<>();
        this.producteur = new SubmissionPublisher<>();
        assert invariant();
    }

    /**
     * @return true si l'invariant est respecté, false sinon
     */
    private boolean invariant() {
        return this.elementsJetable != null && this.producteur != null;
    }

    /**
     * @param elementJetable
     * @throws OperationImpossible si l'élément jetable est null
     */
    public void ajouterALaCorbeille(final IElementJetable elementJetable) throws OperationImpossible {
        if (elementJetable == null) {
            throw new OperationImpossible("L'élément jetable ne peut pas être null.");
        }
        this.elementsJetable.add(elementJetable);
        assert invariant();
    }

    /**
     * @param elementJetable
     * @throws OperationImpossible si l'élément jetable est null
     */
    public void supprimerDeLaCorbeille(final IElementJetable elementJetable) throws OperationImpossible {
        if (elementJetable == null) {
            throw new OperationImpossible("L'élément jetable ne peut pas être null.");
        }
        this.elementsJetable.remove(elementJetable);
        assert invariant();
    }

    /**
     * @param type le type des éléments jetables à récupérer
     * @param <T> le type des éléments jetables à récupérer
     * @return la liste des éléments jetables de la corbeille du type spécifié
     * @throws OperationImpossible si le type est null
     */
    public <T extends IElementJetable> List<T> getElementsJetable(final Class<T> type) throws OperationImpossible {
        if (type == null) {
            throw new OperationImpossible("Le type ne peut pas être null.");
        }

        return this.elementsJetable.stream()
                .filter(elementJetable -> type.isInstance(elementJetable))
                .map(elementJetable -> type.cast(elementJetable))
                .toList();
    }

    /**
     * Vide la corbeille.
     */
    public void viderLaCorbeille() {
        for (final IElementJetable elementJetable : this.elementsJetable) {
            this.producteur.submit(elementJetable);
        }
        this.elementsJetable.clear();
        assert invariant();
    }

    /**
     * @param subscriber
     */
    public void subscribe(final Subscriber<IElementJetable> subscriber) {
        this.producteur.subscribe(subscriber);
        assert invariant();
    }
}
