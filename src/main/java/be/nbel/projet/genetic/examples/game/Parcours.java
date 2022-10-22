package be.nbel.projet.genetic.examples.game;

import be.nbel.projet.genetic.IGeneticElement;

import java.util.LinkedList;

public class Parcours implements IGeneticElement {

    LinkedList<IAction> actions = new LinkedList<>();


    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public IGeneticElement crossOver(IGeneticElement otherParent) {
        return null;
    }

    @Override
    public boolean mutate(double mutationFactor) {
        return false;
    }

    @Override
    public int compareTo(IGeneticElement o) {
        return 0;
    }
}
