import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {
	private String filename;
	
	public QuestionReader(String file) {
		this.filename = file;
	}
	
	public List<Question> readQuestions() {
		List<Question> questionsToRead = new ArrayList<Question>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filename));
			while (true) {
				String row = csvReader.readLine();
				if (row == null) {
					break;
				}
			    String[] data = row.split(",");
			    Question q = new Question(data[0],data[1],data[2],data[3],data[4],data[5]);
			    questionsToRead.add(q);
			    
			}

			csvReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("creating question.csv file");
			File file = new File("resources/question.csv");
			try {
				file.createNewFile();
			}
			catch (IOException f) {
				System.out.println("something went wrong.");
			}
			
		} catch (Exception e) {
			System.out.println("Something has gone horribly wrong.");
		}

		return questionsToRead;
	}
	
	public void saveQuestions(List<Question> questionList) {
		try {
			BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filename));
			for (Question q: questionList) {
				csvWriter.write(q.getCSV());
				csvWriter.newLine();
			}
			csvWriter.close();
			
		} catch(IOException e) {
			System.out.println("something went wrong writing questions");
		}
	}

}
