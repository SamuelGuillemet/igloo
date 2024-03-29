package eu.telecomsudparis.csc4102.suipro;

import java.util.concurrent.Flow.Subscription;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * ElementJetable est la classe abstraite des objets jetables.
 * Ils peuvent être jetés à la corbeille.
 *
 * Un objet jetable peut être en fonctionnement ou à la corbeille.
 * Il est en fonctionnement par défaut.
 */
public abstract class ElementJetable implements IElementJetable {
    /**
     * indique si l'élément est en fonctionnement ou non.
     */
    private boolean enFonctionnement = true;

    /**
     *
     */
    private Subscription subscription;

    /**
     * @return true si l'élément est en fonctionnement, false sinon
     */
    public final boolean estEnFonctionnement() {
        return this.enFonctionnement;
    }

    /**
     * met l'élément à la corbeille.
     * 
     * @param corbeille
     */
    public final void mettreALaCorbeille(final ICorbeille corbeille) throws OperationImpossible {
        if (corbeille == null) {
            throw new OperationImpossible("La corbeille ne peut pas être null.");
        }
        this.enFonctionnement = false;
        corbeille.ajouterALaCorbeille(this);
        this.specificMettreALaCorbeille(corbeille);
    }

    protected abstract void specificMettreALaCorbeille(ICorbeille corbeille) throws OperationImpossible;

    /**
     * restaure l'élément.
     * We are using the IllegalStateException to indicate that the element cannot be restored,
     * for example if one of its "parent" is still in the trash.
     * 
     * @param corbeille
     */
    public final void restaurer(final ICorbeille corbeille) throws OperationImpossible {
        if (corbeille == null) {
            throw new OperationImpossible("La corbeille ne peut pas être null.");
        }
        this.enFonctionnement = true;
        try {
            this.specificRestaurer(corbeille);
            corbeille.supprimerDeLaCorbeille(this);
        } catch (IllegalStateException e) {
            this.enFonctionnement = false;
            // System.err.println(e.getMessage());
        }
    }

    protected abstract void specificRestaurer(ICorbeille corbeille) throws IllegalStateException, OperationImpossible;

    @Override
    public void onSubscribe(final Subscription souscription) {
        this.subscription = souscription;
        request();
    }

    protected void request() {
        this.subscription.request(1);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(final Throwable arg0) {
    }
}
