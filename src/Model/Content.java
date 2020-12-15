package Model;

public class Content {

    private double tries;
    private String student;
    private double score;
    private double avgScore;
    private String extra;
    private String name;
    
    public Content(double tries, String student,double score, String extra) {
    	reset();
    	this.tries=tries;
        this.student=student;
        this.score=score;
        this.extra=extra;
    }
    
    
    public Content(double tries, String student,double score) {
    	reset();
    	this.tries=tries;
    	this.student=student;
    	this.score=score;
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
        this.extra="";
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

    public String getExtra() {
        return extra;
    }
    
    public String getName() {
        return name;
    }
    
    
}