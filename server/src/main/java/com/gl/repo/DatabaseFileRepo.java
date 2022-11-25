package com.gl.repo;

import com.gl.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseFileRepo extends JpaRepository<DatabaseFile, String> {
    Optional<DatabaseFile> findByFileName(@Param("name") String fileName);
}
