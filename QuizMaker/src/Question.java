
public class Question {
	private String question;
	private String ansA;
	private String ansB;
	private String ansC;
	private String ansD;
	private String correctAns;
	
	public Question(String q, String A, String B, String C, String D, String correct) {
		this.question = q;
		this.ansA = A;
		this.ansB = B;
		this.ansC = C;
		this.ansD = D;
		this.correctAns = correct;
	}
	
	public boolean isCorrect(String ans) {
		return this.correctAns.equals(ans);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(question);
		sb.append("\nA - " + ansA);
		sb.append("\nB - " + ansB);
		sb.append("\nC - " + ansC);
		sb.append("\nD - " + ansD);
		return sb.toString();
	}
	
	public String getCSV() {
		return question + "," + ansA + "," + ansB + "," + ansC + "," + ansD + "," + correctAns;
	}

}
