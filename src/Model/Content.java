package Model;

public class Content {

    private double tries;
    private String student;
    private double score;
    private double avgScore;
    private String extra;
    private String name;
    
    public Content(double tries, String student,double score, String extra) {
    	
    	this.tries=tries;
        this.student=student;
        this.score=score;
        this.extra=extra;
    }
    
    
    public Content(double tries, String student,double score) {
    	
    	this.tries=tries;
    	this.student=student;
    	this.score=score;
    }
    

    public Content(String name,double avgScore) {
    	
        this.name=name;
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
    
    public double getAverage() {
        return avgScore;
    }

    public String getExtra() {
        return extra;
    }
    
    public String getName() {
        return name;
    }
    
    
}