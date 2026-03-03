package com.prashanth291.quiz_service.service;


import com.prashanth291.quiz_service.feign.QuizInterface;
import com.prashanth291.quiz_service.model.QuestionWrapper;
import com.prashanth291.quiz_service.model.Quiz;
import com.prashanth291.quiz_service.model.Response;
import com.prashanth291.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQues, String title)
    {
        try{
            List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQues).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);;
            quiz.setQuestionIds(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Create Quiz",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int id) {
        try{
//            Optional<Quiz> quiz = quizRepository.findById(id);
//            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> wrappedQuestions = new ArrayList<>();
//            for(Question question:questionsFromDB)
//            {
//                wrappedQuestions.add(new QuestionWrapper(
//                        question.getId(),question.getQuestion_title(),question.getOption1(), question.getOption2(),
//                        question.getOption3(),question.getOption4()));
//            }
            return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> calculateScore(int id, List<Response> quizResponse) {
        int score = 0;
        try{
            Quiz quiz = quizRepository.findById(id).get();
//            List<Question> questions = quiz.getQuestions();
//            int idx = 0;
//            for(QuizResponse res: quizResponse)
//            {
//                if(res.getResponse().equals(questions.get(idx).getRight_answer())) {
//                    score++;
//                }
//                idx++;
//            }
            return new ResponseEntity<>(score,HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(score,HttpStatus.BAD_REQUEST);
    }
}
