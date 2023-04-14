package eu.telecomsudparis.csc4102.suipro;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
     * liste de labels de la tâche.
     */
    private ArrayList<Label> labels;

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
            throw new OperationImpossible("Le nom ne peut pas être null ou vide.");
        }
        if (id == null || id.isBlank()) {
            throw new OperationImpossible("L'id ne peut pas être null ou vide.");
        }
        if (activite == null) {
            throw new OperationImpossible("L'activité ne peut pas être null.");
        }
        if (!activite.estEnFonctionnement()) {
            throw new OperationImpossible("L'activité doit être active.");
        }
        if (activite.getTache(id) != null) {
            throw new OperationImpossible("L'id de la tâche doit être unique.");
        }

        this.nom = nom;
        this.id = id;
        this.activite = activite;
        this.periodesDeTravail = new ArrayList<>();
        this.labels = new ArrayList<>();

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
                && periodesDeTravail != null
                && labels != null;
    }

    /**
     * Renvoie le temps de travail de la tâche.
     * 
     * @return le temps de travail de la tâche
     */
    public double calculerTempsDeTravail() {
        return periodesDeTravail.stream().mapToDouble(IPeriodeDeTravail::calculerTempsDeTravail).sum();
    }

    /**
     * Ajoute un label à la tâche.
     * 
     * @param label
     * @throws OperationImpossible
     */
    public void ajouterLabel(final Label label) throws OperationImpossible {
        if (label == null) {
            throw new OperationImpossible("Le label ne peut pas être null.");
        }
        if (labels.contains(label)) {
            throw new OperationImpossible("Le label est déjà associé à cette tâche.");
        }
        if (!this.estEnFonctionnement()) {
            throw new OperationImpossible("La tâche doit être active.");
        }

        this.labels.add(label);

        assert invariant();
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

    /**
     * @return les labels de la tâche
     */
    public List<Label> getLabels() {
        return labels.stream().toList();
    }

    //#endregion

    /**
     * @param periodeDeTravail
     * @throws OperationImpossible
     */
    public void ajouterPeriodeDeTravail(final IPeriodeDeTravail periodeDeTravail) throws OperationImpossible {
        if (periodeDeTravail == null) {
            throw new OperationImpossible("La période de travail ne peut pas être null.");
        }
        if (periodesDeTravail.contains(periodeDeTravail)) {
            throw new OperationImpossible("La période de travail est déjà associée à cette tâche.");
        }
        if (!periodeDeTravail.estEnFonctionnement()) {
            throw new OperationImpossible("La période de travail doit être active.");
        }
        if (periodeDeTravail.getTache() != this) {
            throw new OperationImpossible("La période de travail doit être associée à cette tâche.");
        }
        if (!this.estEnFonctionnement()) {
            throw new OperationImpossible("La tâche doit être active.");
        }

        this.periodesDeTravail.add(periodeDeTravail);

        assert invariant();
    }

    //#region ElementJetable

    @Override
    protected void specificMettreALaCorbeille(final ICorbeille corbeille) throws OperationImpossible {
        for (IPeriodeDeTravail periodeDeTravail : periodesDeTravail) {
            periodeDeTravail.mettreALaCorbeille(corbeille);
        }
    }

    @Override
    protected void specificRestaurer(final ICorbeille corbeille) throws OperationImpossible {
        if (!activite.estEnFonctionnement()) {
            throw new IllegalStateException("l'activité de la tâche doit être en fonctionnement");
        }
        for (IPeriodeDeTravail periodeDeTravail : periodesDeTravail) {
            periodeDeTravail.restaurer(corbeille);
        }
    }

    //#endregion

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tache [nom=" + nom + ", id=" + id + ", enFonctionnement=" + estEnFonctionnement() + "]");
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

    /**
    * This method is called when an event is fire by the Corbeille when a PeriodeDeTravail is removed from it.
    * 
    * @param evt {@code source} must be a {@link Corbeille} and {@code propertyName} must be {@link PeriodeDeTravail} string.
    */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getSource().getClass() != Corbeille.class) {
            return;
        }
        if (!evt.getPropertyName().equals(PeriodeDeTravail.class.getSimpleName())) {
            return;
        }
        if (evt.getNewValue() != null) {
            return;
        }
        periodesDeTravail.remove(evt.getOldValue());
    }
}
