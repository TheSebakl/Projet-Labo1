package be.nbel.projet.labo1.controller;

import be.nbel.projet.genetic.GeneticAlgorithm;
import be.nbel.projet.genetic.IGeneticElement;
import be.nbel.projet.genetic.examples.game.GeneticAlgorithmGame;
import be.nbel.projet.genetic.examples.game.GeneticAlgorithmRunnable;
import be.nbel.projet.genetic.examples.game.Parcours;
import be.nbel.projet.labo1.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

public class GamesController {
    private ArrayList<IGameRunnable> threads = new ArrayList<>();
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game_base;
    private final boolean isManuel;
    private int indexActive = 0;
    private GeneticAlgorithm geneticAlgorithm;
    private ScoreRunner bestThread;

    // MAN ?
    public GamesController(int workerThreads, int width, int height, int characters, double crossOver, double mutatioNFactor, int delay){
        isManuel = characters<=1;
        setWidthAndHeight(width, height);
        game_base = factory.generate();
        if(isManuel) characters = 1;
        generateThreads(workerThreads, crossOver, mutatioNFactor, characters, delay);
        generateActions();
    }

    private void generateActions(){
        if(!isManuel()){
            Parcours parcours = new Parcours();
            System.out.println(parcours);
        }
    }

    public boolean isManuel() {
        return isManuel;
    }

    public void generateThreads(int Threads, double crossOverRate, double mutationFactor, int characters, int delay){
        this.threads = new ArrayList<>();
        if(isManuel()) {
            GravityRunnable gravityManager = new GravityRunnable(new GameController(game_base), delay);
            this.threads.add(gravityManager);
            new Thread(gravityManager).start();
        }else{

            if(Threads == -1) Threads = threads.size();
            LinkedList<ScoreRunner> scoreRunners = new LinkedList<>();
            for(int i = 0; i < Threads; i++){
                GameController gc = new GameController(game_base);
                ScoreRunner scoreRunner = new ScoreRunner(gc, game_base, delay);

                threads.add(scoreRunner);
                scoreRunners.add(scoreRunner);
                new Thread(scoreRunner).start();
            }
            this.geneticAlgorithm = new GeneticAlgorithmGame(scoreRunners,crossOverRate, characters, mutationFactor );
            new Thread(new GeneticAlgorithmRunnable(geneticAlgorithm)).start();

            GameController gc = new GameController(game_base);
            bestThread = new ScoreRunner(gc, game_base, delay*5, true);
            new Thread(bestThread).start();

        }
    }


    public void setWidthAndHeight(int width, int height){
        this.factory = new PlateauFactory(width, height);
    }



    public GameController getActiveGame() {
        if(indexActive == -1) return bestThread.getGame();
        return threads.get(indexActive).getGame();
    }

    public String getPath(){
        if(isManuel) return "";Parcours p;
        if(indexActive>=0)p = ((ScoreRunner)threads.get(indexActive)).getParcours();
        else p = bestThread.getParcours();
        if(p == null) return  "";
        return p.toString();
    }

    public void reload() {
        if(isManuel()) getActiveGame().reload(game_base);
        else{

            for(int i = 0; i < threads.size(); i++){
                threads.get(i).reload(game_base);
            }
            bestThread.reload(game_base);
            geneticAlgorithm.forceFinish();
        }
        //else{
        //    generateCharacters(-1);
        //}

        // for(int i = 0; i < threads.size(); i++){
        //     gameControllers.get(i).reload(game_base);
        // }
    }

    public void renew() {
        game_base = factory.generate();
        reload();
    }


    public int getTotalGames() {
        return threads.size();
    }

    public int getActiveGameIndex() {
        return indexActive;
    }

    public double getLastScore() {
        return getActiveGame().getScore();
    }

    public double getBestScore() {
        if(geneticAlgorithm != null){
            if(geneticAlgorithm.getBestElement() != null) return geneticAlgorithm.getBestElement().getScore();
        }
        return 0;
    }

    public double getLastBestScore(){
        if(isManuel()) return getActiveGame().getScore();
        if(geneticAlgorithm.getBestLastElement() != null)
            return geneticAlgorithm.getBestLastElement().getScore();
        return 0;
    }

    public void showBest() {
        if(geneticAlgorithm.getBestElement() != null) {
            indexActive = -1;
            bestThread.addParcours((Parcours) geneticAlgorithm.getBestElement());
        }
    }
    public void changeIndex(int sens){
        int newIndex = indexActive + sens;
        if(newIndex<0 || newIndex>=threads.size()) return;
        indexActive = newIndex;
    }
}
