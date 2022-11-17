package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.*;
import be.nbel.projet.labo1.model.tiles.ITiles;
import be.nbel.projet.labo1.vue.GameView;

public class GameController {
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game;
    private Plateau game_base;
    private GameCharacter character;
    private boolean tickToPlay = true;
    private Movements lastMovement = null;
    boolean modeAutomatique = false;
    private double score; // Objectif, avoir le score le plus bas possible. Si mort, devient MAX - Score (plus il a été loin, plus il est avantagé).

    protected GameController(Plateau game_base){
        this.game_base = game_base;
        modeAutomatique = true;
        reload();
    }

    public  GameController(){
        renew();
        new Thread(new GravityRunnable(this, 1000)).start();
    }

    public void renew(){

        this.game_base = factory.generate();
        reload();
    }

    public void reload(){
        this.game = game_base.copy();
        this.character = new GameCharacter(new Coordonnees(game.getDepart().getCoord()));
    }

    public void setWidthAndHeight(int width, int height){
        this.factory = new PlateauFactory(width, height);
    }

    public ITiles[][] getTiles(){
        return game.getTiles();
    }

    public Coordonnees getMainCharacterCoordonnees(){
        return character.getCoordonnees();
    }

    public void moveCharacter(Movements movement){
        if(canPlay()) {
            character.move(game, movement);
            this.lastMovement = movement;
            tickToPlay = false;
        }
    }

    public void applyGravity() {
        if(!isGameFinished() && !isTickToPlay()){
            System.out.println("GRAVITY : isInAir? " + character.isInAir() + " - X? " + lastMovement.X);
            if(character.isInAir()) {
                if (lastMovement.X == 1 && !character.move(this.game, Movements.RIGHT_BOTTOM))
                    character.move(this.game, Movements.BOTTOM);
                else if (lastMovement.X == -1 && !character.move(this.game, Movements.LEFT_BOTTOM))
                    character.move(this.game, Movements.BOTTOM);
                else if (lastMovement.X == 0)
                    character.move(this.game, Movements.BOTTOM);
            }
            tickToPlay = true;
        }
    }

    public boolean isGameFinished() {
        return character.isDead() || character.hasFinished();
    }

    public boolean isDead() {
        return character.isDead();
    }

    public boolean isTickToPlay(){
        return tickToPlay;
    }

    public boolean canPlay(){
        return isTickToPlay() && !isGameFinished();
    }
}
