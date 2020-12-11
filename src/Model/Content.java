package Model;

public class Content {

    private double tries;
    private String student;
    private double score;
    private double avgScore;
    private String question;
    private String nameId;
    
    public Content(double tries, String student,double score, String question) {
        this.tries=tries;
        this.student=student;
        this.score=score;
        this.question=question;
    }
    

        

    public Content(String nameId,double avgScore) {
        this.nameId=nameId;
        this.avgScore=avgScore;

    }

    public double getTries() {
        return tries;
    }

    public String getStudent() {
        return student;
    }

    public double getScore() {
        return score;
    }
    
    public double avgScore() {
        return avgScore;
    }

    public String getQuestion() {
        return question;
    }
    
    public String getNameId() {
        return nameId;
    }
    
    
}