package eu.telecomsudparis.csc4102.suipro;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public final class Corbeille {
    private static Corbeille instance;

    private LinkedHashSet<ElementJetable> elementsJetable;

    private Corbeille() {
        this.elementsJetable = new LinkedHashSet<>();
    }

    public static Corbeille getInstance() {
        if (instance == null) {
            instance = new Corbeille();
        }
        return instance;
    }

    public void ajouterALaCorbeille(final ElementJetable elementJetable) {
        this.elementsJetable.add(elementJetable);
    }

    public void supprimerDeLaCorbeille(final ElementJetable elementJetable) {
        this.elementsJetable.remove(elementJetable);
    }

    public ArrayList<Developpeur> getDeveloppeurs() {
        return this.elementsJetable.stream()
                .filter(elementJetable -> elementJetable instanceof Developpeur)
                .map(elementJetable -> (Developpeur) elementJetable)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<PeriodeDeTravail> getPeriodesDeTravail() {
        return this.elementsJetable.stream()
                .filter(elementJetable -> elementJetable instanceof PeriodeDeTravail)
                .map(elementJetable -> (PeriodeDeTravail) elementJetable)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Tache> getTaches() {
        return this.elementsJetable.stream()
                .filter(elementJetable -> elementJetable instanceof Tache)
                .map(elementJetable -> (Tache) elementJetable)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Activite> getActivites() {
        return this.elementsJetable.stream()
                .filter(elementJetable -> elementJetable instanceof Activite)
                .map(elementJetable -> (Activite) elementJetable)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
