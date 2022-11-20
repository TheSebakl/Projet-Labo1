package be.nbel.projet.labo1.model;

import be.nbel.projet.genetic.GeneticAlgorithm;
import be.nbel.projet.genetic.examples.game.Parcours;
import be.nbel.projet.labo1.controller.GameController;
import org.lwjgl.Sys;

import java.util.LinkedList;

public class ScoreRunner implements IGameRunnable, Runnable {
    private GameController gameController;
    // private MovementMapper movementMapper = new MovementMapper().setMode(MovementMapper.MODE_NUMBER);
    private LinkedList<Parcours> parcours = new LinkedList<>();
    private Plateau game_base;
    private int delay;
    private int nbrAction = 0;

    public ScoreRunner(GameController gc, Plateau game_base, int delay){
        this.gameController = gc;
        this.delay = delay;
        this.game_base = game_base;
    }

    public void addParcours(Parcours parcours){
        this.parcours.addLast(parcours);
    }

    public Parcours getParcours(){ if(parcours.size()==0) return null; return parcours.getFirst(); }

    @Override
    public void run() {
        while(true){
            if(parcours.size()>0) {
                if (gameController.isTickToPlay()) {
                    Movements m = parcours.getFirst().next();
                    if (m == null) finishGame();
                    else {
                        gameController.moveCharacter(m);
                        if (gameController.isGameFinished()) finishGame();
                    }
                } else {
                    gameController.applyGravity();
                    if (gameController.isGameFinished()) finishGame();
                }
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void finishGame(){
        double score = gameController.getScore();
        getParcours().setScore(score);
        gameController.reload(game_base);
        parcours.removeFirst();
        nbrAction++;
    }

    @Override
    public GameController getGame() {
        return gameController;
    }
}
