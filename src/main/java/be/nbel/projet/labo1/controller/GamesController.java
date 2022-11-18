package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.GameCharacter;
import be.nbel.projet.labo1.model.GravityRunnable;
import be.nbel.projet.labo1.model.Plateau;
import be.nbel.projet.labo1.model.PlateauFactory;
import be.nbel.projet.labo1.model.tiles.ITiles;

import java.util.ArrayList;

public class GamesController {
    private GravityRunnable gravityManager;
    private ArrayList<GameController> gameControllers;
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game_base;
    private final boolean isManuel;
    private int indexActive = 0;


    // MAN ?
    public GamesController(boolean manuel, int workerThreads, int width, int height, int characters){
        isManuel = manuel;
        setWidthAndHeight(width, height);
        game_base = factory.generate();
        if(isManuel) characters = 1;
        this.gameControllers = new ArrayList<>();
        generateCharacters(characters);
        this.gravityManager = new GravityRunnable(gameControllers, 1000);
        generateThreads();
    }

    public boolean isManuel() {
        return isManuel;
    }

    public void generateThreads(){
        new Thread(gravityManager).start();
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



    public GameController getActiveGame() {
        return gameControllers.get(indexActive);
    }

    public void reload() {
        //if(isManuel()) getActiveGame().reload(game_base);
        //else{
        //    generateCharacters(-1);
        //}
        for(int i = 0; i < gameControllers.size(); i++){
            gameControllers.get(i).reload(game_base);
        }
    }

    public void renew() {
        game_base = factory.generate();
        reload();
    }


}
