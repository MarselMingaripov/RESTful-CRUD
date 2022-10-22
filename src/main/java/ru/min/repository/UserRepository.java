package ru.min.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.min.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
