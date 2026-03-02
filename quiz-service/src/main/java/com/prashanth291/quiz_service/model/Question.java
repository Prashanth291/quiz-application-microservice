package com.prashanth291.quiz_service.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;
    private String difficultylevel;
    private String option1,option2,option3,option4;
    private String question_title;
    private String right_answer;

}
