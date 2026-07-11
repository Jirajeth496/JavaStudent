package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Student;

public class StudentController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label scoreLabel;


    @FXML
    public void initialize() {
        Student student = new Student("6710552811", "Cream love Momo");
        student.addScore(100.00);
        showStudent(student);
    }

    private void showStudent(Student student) {
        nameLabel.setText(student.getName());
        idLabel.setText(student.getId());
        scoreLabel.setText(String.valueOf(student.getScore()));
    }
    }
