package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.controller.GameController;

public interface IGameRunnable {

    GameController getGame();

    void reload(Plateau game_base);
}
