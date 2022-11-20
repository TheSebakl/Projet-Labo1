package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.model.tiles.*;

import java.util.Random;

public class PlateauFactory {


    private Random r = new Random();
    private int width, height;
    private ITiles[][] plateau;

    public PlateauFactory(int width, int height){
        if(width < 0) width = r.nextInt(30)+10;
        if(height < 0) height = r.nextInt(10)+4;
        this.width = width;
        this.height = height;
    }

    public Plateau generate(){
        plateau = new ITiles[height][width];
        ITiles depart = new Depart();
        ITiles arrivee = new Arrivee();
        generateGround(10);
        if(!generatePlusBasPossible(0, depart)) System.err.println("TILE NON GENERE???");
        //generateObstacle(3);
        generatePlusBasPossible(width-1, arrivee);
        System.out.println("Plateau generated");
        return new Plateau(new ITiles[][][]{plateau, new ITiles[][]{new ITiles[]{depart, arrivee}}});
    }


    private boolean generatePlusBasPossible(int x, ITiles tile) {
        int size = getGroundPosition(x);
        int y = plateau.length-1-size;
        if(size == 0 || !canPlace(x, y)) return false;
        plateau[y][x] = tile;
        tile.getCoord().X = x;
        tile.getCoord().Y = y;
        return true;
    }

    private boolean canPlace(int x, int y){
        if(x < 0 || y<0 || x >= plateau[0].length || y>=plateau.length) return false;
        return plateau[y][x] == null;
    }

    private void generateObstacle(int nbrObstacle){
        int i = 0;
        int nbr = 0;
        do{
            int index = r.nextInt(plateau[0].length-3)+2;
            int size = getGroundPosition(index);
            int siz = getGroundPosition(index-1);
            if(size<=siz){
                int y = plateau.length-1-siz;
                if(canPlace(index-1, y)) {
                    ITiles elemPrec = plateau[y][index-1];
                    if (generatePlusBasPossible(index, new Obstacle())) nbr++;
                } // TODO Laisser une place en haut
            }
            i++;
        }while(nbr<nbrObstacle && i<50);
    }

    private int getGroundPosition(int x){
        int size = -1;
        boolean isGround = true;
        do {
            size++;
            int y = plateau.length-1-size;
            isGround = plateau[y][x] != null && plateau[y][x] instanceof Ground;
        }while(isGround && size < plateau.length-1);

        return size;
    }

    private void generateGround(double holeRatio){
        int score = 1+r.nextInt(plateau.length-3);
        for(int j = 0; j < score; j++){
            plateau[plateau.length-1-j][0] = new Ground(0, plateau.length-1-j);
        }
        for(int i = 1; i < plateau[0].length-1; i++){
            if(score<=0) score = 1;
            else {
                double newRandom = r.nextDouble();
                if (newRandom < 0.05) score-=2;
                else if (newRandom < 0.2) score--;
                else if (newRandom > 0.8) score++;
                if(score>=plateau.length-1) score--;
            }
            for(int j = 0; j < score; j++){
                plateau[plateau.length-1-j][i] = new Ground(i, plateau.length-1-j);
            }
        }
        plateau[plateau.length-1][plateau[0].length-1] = new Ground(plateau[0].length-1, plateau.length-1);
    }

}
