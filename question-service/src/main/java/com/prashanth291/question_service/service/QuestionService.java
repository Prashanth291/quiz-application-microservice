package com.prashanth291.question_service.service;


import com.prashanth291.question_service.model.Question;
import com.prashanth291.question_service.model.QuestionWrapper;
import com.prashanth291.question_service.model.Response;
import com.prashanth291.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public Optional<Question> getQuestion(int id) {
        return questionRepository.findById(id);
    }

    public ResponseEntity<Question> addQuestion(Question ques) {
        try{
            return new ResponseEntity<>(questionRepository.save(ques),HttpStatus.CREATED);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    public ResponseEntity<Question> updateQuestion(Question ques) {
        try{
            return new ResponseEntity<>(questionRepository.save(ques),HttpStatus.CREATED);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String cat) {
        try{
            return new ResponseEntity<>(questionRepository.findAllByCategory(cat),HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQues) {
        try{
            List<Integer> questions = questionRepository.findRandomQuestionsByCategory(category,numQues);
            return new ResponseEntity<>(questions,HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        try{
            List<Question> questionsFromDB = new ArrayList<>();
            for(Integer id:questionIds)
            {
                questionsFromDB.add(questionRepository.findById(id).get());
            }

            List<QuestionWrapper> wrappedQuestions = new ArrayList<>();
            for(Question question:questionsFromDB)
            {
                wrappedQuestions.add(new QuestionWrapper(
                        question.getId(),question.getQuestion_title(),question.getOption1(), question.getOption2(),
                        question.getOption3(),question.getOption4()));
            }

            return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        try{
            int score = 0;
            for(Response response:responses){
                int questionId = response.getId();
                if(response.getResponse().equals(questionRepository.findById(questionId).get().getRight_answer())){
                    score++;
                }
            }
            return new ResponseEntity<>(score,HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
    }
}
