package com.prashanth291.question_service.repository;

import com.prashanth291.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String Category);

    @Query(value="Select q.id from question q where q.category = :category order by Random() limit :numQues",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQues);
}
