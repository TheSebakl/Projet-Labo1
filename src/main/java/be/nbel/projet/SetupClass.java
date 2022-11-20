package be.nbel.projet;

import be.nbel.projet.labo1.controller.GameController;
import be.nbel.projet.labo1.controller.GamesController;
import be.nbel.projet.labo1.vue.GameView;
import org.lwjgl.Sys;
import org.newdawn.slick.*;

import java.io.File;

import static be.nbel.projet.labo1.vue.GameView.SCREEN_WIDTH;
import static be.nbel.projet.labo1.vue.GameView.SCREEN_HEIGHT;

public class SetupClass {



    public static void main(String[] args) throws SlickException {
        GamesController gc  = setupController(args);
        launchGame(gc);
    }

    private static GamesController setupController(String[] args){

        int nbrThread = 1, chars = 1, evaluationTicks = 20;
        int w = -1, h = -1;
        double crossOver = 0.2, mutation = 0.01;
        if(args.length > 0){
            try {
                nbrThread = Integer.parseInt(args[0]);
                w = Integer.parseInt(args[1]);
                h = Integer.parseInt(args[2]);
                chars = Integer.parseInt(args[3]);
                crossOver= Double.parseDouble(args[4]);
                mutation= Double.parseDouble(args[5]);
                evaluationTicks = Integer.parseInt(args[6]);

            }catch(Exception e){
                System.err.println("Impossible de convertir la taille en Integer");
            }
        }
        return new GamesController( nbrThread, w, h, chars, crossOver, mutation, evaluationTicks);
    }

    private static void launchGame(GamesController gc) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());

        AppGameContainer app = new AppGameContainer(new GameView("Projet - Labo 1", gc));
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }




}
