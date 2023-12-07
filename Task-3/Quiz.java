package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Toggle;

public class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int getTotalScore() {
        return questions.size();
    }

    public void evaluateAnswer(int questionIndex, int userAnswer) {
        if (questions.get(questionIndex).getCorrectOption() == userAnswer) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
