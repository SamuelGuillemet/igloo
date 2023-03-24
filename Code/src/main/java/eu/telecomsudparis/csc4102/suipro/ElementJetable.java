package eu.telecomsudparis.csc4102.suipro;

public abstract class ElementJetable implements IElementJetable {
    private boolean actif = true;

    public boolean estActif() {
        return this.actif;
    }

    public void mettreALaCorbeille() {
        this.actif = false;
        Corbeille.getInstance().ajouterALaCorbeille(this);
    }
}
