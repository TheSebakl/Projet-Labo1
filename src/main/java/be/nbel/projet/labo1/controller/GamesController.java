package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.GravityRunnable;
import be.nbel.projet.labo1.model.Plateau;
import be.nbel.projet.labo1.model.PlateauFactory;

import java.util.ArrayList;

public class GamesController {
    private GravityRunnable gravityManager;
    private ArrayList<GameController> gameControllers;
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game_base;
    private final boolean isManuel;


    // MAN ?
    public GamesController(boolean manuel, int workerThreads, int width, int height, int characters){
        isManuel = manuel;
        setWidthAndHeight(width, height);
        if(isManuel) characters = 1;
        this.gameControllers = new ArrayList<>();
        generateCharacters(characters);
        this.gravityManager = new GravityRunnable(gameControllers, 1000);
    }

    public boolean isManuel() {
        return isManuel;
    }

    public void generateThreads(){

    }

    public void generateCharacters(int characters){
        if(characters == -1) characters = gameControllers.size();
        for(int i = 0; i < characters; i++){
            gameControllers.add(new GameController(game_base));
        }
    }


    public void setWidthAndHeight(int width, int height){
        this.factory = new PlateauFactory(width, height);
    }


}
