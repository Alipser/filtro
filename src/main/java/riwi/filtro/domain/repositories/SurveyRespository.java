package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.Survey;


@Repository
public interface SurveyRespository extends JpaRepository <Survey, Integer> {
    public Survey findByTitle(String title);

}
