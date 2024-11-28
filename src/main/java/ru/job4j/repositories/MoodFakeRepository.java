package ru.job4j.repositories;

import org.springframework.test.fake.CrudRepositoryFake;
import ru.job4j.model.Mood;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoodFakeRepository
        extends CrudRepositoryFake<Mood, Long>
        implements MoodRepository {

    public List<Mood> findAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public Mood save(Mood entity) {
        if (entity.getId() == null) {
            entity.setId(generateId());
        }
        return super.save(entity);
    }

    private Long generateId() {
        return memory.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L) + 1;
    }

}
