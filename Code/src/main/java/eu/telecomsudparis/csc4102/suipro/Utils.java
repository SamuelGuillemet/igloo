package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;
import java.util.List;

enum PrintType {
    /** 
     * Tout les résultats
     */
    ALL,
    /** 
     * Seulement les actifs
     */
    ACTIF,
    /** 
     * Seulement les inactifs
     */
    INACTIF
}

public final class Utils {
    private Utils() {
    }

    public static <T extends ElementJetable> List<T> filterPrintType(final Collection<T> collection,
            final PrintType printType) {
        switch (printType) {
            case ALL:
                return collection.stream().toList();
            case ACTIF:
                return collection.stream().filter(e -> e.estActif()).toList();
            case INACTIF:
                return collection.stream().filter(e -> !e.estActif()).toList();
            default:
                throw new IllegalArgumentException("Invalid print type");
        }
    }
}
