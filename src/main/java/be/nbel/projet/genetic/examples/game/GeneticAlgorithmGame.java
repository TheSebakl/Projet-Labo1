package be.nbel.projet.genetic.examples.game;

import be.nbel.projet.genetic.GeneticAlgorithm;
import be.nbel.projet.genetic.IGeneticElement;
import be.nbel.projet.genetic.Scoring;
import be.nbel.projet.genetic.breeding.BreedingParentsType;
import be.nbel.projet.labo1.model.GravityRunnable;
import be.nbel.projet.labo1.model.IGameRunnable;
import be.nbel.projet.labo1.model.ScoreRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Set;

public class GeneticAlgorithmGame extends GeneticAlgorithm {
    private LinkedList<ScoreRunner> scoreRunnables;
    public GeneticAlgorithmGame(LinkedList<ScoreRunner> scoreRunners, double crossOverRate, int characters, double mutationFactor){
        super(Parcours.class);
        this.scoreRunnables = scoreRunners;
        super.maxNbrIteration = 500;
        super.crossOverRate = crossOverRate;
        super.maxPopulation = characters;
        super.objectiveScore = 10;
        super.mutationFactor = mutationFactor;
        super.scoringObjective = Scoring.MINIMUM;
        try {
            super.breedingParents = BreedingParentsType.ONLY_BEST.getClasse().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        super.generatePopulation(true);
    }

    @Override
    protected void afterGeneratePopulation(){
        Set<IGeneticElement> elements = super.getElements();
        int i = 0;
        for(IGeneticElement element : elements){
            scoreRunnables.get(i%scoreRunnables.size()).addParcours((Parcours) element);
            i++;
        }
    }


}
