package be.nbel.projet.genetic;

public enum Scoring {
    MINIMUM((a, b)-> a<=b), MAXIMUM((a, b)-> a>=b);

    private IScoring scoring;
    Scoring(IScoring scoring){
        this.scoring = scoring;
    }

    public boolean hasAttainedScore(double score, double objective){
        return scoring.hasAttainedScore(score, objective);
    }


    private interface IScoring{
        boolean hasAttainedScore(double score, double objective);
    }
}
