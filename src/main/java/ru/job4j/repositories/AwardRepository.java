package ru.job4j.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Award;

import java.util.List;

@Repository
public interface AwardRepository extends CrudRepository<Award, Long> {
    List<Award> findAll();
}
