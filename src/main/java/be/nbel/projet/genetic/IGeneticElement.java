package be.nbel.projet.genetic;

public interface IGeneticElement extends Comparable<IGeneticElement> {

    double getScore();

    IGeneticElement crossOver(IGeneticElement otherParent);

    boolean mutate(double mutationFactor);


}
