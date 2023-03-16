package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;

public class Tache extends ElementJetable {
    private final String nom;
    private final String id;

    private ArrayList<PeriodeDeTravail> periodesDeTravail;
    private Activite activite;

    public Tache(final String nom, final String id, final Activite activite) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas être null ou vide.");
        }

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id ne peut pas être null ou vide.");
        }

        if (activite == null) {
            throw new IllegalArgumentException("L'activité ne peut pas être null.");
        }

        if (!activite.estActif()) {
            throw new IllegalArgumentException("L'activité doit être active.");
        }

        this.nom = nom;
        this.id = id;
        this.activite = activite;
        this.activite.ajouterTache(this);
        this.periodesDeTravail = new ArrayList<PeriodeDeTravail>();

        assert invariant();
    }

    public boolean invariant() {
        return nom != null && !nom.isBlank() && id != null && !id.isBlank() && activite != null && activite.estActif();
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public ArrayList<PeriodeDeTravail> getPeriodesDeTravail() {
        return periodesDeTravail;
    }

    public Activite getActivite() {
        return activite;
    }

    @Override
    public void mettreALaCorbeille() {
        super.mettreALaCorbeille();
        for (PeriodeDeTravail periodeDeTravail : periodesDeTravail) {
            periodeDeTravail.mettreALaCorbeille();
        }
    }

    @Override
    public void restaurer() {
        super.restaurer();
        for (PeriodeDeTravail periodeDeTravail : periodesDeTravail) {
            periodeDeTravail.restaurer();
        }
    }

    @Override
    public String toString() {
        return "Tache [nom=" + nom + ", id=" + id + ", activite=" + activite + "]";
    }
}
