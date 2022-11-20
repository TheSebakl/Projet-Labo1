package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.controller.GameController;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GravityRunnable implements IGameRunnable, Runnable {
    private final GameController gc;
    private final int period;
    public GravityRunnable(GameController gameControllers, int period){
        this.gc = gameControllers;
        this.period = period;
    }

    @Override
    public void run() {
        while(true) {
            gc.applyGravity();
            try {
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public GameController getGame() {
        return gc;
    }

    @Override
    public void reload(Plateau game_base) {
        gc.reload(game_base);
    }
}
