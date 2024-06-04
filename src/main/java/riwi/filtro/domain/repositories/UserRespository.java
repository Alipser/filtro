package riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.filtro.domain.entities.User;



@Repository
public interface UserRespository extends JpaRepository <User, Integer> {

}
