package be.nbel.projet.genetic.breeding;

import be.nbel.projet.genetic.IGeneticElement;

import java.util.List;
import java.util.SortedSet;

public interface IBreeding {

        List<IGeneticElement> getParents(SortedSet<IGeneticElement> elements, int size, double randomParentRatio);




}
