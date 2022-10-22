package be.nbel.projet;

import be.nbel.projet.genetic.GeneticAlgorithm;
import be.nbel.projet.genetic.IGeneticElement;
import be.nbel.projet.genetic.examples.cat.Cat;

import java.util.SortedSet;

public class MainGenetic {


    public static void main(String[] args) {
        main1();
    }

    public static void main1(){
        GeneticAlgorithm geneticAlgorithm = ((new GeneticAlgorithm.Builder())).build(Cat.class);
        System.out.println("Score 1er : " + ((IGeneticElement)((SortedSet)geneticAlgorithm.getElements()).first()).getScore());
        int i = 0;
        while(i != -1){
            i = geneticAlgorithm.nextIteration();
            if(i%10==0) System.out.println("Score it("+i+") : " + ((IGeneticElement)((SortedSet)geneticAlgorithm.getElements()).first()).getScore());
        }

        System.out.println("It ("+geneticAlgorithm.getIteration()+"): " + (((SortedSet)geneticAlgorithm.getElements()).first()).toString());

    }
}
