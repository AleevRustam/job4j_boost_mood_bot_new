package ru.job4j.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.*;

@Profile("test")
@Repository
public class UserFakeRepository implements UserRepository {

    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    /*@Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findByClientId(Long clientId) {
        return userMap.get(clientId);
    }

    public void add(User user) {
        userMap.put(user.getClientId(), user);
    }*/
}
