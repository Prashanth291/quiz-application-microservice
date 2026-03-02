package com.prashanth291.question_service.controller;



import com.prashanth291.question_service.model.Question;
import com.prashanth291.question_service.model.QuestionWrapper;
import com.prashanth291.question_service.model.Response;
import com.prashanth291.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable int id) {

        return questionService.getQuestion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add-question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question ques)
    {
        return questionService.addQuestion(ques);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable int id)
    {
        questionService.deleteQuestion(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQueston(@RequestBody Question ques)
    {
        return questionService.updateQuestion(ques);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String category, @RequestParam Integer numQues){
            return questionService.getQuestionsForQuiz(category,numQues);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/get-score")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> response){
        return questionService.calculateScore(response);
    }
    // Generate Quiz with rqst
    // Get Questions Based on (QuestionId)
    // get score
}
