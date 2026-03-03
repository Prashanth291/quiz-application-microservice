package com.prashanth291.quiz_service.controller;


import com.prashanth291.quiz_service.model.QuestionWrapper;
import com.prashanth291.quiz_service.model.QuizDto;
import com.prashanth291.quiz_service.model.Response;
import com.prashanth291.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQues(), quizDto.getTitle());
    }

    @GetMapping("/get-quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id)
    {
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateScore(@PathVariable int id,@RequestBody List<Response> quizResponse)
    {
        return quizService.calculateScore(id,quizResponse);
    }


}
