package com.prashanth291.quiz_service.model;


import lombok.Data;

@Data
public class QuizDto {
    private String title, category;
    private int numQues;
}
