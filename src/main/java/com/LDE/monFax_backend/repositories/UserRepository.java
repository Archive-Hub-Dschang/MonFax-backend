package com.LDE.monFax_backend.repositories;

import com.LDE.monFax_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findTop5ByOrderByLastLoginDesc();
    Optional<User> findByUsername(String username);
}
