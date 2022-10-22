package be.nbel.projet.genetic;

import be.nbel.projet.genetic.breeding.BreedingParentsType;
import be.nbel.projet.genetic.breeding.IBreeding;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GeneticAlgorithm {

    private SortedSet<IGeneticElement> elements = new TreeSet<>();
    private List<IGeneticElement> parents = new ArrayList<>();

    private int maxPopulation;
    private double crossOverRate; // rate des "nouveaux enfants" --> 100-crossOverRate donne le pourcentage des parents présent dans la génération enfant.
    private double mutationFactor;
    private int numberOfCutPoints; // ??? TODO
    private double keepRandomParentsRatio;
    private IBreeding breedingParents;
    private int maxNbrIteration;
    private double objectiveScore;
    private Scoring scoringObjective;

    private int nbrIteration = 0;

    // Population Size
    // Crossover rate
    // Mutation rate
    // Number of cut points
    // Which parents to keep? (fitness vs randomlu)
    // Which parents to choose for breeding?
    // Who is mutated? How?
    // Speciation ?


    private Class<? extends IGeneticElement> iGeneticElementClass;
    private Random r = new Random();

    private GeneticAlgorithm(Class<? extends IGeneticElement> iGeneticElementClass){
        this.iGeneticElementClass = iGeneticElementClass;
    }

    public int nextIteration(){
        if(this.scoringObjective.hasAttainedScore(elements.first().getScore(), objectiveScore) || nbrIteration>=maxNbrIteration) return -1;
        keepGoodParents();
        elements.clear();
        generatePopulation(false);
        return ++nbrIteration;

    }

    private void keepGoodParents(){
        double childRate = (100-crossOverRate);
        int nbrParentAGarder = (int) (maxPopulation * (childRate/100));
        parents = breedingParents.getParents(elements, nbrParentAGarder, keepRandomParentsRatio);
    }

    public Set<IGeneticElement> getElements(){
        return elements;
    }

    private boolean generateChromosome(boolean init) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if(elements.size()<maxPopulation) {
            if (init) {
                IGeneticElement child = iGeneticElementClass.getDeclaredConstructor().newInstance();
                elements.add(child);
            } else {
                if (parents.size() < 2) throw new IllegalArgumentException("Il n'y a pas assez de parents");
                IGeneticElement[] par = getBreedingParents();
                IGeneticElement enfant = par[0].crossOver(par[1]);
                enfant.mutate(mutationFactor);
                elements.add(enfant);
            }
            return true;
        }
        return false;
    }

    private IGeneticElement[] getBreedingParents(){
        IGeneticElement[] par = new IGeneticElement[2];
        int indexParent1 = r.nextInt(parents.size());
        int indexParent2;
        do {
            indexParent2 = r.nextInt(parents.size());
        }while(indexParent2!=indexParent1);

        par[0] = parents.get(indexParent1);
        par[1] = parents.get(indexParent2);
        return par;
    }

    private void generatePopulation(boolean init){
            try {
                while(generateChromosome(init));
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

    }

    public int getIteration() {
        return nbrIteration;
    }

    public static class Builder{

        private int maxNbrIteration = 10000;
        private double objectiveScore = 95;
        private Scoring scoringObjective = Scoring.MAXIMUM;
        private double crossOverRate = 70;

        private int maxPopulation = 25; // Taille Population
        private double mutationFactor = 5; // Facteur de Mutation
        private int numberOfCutPoints = 1; // ???
        private double keepRandomParentsRatio = 5;
        private BreedingParentsType breedingParentsType = BreedingParentsType.ONLY_BEST;

        Builder setMaxPopulation(int maxPopulation){
            this.maxPopulation = maxPopulation;
            return this;
        }

        Builder setMaxNbrIteration(int nbrIteration){
            this.maxNbrIteration = nbrIteration;
            return this;
        }

        Builder setCrossOverRate(int crossOverRate){
            this.crossOverRate = crossOverRate;
            return this;
        }

        Builder setObjectiveScore(double objectiveScore){
            this.objectiveScore = objectiveScore;
            return this;
        }

        Builder setMutationFactor(double mutationFactor){
            this.mutationFactor = mutationFactor;
            return this;
        }

        Builder setNumberOfCutPoints(int numberOfCutPoints){
            this.numberOfCutPoints = numberOfCutPoints;
            return this;
        }

        Builder setBreedingParentType(BreedingParentsType breedingParentType){
            this.breedingParentsType = breedingParentType;
            return this;
        }

        public Builder setScoringObjective(Scoring scoringObjective) {
            this.scoringObjective = scoringObjective;
            return this;
        }

        public GeneticAlgorithm build(Class<? extends IGeneticElement> geneticClass){
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(geneticClass);
            geneticAlgorithm.maxNbrIteration = this.maxNbrIteration;
            geneticAlgorithm.crossOverRate = this.crossOverRate;
            geneticAlgorithm.maxPopulation = this.maxPopulation;
            geneticAlgorithm.objectiveScore = this.objectiveScore;
            geneticAlgorithm.mutationFactor = this.mutationFactor;
            geneticAlgorithm.numberOfCutPoints = this.numberOfCutPoints;
            geneticAlgorithm.keepRandomParentsRatio = this.keepRandomParentsRatio;
            geneticAlgorithm.scoringObjective = this.scoringObjective;
            try {
                geneticAlgorithm.breedingParents = this.breedingParentsType.getClasse().getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            geneticAlgorithm.generatePopulation(true);
            return geneticAlgorithm;
        }


    }


}
