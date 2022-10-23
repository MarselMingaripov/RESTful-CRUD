package ru.min.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.min.entity.User;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, String> {
}
