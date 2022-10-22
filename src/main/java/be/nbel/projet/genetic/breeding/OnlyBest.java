package be.nbel.projet.genetic.breeding;

import be.nbel.projet.genetic.IGeneticElement;

import java.util.*;

public class OnlyBest implements IBreeding {

    @Override
    public List<IGeneticElement> getParents(SortedSet<IGeneticElement> elements, int size, double randomParentRatio) {
        Random r = new Random();
        IGeneticElement[] randomElements = elements.toArray(new IGeneticElement[0]);
        Set<IGeneticElement> parents = new HashSet<>();
        Iterator<IGeneticElement> it = elements.iterator();
        while(it.hasNext() && parents.size() < size && elements.size()>0){ // croosOverRate correspond au pourcentage de nouveaux enfants.
            IGeneticElement el;
            if(r.nextDouble()*100 < randomParentRatio) el = randomElements[r.nextInt(elements.size())]; // Problème: le randomRatio n'est pas entièrement correct, car si il a pris par hasard un parent existant, il est simplement ignoré.
            else el = it.next();
            parents.add(el);
        }
        return new ArrayList<>(parents);
    }

}
