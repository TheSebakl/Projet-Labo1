package be.nbel.projet.genetic.examples.cat;

import be.nbel.projet.genetic.IGeneticElement;

import java.awt.*;
import java.util.Random;


public class Cat implements IGeneticElement {


    private static Color CIBLE_COLOR = new Color(255, 158, 46);
    private static boolean CIBLE_ISLONGHAIR = true;

    // Objective: Have color Blue;
    // Objective: Have type of Hair.
    private CatColors color;
    private Color eyeColor;
    private boolean isLonghair = false;

    private int lastCalculatedScore = 0;

    public Cat(){
        Random r = new Random();
        // Color
        color = CatColors.values()[r.nextInt(CatColors.values().length)];
        // Eyes
        int red = r.nextInt(255);
        int green = r.nextInt(255);
        int blue = r.nextInt(255);
        eyeColor = new Color(red, green, blue);
        // isLongHair
        if(r.nextBoolean()) isLonghair = true;
    }

    private Cat(CatColors color, Color eyeColor, boolean isLonghair){
        this.color = color;
        this.eyeColor = eyeColor;
        this.isLonghair = isLonghair;
    }

    public boolean mutate(double mutateFactor){
        Random r = new Random();
        if(r.nextInt(100) <= mutateFactor){
            int index = r.nextInt(5);
            switch (index){
                case 0:
                    this.color = CatColors.values()[r.nextInt(CatColors.values().length)];
                    break;
                case 1:
                case 2:
                case 3:
                    int[] rgb = new int[] {eyeColor.getRed(), eyeColor.getGreen(), eyeColor.getBlue()};
                    rgb[index-1] = r.nextInt(255);
                    this.eyeColor = new Color(rgb[0], rgb[1], rgb[2]);
                    break;
                case 4:
                    isLonghair = !isLonghair;
                    break;
            }
            return true;
        }
        return false;
    }

    public Cat crossOver(IGeneticElement otherParent){
        Random r = new Random();
        double scoreRatio = getScore()+otherParent.getScore()/2;
        CatColors color = this.color;
        Color eyeColor = this.eyeColor;
        boolean isLonghair = this.isLonghair;
        if(otherParent instanceof Cat) {
            if (r.nextInt(100) > scoreRatio) color = ((Cat) otherParent).color;
            if (r.nextInt(100) > scoreRatio) eyeColor = ((Cat) otherParent).eyeColor;
            if (r.nextInt(100) > scoreRatio) isLonghair = ((Cat) otherParent).isLonghair;
        }
        return new Cat(color, eyeColor, isLonghair);
    }


    public double getScore(){
        int score = 0;
        int maxScore = 255*5;

        score+=color.getScore();
        score+=255-Math.abs(eyeColor.getRed()-CIBLE_COLOR.getRed());
        score+=255-Math.abs(eyeColor.getBlue()-CIBLE_COLOR.getBlue());
        score+=255-Math.abs(eyeColor.getGreen()-CIBLE_COLOR.getGreen());
        score+=255*(CIBLE_ISLONGHAIR==isLonghair?1:0);
        lastCalculatedScore =  (int)((double)score/maxScore*100);
        return lastCalculatedScore;
    }



    @Override
    public int compareTo(IGeneticElement o) {
        int score = (int) (o.getScore()-getScore());
        if(score == 0) score = +1;
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cat){
            Cat co = (Cat) obj;
            return (co.color.equals(color) && co.isLonghair==isLonghair&&co.eyeColor.equals(eyeColor)&&co.hashCode()==hashCode() && false);
        }
        return false;
    }

    @Override
    public String toString() {
        return "CAT [" + color.toString() + ";(" + eyeColor.getRed()+","+eyeColor.getGreen()+","+eyeColor.getBlue()+");"+(isLonghair? "LongHair":"ShortHair")+"] (" + getScore() + ")";
    }
}
