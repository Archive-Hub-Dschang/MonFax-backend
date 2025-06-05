package com.LDE.monFax_backend.repositories;

import com.LDE.monFax_backend.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository  extends JpaRepository<Level,Long> {
}
