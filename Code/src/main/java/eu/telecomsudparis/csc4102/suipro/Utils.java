package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

enum PrintType {
    /** 
     * Tout les résultats.
     */
    ALL,
    /** 
     * Seulement les actifs. 
     * C'est à dire ceux qui sont en fonctionnement.
     */
    ACTIF,
    /** 
     * Seulement les inactifs.
     * C'est à dire ceux qui sont à la corbeille.
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
     * @throws OperationImpossible
     */
    public static <T extends IElementJetable> List<T> filterPrintType(final Collection<T> collection,
            final PrintType printType) throws OperationImpossible {
        switch (printType) {
            case ALL:
                return collection.stream().toList();
            case ACTIF:
                return collection.stream().filter(IElementJetable::estEnFonctionnement).toList();
            case INACTIF:
                return collection.stream().filter(e -> !e.estEnFonctionnement()).toList();
            default:
                throw new OperationImpossible("Invalid print type");
        }
    }

    /**
     * @param collection
     * @return Une chaîne de caractères représentant la collection.
     */
    public static String printCollection(final Collection<? extends IElementJetable> collection) {
        String result = collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(result);
        return result;
    }
}
