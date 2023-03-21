package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Tache extends ElementJetable {
    private final String nom;
    private final String id;

    private ArrayList<PeriodeDeTravail> periodesDeTravail;
    private Activite activite;

    public Tache(final String nom, final String id, final Activite activite) throws OperationImpossible {
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
        if (activite.getTache(id) != null) {
            throw new IllegalArgumentException("L'id de la tâche doit être unique.");
        }

        this.nom = nom;
        this.id = id;
        this.activite = activite;
        this.periodesDeTravail = new ArrayList<PeriodeDeTravail>();

        this.activite.ajouterTache(this);

        assert invariant();
    }

    public boolean invariant() {
        return nom != null && !nom.isBlank()
                && id != null && !id.isBlank()
                && activite != null
                && periodesDeTravail != null;
    }

    //#region Getters

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

    //#endregion

    public void ajouterPeriodeDeTravail(final PeriodeDeTravail periodeDeTravail) throws OperationImpossible {
        if (periodeDeTravail == null) {
            throw new IllegalArgumentException("La période de travail ne peut pas être null.");
        }
        if (periodesDeTravail.contains(periodeDeTravail)) {
            throw new OperationImpossible("La période de travail est déjà associée à cette tâche.");
        }
        if (!periodeDeTravail.estActif()) {
            throw new OperationImpossible("La période de travail doit être active.");
        }
        if (periodeDeTravail.getTache() != this) {
            throw new OperationImpossible("La période de travail doit être associée à cette tâche.");
        }

        this.periodesDeTravail.add(periodeDeTravail);

        assert invariant();
    }

    //#region ElementJetable

    @Override
    public void mettreALaCorbeille() {
        super.mettreALaCorbeille();
        for (PeriodeDeTravail periodeDeTravail : periodesDeTravail) {
            periodeDeTravail.mettreALaCorbeille();
        }
    }

    //#endregion

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tache [nom=" + nom + ", id=" + id + ", actif=" + estActif() + "]");
        builder.append("\n\t↳ ");
        builder.append(activite.toString());
        return builder.toString();
    }
}