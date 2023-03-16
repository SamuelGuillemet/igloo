package eu.telecomsudparis.csc4102.suipro;

public abstract class ElementJetable {
    private boolean actif;

    public ElementJetable() {
        this.actif = true;
    }

    public boolean estActif() {
        return this.actif;
    }

    public void mettreALaCorbeille() {
        this.actif = false;
    }

    public void restaurer() {
        this.actif = true;
    }
}
