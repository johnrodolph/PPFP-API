package com.csit321g2.ppfp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csit321g2.ppfp.Entity.ProjectEntity;

@Repository

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{

	List<ProjectEntity> findByIsApprovedAndIsDeleted(boolean isApproved, boolean isDeleted);
	List<ProjectEntity> findByUserIdAndIsDeleted(int userId, boolean isDeleted);
	List<ProjectEntity> findByIsApprovedAndIsRejected(boolean isApproved, boolean isRejected);
}
