package application;

public class Question {
    private String questionText;
    private String[] options;
    private int correctOption;
    private int userAnswer = -1; // Default value indicating no user answer

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }
}
