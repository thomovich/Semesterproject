package Model;

public class Content {

	private String tries;
	private String student;
	private String score;
	private String question;
	
	public Content(String tries, String student,String score, String question) {
		this.question=question;
		this.score=score;
		this.student=student;
		this.tries=tries;
	}

	public String getTries() {
		return tries;
	}

	public String getStudent() {
		return student;
	}

	public String getScore() {
		return score;
	}

	public String getQuestion() {
		return question;
	}
}
