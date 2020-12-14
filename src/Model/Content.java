package Model;

public class Content {

    private double tries;
    private String student;
    private double score;
    private double avgScore;
    private String question;
    private String name;
    
    public Content(double tries, String student,double score, String question) {
    	reset();
    	this.tries=tries;
        this.student=student;
        this.score=score;
        this.question=question;
    }
    

    public Content(String name,double avgScore) {
    	reset();
        this.name=name;
        this.avgScore=avgScore;

    }
    
    public void reset() {
    	this.tries=0;
        this.student="";
        this.score=0;
        this.question="";
        this.name="";
        this.avgScore=0;
        
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
    
    public double getAverage() {
        return avgScore;
    }

    public String getQuestion() {
        return question;
    }
    
    public String getName() {
        return name;
    }
    
    
}