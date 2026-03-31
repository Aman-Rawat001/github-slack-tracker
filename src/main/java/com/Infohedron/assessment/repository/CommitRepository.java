package com.Infohedron.assessment.repository;

import com.Infohedron.assessment.entity.CommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<CommitEntity, Long> {
}
