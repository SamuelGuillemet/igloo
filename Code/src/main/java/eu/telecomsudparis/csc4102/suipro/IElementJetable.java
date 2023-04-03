// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

/**
 * Cette interface définit le concept d'élément jetable. Un élément jetable est un
 * élément pouvant être mis à la corbeille.
 */
public interface IElementJetable {
    boolean estActif();

    void mettreALaCorbeille();
}
