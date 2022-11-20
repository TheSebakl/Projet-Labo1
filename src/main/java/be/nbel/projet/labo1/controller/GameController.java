package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.*;
import be.nbel.projet.labo1.model.tiles.ITiles;
import be.nbel.projet.labo1.vue.GameView;

public class GameController {

    private Plateau game;
    private GameCharacter character;
    private boolean tickToPlay = true;
    private Movements lastMovement = null;

    protected GameController(Plateau game_base){
        reload(game_base);
    }

    public void reload(Plateau game_base){
        this.game = game_base.copy();
        this.character = new GameCharacter(new Coordonnees(game.getDepart().getCoord()));
    }


    public ITiles[][] getTiles(){
        return game.getTiles();
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
            // System.out.println("GRAVITY : isInAir? " + character.isInAir() + " - X? " + lastMovement.X);
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

    public GameCharacter getMainCharacter() {
        return character;
    }

    public synchronized double getScore() {
        // if (!isGameFinished()) return Double.NaN;
        Coordonnees arrivee = this.game.getArrivee().getCoord();
        double score = Math.pow(arrivee.X - character.getCoordonnees().X,2)+Math.pow(arrivee.Y - character.getCoordonnees().Y,2);
        //if(isDead()) score += 100;
        return score;

    }

}
