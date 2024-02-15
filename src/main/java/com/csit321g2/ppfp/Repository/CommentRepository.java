package com.csit321g2.ppfp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csit321g2.ppfp.Entity.CommentEntity;

@Repository

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
	List<CommentEntity> findAllByProjectIdAndIsDeleted(int projectId, boolean isDeleted);
	List<CommentEntity> findByIsApprovedAndIsDeleted(boolean isApproved, boolean isDeleted);
	List<CommentEntity> findByUserIdAndIsDeleted(int userId, boolean isDeleted);
	List<CommentEntity> findByIsApprovedAndIsRejected(boolean isApproved, boolean isRejected);
}
