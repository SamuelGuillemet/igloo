package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;
import java.util.List;

enum PrintType {
    /** 
     * Tout les résultats.
     */
    ALL,
    /** 
     * Seulement les actifs.
     */
    ACTIF,
    /** 
     * Seulement les inactifs.
     */
    INACTIF
}

/**
 * Classe utilitaire.
 */
public abstract class Utils {
    /**
     * @param <T>
     * @param collection
     * @param printType
     * @return Une liste filtrée en fonction du type d'impression.
     * @throws IllegalArgumentException
     */
    public static <T extends IElementJetable> List<T> filterPrintType(final Collection<T> collection,
            final PrintType printType) throws IllegalArgumentException {
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

    /**
     * @param collection
     * @return Une chaîne de caractères représentant la collection.
     */
    public static String printCollection(final Collection<? extends IElementJetable> collection) {
        StringBuilder sb = new StringBuilder();
        for (IElementJetable element : collection) {
            sb.append(element.toString());
            sb.append("\n");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        System.out.println(sb.toString());
        return sb.toString();
    }
}
