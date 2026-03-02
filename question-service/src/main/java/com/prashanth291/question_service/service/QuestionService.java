package com.prashanth291.question_service.service;


import com.prashanth291.question_service.model.Question;
import com.prashanth291.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
