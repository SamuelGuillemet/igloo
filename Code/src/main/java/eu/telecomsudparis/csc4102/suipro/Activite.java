package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;

public class Activite extends ElementJetable {
    private final String nom;
    private final String id;

    private ArrayList<Tache> taches;

    public Activite(final String nom, final String id) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas être null ou vide.");
        }

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id ne peut pas être null ou vide.");
        }

        this.nom = nom;
        this.id = id;

        assert invariant();
    }

    public boolean invariant() {
        return nom != null && !nom.isBlank() && id != null && !id.isBlank();
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public void ajouterTache(final Tache tache) {
        if (tache == null) {
            throw new IllegalArgumentException("La tâche ne peut pas être null.");
        }

        if (!tache.estActif()) {
            throw new IllegalArgumentException("La tâche doit être active.");
        }

        if (tache.getActivite() != this) {
            throw new IllegalArgumentException("La tâche doit être associée à cette activité.");
        }

        taches.add(tache);
        assert invariant();
    }

    @Override
    public void mettreALaCorbeille() {
        super.mettreALaCorbeille();
        for (Tache tache : taches) {
            tache.mettreALaCorbeille();
        }
    }

    @Override
    public void restaurer() {
        super.restaurer();
        for (Tache tache : taches) {
            tache.restaurer();
        }
    }

    @Override
    public String toString() {
        return "Activite [id=" + id + ", nom=" + nom + "]";
    }
}
