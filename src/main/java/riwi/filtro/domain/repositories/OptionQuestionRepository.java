package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.OptionQuestion;



@Repository
public interface OptionQuestionRepository extends JpaRepository <OptionQuestion, Integer> {

}
