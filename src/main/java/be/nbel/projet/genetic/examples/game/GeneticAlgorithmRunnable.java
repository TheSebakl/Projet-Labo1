package be.nbel.projet.genetic.examples.game;

import be.nbel.projet.genetic.GeneticAlgorithm;
import be.nbel.projet.genetic.IGeneticElement;
import org.lwjgl.Sys;

import java.util.SortedSet;

public class GeneticAlgorithmRunnable implements Runnable {
    private GeneticAlgorithm geneticAlgorithm;

    public GeneticAlgorithmRunnable(GeneticAlgorithm algorithm){
        this.geneticAlgorithm = algorithm;
    }
    @Override
    public void run() {
        int i = 0;
        while(i != -1){
            i = geneticAlgorithm.nextIteration();
            // if(i%10==0)
                System.out.println("Score it("+i+") : " + ((IGeneticElement)((SortedSet)geneticAlgorithm.getElements()).first()).getScore());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("It ("+geneticAlgorithm.getIteration()+"): " + (((SortedSet)geneticAlgorithm.getElements()).first()).toString());
    }
}
