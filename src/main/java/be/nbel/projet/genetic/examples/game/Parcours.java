package be.nbel.projet.genetic.examples.game;

import be.nbel.projet.genetic.IGeneticElement;
import be.nbel.projet.genetic.examples.cat.Cat;
import be.nbel.projet.genetic.examples.cat.CatColors;
import be.nbel.projet.labo1.model.MovementMapper;
import be.nbel.projet.labo1.model.Movements;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Integer.min;
import static java.lang.Math.max;

public class Parcours implements IGeneticElement {
    private int RANDOM_MIN = 10;
    private int RANDOM_MAX = 15;

    private LinkedList<Movements> actions;
    private double score = Double.NaN;
    private int index = 0;


    public Parcours(){
        this.actions = new LinkedList<>();
        Random r = new Random();
        for(int i = 0; i < RANDOM_MIN + r.nextInt(RANDOM_MAX-RANDOM_MIN); i++){
            actions.add(Movements.values()[r.nextInt(Movements.values().length)]);
        }
    }
    public Parcours(LinkedList<Movements> newActions) {
        this.actions = newActions;
    }

    public Movements next(){
        return (actions.size()>index ? actions.get(index++): null);
    }

    @Override
    public synchronized double getScore() {
        return score;
    }

    public synchronized void setScore(double d){
        this.score = d+actions.size();
    }

    @Override
    public IGeneticElement crossOver(IGeneticElement otherParent) {

        Random r = new Random();
        double scoreRatio = getScore()/(getScore()+otherParent.getScore())*100;
        LinkedList<Movements> newActions = new LinkedList<>();
        if(otherParent instanceof Parcours) {
            int min = min(actions.size(),((Parcours) otherParent).actions.size());
            for (int i = 0; i < max(actions.size(),((Parcours) otherParent).actions.size()); i++){
                Movements toAdd;
                if(i < min){
                    if(r.nextInt(100) < scoreRatio) toAdd = actions.get(i);
                    else toAdd = ((Parcours) otherParent).actions.get(i);
                }else{
                    if(actions.size()>min) toAdd = actions.get(i);
                    else toAdd = ((Parcours) otherParent).actions.get(i);
                }
                newActions.add(toAdd);
            }
        }else newActions = (LinkedList<Movements>) actions.clone();
        return  new Parcours(newActions);
    }

    @Override
    public boolean mutate(double mutationFactor) {
        Random r = new Random();
        if(r.nextInt(100) <= mutationFactor*100){
            int index = r.nextInt(4);
            if(actions.size()<3 && index==0) index++;
            switch (index){
                case 0: // Remove 1 element
                    this.actions.remove(r.nextInt(actions.size()));
                    break;
                case 1: // Add 1 element
                    this.actions.add(r.nextInt(actions.size()), Movements.values()[r.nextInt(Movements.values().length)]);
                    break;
                case 2: // Mutate 1 element
                case 3:
                    this.actions.set(r.nextInt(actions.size()), Movements.values()[r.nextInt(Movements.values().length)]);
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(IGeneticElement o) {
        int score = (int) (o.getScore()-getScore())*-1;
        if(score == 0) score = -1;
        return score;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Parcours [");
        int i = 0;
        for (Movements action : this.actions) {
            if(i==index) builder.append(">");
            builder.append(action.toString());
            builder.append(",");
            i++;
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        return builder.toString();
    }
}
