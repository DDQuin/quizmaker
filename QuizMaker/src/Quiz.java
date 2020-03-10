import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
	private static final String FILE_NAME = "resources/question.csv";
	private List<Question> questionList;
	private Scanner reader;
	private Random rand;
	private int score;
	private QuestionReader qr;
	
	public Quiz() {
		questionList = new ArrayList<Question>();
		qr = new QuestionReader(FILE_NAME);
		questionList = qr.readQuestions(); 
		reader = new Scanner(System.in);
		rand = new Random();
		score = 0;
		
	}
	
	public void start() {
		System.out.println("Welcome to QuizMaker!");
		takeResponse();
	}
	
	private void takeResponse() {
		score = 0;
		System.out.println("Type 'quit' to quit, 'list' to get a list of all questions,'quiz' to start generating quiz and 'add' to add questions");
		String response = getStringResponse();
		if (response.equals("quit")) {
			System.out.println("Thanks for playing!");
			System.exit(0);
		} else if (response.equals("list")) {
			printQuestionList();
			takeResponse();
		} else if (response.equals("quiz")){
			startQuiz();
		} else if (response.equals("add")) {
			addQuestion();
		}
	}
	
	private void addQuestion() {
		System.out.println("What is the question you want to add?");
		String question = reader.nextLine();
		System.out.println("What is the first choice A to the question? ");
		String A = reader.nextLine();
		System.out.println("What is the second choice B to the question? ");
		String B = reader.nextLine();
		System.out.println("What is the third choice C to the question? ");
		String C = reader.nextLine();
		System.out.println("What is the fourth choice D to the question? ");
		String D = reader.nextLine();
		System.out.println("Which letter is the answer?");
		String answer = getAnswer();
		Question q = new Question(question,A,B,C,D,answer);
		questionList.add(q);
		qr.saveQuestions(questionList);
		System.out.println("Question added and written to questions.csv!");
		takeResponse();
		
	}
	
	private void startQuiz() {
		if (questionList.isEmpty()) {
			System.out.println("No questions stored!");
			takeResponse();
		}
		int numQuestions = getNumQuestions();
		List<Question> randomQuestions = generateRandomQuestions(numQuestions);
		for (int i = 0; i < randomQuestions.size(); i++) {
			Question q = randomQuestions.get(i);
			System.out.println(q);
			String answer = getAnswer();
			if (q.isCorrect(answer)) {
				score++;
			}
		}
		System.out.println("Your final score was " + score + " out of " + randomQuestions.size());
		takeResponse();
	}
	
	private List<Question> generateRandomQuestions(int numQuestions) {
		ArrayList<Question> copyQuestions = new ArrayList<Question>();
		copyQuestions.addAll(questionList);
		ArrayList<Question> randomQuestions = new ArrayList<Question>();
		for (int i = 0; i < numQuestions; i++) {
			int randomNum = rand.nextInt(questionList.size());
			randomQuestions.add(copyQuestions.get(randomNum));
			copyQuestions.remove(randomNum);
		}
		return randomQuestions;
	}
	
	private String getStringResponse() {
		String response = reader.nextLine();
		while (!response.equals("quit") && !response.equals("quiz") && !response.equals("list") && !response.equals("add")) {
			System.out.println("The keywords are, 'quit', 'quiz', 'list' and 'add'!");
			response = reader.nextLine();
		}
		return response;
	}
	
	private int getNumQuestions() {
		System.out.println("Choose how many questions you want, must be between 1 and " + questionList.size());
		int numQuestions = reader.nextInt();
		while (numQuestions < 1 || numQuestions > questionList.size()) {
			System.out.println("Must be between 1 and " + questionList.size() + "!");
			numQuestions = reader.nextInt();
		}
		return numQuestions;
	}
	
	private String getAnswer() {
		String answer = reader.nextLine();
		while (!answer.equals("A") && !answer.equals("B") && !answer.equals("C") && !answer.equals("D")) {
			System.out.println("The answer is either A, B, C or D!");
			answer = reader.nextLine();
		}
		return answer;
	}
	
	private void printQuestionList() {
		if (questionList.isEmpty()) {
			System.out.println("No questions stored!");
			takeResponse();
		}
		for (Question q: questionList) {
			System.out.println(q + "\n");
		}
	}

}
