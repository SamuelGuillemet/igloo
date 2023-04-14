package eu.telecomsudparis.csc4102.suipro;

import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe représente un label.
 */
public class Label implements ILabel {
    /**
     * L'identifiant du label.
     */
    private final String labelId;

    /**
     * Le nom du label.
     */
    private String labelName;

    /**
     * @param labelId
     * @param labelName
     * @throws OperationImpossible
     */
    public Label(final String labelId, final String labelName) throws OperationImpossible {
        if (labelId == null || labelId.isBlank()) {
            throw new OperationImpossible("L'identifiant du label ne peut pas être null ou vide.");
        }
        if (labelName == null || labelName.isBlank()) {
            throw new OperationImpossible("Le nom du label ne peut pas être null ou vide.");
        }
        this.labelId = labelId;
        this.labelName = labelName;

        assert invariant();
    }

    /**
     * @return true si l'invariant de classe est respecté, false sinon.
     */
    public boolean invariant() {
        return labelId != null && !labelId.isBlank()
                && labelName != null && !labelName.isBlank();
    }

    @Override
    public String getId() {
        return labelId;
    }

    /**
     * @return name
     */
    public String getNom() {
        return labelName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Label)) {
            return false;
        }

        final Label label = (Label) obj;
        return labelId.equals(label.labelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId);
    }

    @Override
    public String toString() {
        return "Label [labelId=" + labelId + ", labelName=" + labelName + "]";
    }
}
