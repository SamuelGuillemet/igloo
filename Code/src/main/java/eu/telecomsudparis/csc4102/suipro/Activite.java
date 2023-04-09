package eu.telecomsudparis.csc4102.suipro;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept d'activité. Une activité est un élément jetable
 * référençant une collection de tâches.
 */
public final class Activite extends ElementJetable implements IActivite {
    /**
     * nom de l'activité.
     */
    private final String nom;

    /**
     * identifiant de l'activité.
     */
    private final String id;

    /**
     * tâches de l'activité.
     */
    private LinkedHashMap<String, ITache> taches;

    /**
     * @param nom
     * @param id
     * @throws OperationImpossible
     */
    @Deprecated
    public Activite(final String nom, final String id) throws OperationImpossible {
        if (nom == null || nom.isBlank()) {
            throw new OperationImpossible("Le nom ne peut pas être null ou vide.");
        }
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("L'id ne peut pas être null ou vide.");
        }

        this.nom = nom;
        this.id = id;
        this.taches = new LinkedHashMap<>();
    }

    public Activite(final String nom, final String id, final ICorbeille corbeille)
            throws OperationImpossible {
        this(nom, id);
        if (corbeille == null) {
            throw new OperationImpossible("La corbeille ne peut pas être null.");
        }
        this.setCorbeille(corbeille);
        assert invariant();
    }

    /**
     * @return true si l'invariant est respecté, false sinon
     */
    public boolean invariant() {
        return nom != null && !nom.isBlank()
                && id != null && !id.isBlank()
                && taches != null
                && super.invariant();
    }

    //#region Getters

    /**
     * @return le nom de l'activité
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return l'id de l'activité
     */
    public String getId() {
        return id;
    }

    /**
     * @return la liste des tâches de l'activité
     */
    public Collection<ITache> getTaches() {
        return taches.values();
    }

    /**
     * @param id
     * @return la tâche correspondant à l'id
     */
    public ITache getTache(final String id) {
        return taches.get(id);
    }

    //#endregion

    /**
     * @param tache
     * @throws OperationImpossible
     */
    public void ajouterTache(final ITache tache) throws OperationImpossible {
        if (tache == null) {
            throw new OperationImpossible("La tâche ne peut pas être null.");
        }
        if (taches.containsValue(tache)) {
            throw new OperationImpossible("La tâche est déjà associée à cette activité.");
        }
        if (!tache.estEnFonctionnement()) {
            throw new OperationImpossible("La tâche doit être active.");
        }
        if (tache.getActivite() != this) {
            throw new OperationImpossible("La tâche doit être associée à cette activité.");
        }
        if (!this.estEnFonctionnement()) {
            throw new OperationImpossible("L'activité doit être active.");
        }

        taches.put(tache.getId(), tache);

        assert invariant();
    }

    @Override
    protected void specificMettreALaCorbeille() throws OperationImpossible {
        for (ITache tache : taches.values()) {
            tache.mettreALaCorbeille();
        }
    }

    @Override
    protected void specificRestaurer() throws OperationImpossible {
        for (ITache tache : taches.values()) {
            tache.restaurer();
        }
    }

    @Override
    public String toString() {
        return "Activite [id=" + id + ", nom=" + nom + ", enFonctionnement=" + estEnFonctionnement() + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Activite other = (Activite) obj;
        if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * This method is called when an event is fire by the Corbeille when a Tache is removed from it.
     * 
     * @param evt {@code source} must be a {@link Corbeille} and {@code propertyName} must be {@link Tache} string.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getSource().getClass() != Corbeille.class) {
            return;
        }
        if (!evt.getPropertyName().equals(Tache.class.getSimpleName())) {
            return;
        }
        if (evt.getNewValue() != null) {
            return;
        }
        ITache tache = (ITache) evt.getOldValue();
        taches.remove(tache.getId());

        assert invariant();
    }
}
