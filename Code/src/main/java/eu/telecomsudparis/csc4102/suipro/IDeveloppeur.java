// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette interface définit le concept de développeur. Un développeur est un élément
 * jetable référençant une collection de période de travail.
 */
public interface IDeveloppeur extends IElementJetable {
    void ajouterPeriodeDeTravail(IPeriodeDeTravail periodeDeTravail) throws OperationImpossible;

    String getAlias();

    String getNom();

    String getPrenom();

    Collection<IPeriodeDeTravail> getPeriodesDeTravail();
}
