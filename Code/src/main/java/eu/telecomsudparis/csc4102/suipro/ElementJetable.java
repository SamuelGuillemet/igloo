package eu.telecomsudparis.csc4102.suipro;

/**
 * Cette classe réalise le concept d'élément jetable. Un élément jetable est un
 * élément pouvant être mis à la corbeille.
 */
public abstract class ElementJetable implements IElementJetable {
    /**
     * indique si l'élément est actif ou non.
     */
    private boolean actif = true;

    /**
     * @return true si l'élément est actif, false sinon
     */
    public final boolean estActif() {
        return this.actif;
    }

    /**
     * met l'élément à la corbeille.
     */
    public final void mettreALaCorbeille() {
        this.actif = false;
        Corbeille.getInstance().ajouterALaCorbeille(this);
        this.specificMettreALaCorbeille();
    }

    protected abstract void specificMettreALaCorbeille();
}
