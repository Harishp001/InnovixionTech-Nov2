package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private Quiz quiz;
    private int currentQuestionIndex;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        quiz = new Quiz();
        // Create and add sample questions
        Question question1 = new Question("Which of the following is not a Java feature?",
                new String[]{"Dynamic", "Architecture Neutral", "Use of pointers", "Object-oriented"}, 2);
        Question question2 = new Question("Is Java a programming language?",
                new String[]{"Yes", "No"," "," "}, 0);
        Question question3 = new Question("What is the full form of JVM?",
                new String[]{"Java Virtual Machine", "Java Very Mini", "Java Very Main", "None of the above"}, 0);
        Question question4 = new Question("What does AWT stands for?",
                new String[]{"Abstract Window Toolkit", "Advanced Window Toolkit", "All Window Tools", "None of the above"}, 0);
        Question question5 = new Question("Which package is automatically imported by every Java program?",
                new String[]{"java.lang", "java.util", "java.io", "java.net"}, 0);
        Question question6 = new Question("Which of the following is a marker interface in Java?",
                new String[]{"Serializable", "Comparable", "Cloneable", "Runnable"}, 0);
        Question question7 = new Question("What is the range of the char datatype in Java?",
                new String[]{"-128 to 127", "0 to 255", "0 to 65535", "Depends on the JVM"}, 2);
        Question question8 = new Question("Which method can be defined only once in a program?",
                new String[]{"finalize method", "main method", "static method", "private method"}, 1);
        Question question9 = new Question("Which of the following is not a keyword in Java?",
                new String[]{"class", "interface", "extends", "friend"}, 3);
        Question question10 = new Question("What is the size of the int datatype?",
                new String[]{"8 bits", "16 bits", "32 bits", "64 bits"}, 2);
        
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);
        quiz.addQuestion(question4);
        quiz.addQuestion(question5);
        quiz.addQuestion(question6);
        quiz.addQuestion(question7);
        quiz.addQuestion(question8);
        quiz.addQuestion(question9);
        quiz.addQuestion(question10);

        primaryStage.setTitle("Java Exam");

        Label titleLabel = new Label("Java Exam");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #336699;");

        Label questionLabel = new Label();
        questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton[] radioButtons = new RadioButton[4];
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new RadioButton();
            radioButtons[i].setToggleGroup(toggleGroup);
            radioButtons[i].setTextFill(Color.web("#333333"));
        }

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        submitButton.setOnAction(e -> {
            if (toggleGroup.getSelectedToggle() != null) {
                int userAnswer = toggleGroup.getToggles().indexOf(toggleGroup.getSelectedToggle());
                quiz.evaluateAnswer(currentQuestionIndex, userAnswer);
                if (currentQuestionIndex < quiz.getTotalScore() - 1) {
                    currentQuestionIndex++;
                    loadQuestion(currentQuestionIndex, questionLabel, radioButtons);
                } else {
                    displayResult(primaryStage);
                }
            }
        });

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setStyle("-fx-background-color: #f0f0f0;");
        vBox.getChildren().addAll(titleLabel, questionLabel, radioButtons[0], radioButtons[1], radioButtons[2], radioButtons[3], submitButton);

        Scene scene = new Scene(vBox, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);

        loadQuestion(currentQuestionIndex, questionLabel, radioButtons);
        primaryStage.show();
    }

    private void loadQuestion(int index, Label questionLabel, RadioButton[] radioButtons) {
        Question currentQuestion = quiz.getQuestion(index);
        questionLabel.setText(currentQuestion.getQuestionText());

        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < options.length; i++) {
            radioButtons[i].setText(options[i]);
            radioButtons[i].setSelected(false);
        }
    }

    private void displayResult(Stage primaryStage) {
        int score = quiz.getScore();
        int totalScore = quiz.getTotalScore();

        Label resultLabel = new Label("Quiz completed!\nYour score: " + score + "/" + totalScore);
        resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #336699;");

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-size: 16px; -fx-background-color: #f44336; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());

        VBox resultBox = new VBox(20);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setPadding(new Insets(20));
        resultBox.setStyle("-fx-background-color: #f0f0f0;");
        resultBox.getChildren().addAll(resultLabel, closeButton);

        Scene resultScene = new Scene(resultBox, 300, 200);

        primaryStage.setScene(resultScene);
        primaryStage.setX((primaryStage.getWidth() - resultScene.getWidth()) / 2);
        primaryStage.setY((primaryStage.getHeight() - resultScene.getHeight()) / 2);
    }
}