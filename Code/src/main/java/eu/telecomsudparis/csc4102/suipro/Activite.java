package eu.telecomsudparis.csc4102.suipro;

import java.util.Collection;
import java.util.LinkedHashMap;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Activite extends ElementJetable implements IActivite {
    private final String nom;
    private final String id;

    private LinkedHashMap<String, ITache> taches;

    public Activite(final String nom, final String id) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas être null ou vide.");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id ne peut pas être null ou vide.");
        }

        this.nom = nom;
        this.id = id;
        this.taches = new LinkedHashMap<>();

        assert invariant();
    }

    public boolean invariant() {
        return nom != null && !nom.isBlank()
                && id != null && !id.isBlank()
                && taches != null;
    }

    //#region Getters

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public Collection<ITache> getTaches() {
        return taches.values();
    }

    public ITache getTache(final String id) {
        return taches.get(id);
    }

    //#endregion

    public void ajouterTache(final ITache tache) throws OperationImpossible {
        if (tache == null) {
            throw new IllegalArgumentException("La tâche ne peut pas être null.");
        }
        if (taches.containsValue(tache)) {
            throw new OperationImpossible("La tâche est déjà associée à cette activité.");
        }
        if (!tache.estActif()) {
            throw new OperationImpossible("La tâche doit être active.");
        }
        if (tache.getActivite() != this) {
            throw new OperationImpossible("La tâche doit être associée à cette activité.");
        }
        if (!this.estActif()) {
            throw new OperationImpossible("L'activité doit être active.");
        }

        taches.put(tache.getId(), tache);

        assert invariant();
    }

    @Override
    public void mettreALaCorbeille() {
        super.mettreALaCorbeille();
        for (ITache tache : taches.values()) {
            tache.mettreALaCorbeille();
        }
    }

    @Override
    public String toString() {
        return "Activite [id=" + id + ", nom=" + nom + ", actif=" + estActif() + "]";
    }
}
