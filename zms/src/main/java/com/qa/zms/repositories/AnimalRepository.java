package com.qa.zms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.zms.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{
	
}
