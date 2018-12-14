package ua.nure.nlebed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.nlebed.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
