package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.model.tiles.Ground;
import be.nbel.projet.labo1.model.tiles.ITiles;
import be.nbel.projet.labo1.model.tiles.Obstacle;

public class Character {

    private Coordonnees coordonnees;
    private boolean isFinished = false;
    private boolean isDead = false;
    public Character(Coordonnees coordonnees){
        this.coordonnees = coordonnees;
    }

    public void move(Plateau plateau, Movements movement){
        if(!isFinished) movement(plateau, movement, false);
        //TODO UPDATE FRAME (Observer)
        if(!isFinished) gravity(plateau);
        // TODO vérifier si trou
        // TODO vérifier si Arrivée

    }

    private void movement(Plateau plateau, Movements movements, boolean killEnemies){
        int newX = coordonnees.X + movements.X;
        int newY = coordonnees.Y + movements.Y;
        ITiles objective = plateau.getTile(newX, newY);
        if(objective == null){
            isFinished = true;
            isDead = true;
            return;
        }
        else if(objective instanceof Ground) return;
        else if(objective instanceof Obstacle)
            if(killEnemies){
                plateau.removeEnemy(newX, newY);
            }else {
                isFinished = true;
                isDead = true;
            }

    }

    private void gravity(Plateau plateau){
        movement(plateau, Movements.BOTTOM, true);
    }


    public boolean isDead() {
        return isDead;
    }
}
