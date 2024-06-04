package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.Question;


@Repository
public interface QuestionRepository extends JpaRepository <Question, Integer> {

}
