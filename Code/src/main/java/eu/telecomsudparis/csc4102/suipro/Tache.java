package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le concept de tâche. Une tâche est un élément jetable
 * référençant une activité et une collection de périodes de travail.
 */
public final class Tache extends ElementJetable implements ITache {
    /**
     * nom de la tâche.
     */
    private final String nom;

    /**
     * identifiant de la tâche.
     */
    private final String id;

    /**
     * périodes de travail de la tâche.
     */
    private ArrayList<IPeriodeDeTravail> periodesDeTravail;

    /**
     * activité de la tâche.
     */
    private IActivite activite;

    /**
     * @param nom
     * @param id
     * @param activite
     * @throws OperationImpossible
     */
    public Tache(final String nom, final String id, final IActivite activite) throws OperationImpossible {
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
        this.periodesDeTravail = new ArrayList<>();

        this.activite.ajouterTache(this);

        assert invariant();
    }

    /**
     * @return true si l'invariant est respecté, false sinon
     */
    public boolean invariant() {
        return nom != null && !nom.isBlank()
                && id != null && !id.isBlank()
                && activite != null
                && periodesDeTravail != null;
    }

    //#region Getters

    /**
     * @return le nom de la tâche
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return l'id de la tâche
     */
    public String getId() {
        return id;
    }

    /**
     * @return les périodes de travail de la tâche
     */
    public Collection<IPeriodeDeTravail> getPeriodesDeTravail() {
        return periodesDeTravail.stream().toList();
    }

    /**
     * @return l'activité de la tâche
     */
    public IActivite getActivite() {
        return activite;
    }

    //#endregion

    /**
     * @param periodeDeTravail
     * @throws OperationImpossible
     */
    public void ajouterPeriodeDeTravail(final IPeriodeDeTravail periodeDeTravail) throws OperationImpossible {
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
        if (!this.estActif()) {
            throw new OperationImpossible("La tâche doit être active.");
        }

        this.periodesDeTravail.add(periodeDeTravail);

        assert invariant();
    }

    //#region ElementJetable

    @Override
    protected void specificMettreALaCorbeille() {
        for (IPeriodeDeTravail periodeDeTravail : periodesDeTravail) {
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tache)) {
            return false;
        }
        Tache tache = (Tache) obj;
        return tache.id.equals(this.id);
    }
}
