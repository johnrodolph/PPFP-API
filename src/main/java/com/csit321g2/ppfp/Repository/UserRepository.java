package com.csit321g2.ppfp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.ppfp.Entity.UserEntity;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUid(int uid);

	List<UserEntity> findByIsApplying(boolean isApplying);

	@Query("SELECT u FROM UserEntity u WHERE u.isApplying = :isApplying AND u.isMod != true")
	List<UserEntity> findByIsApplyingAndIsNotMod(@Param("isApplying") boolean isApplying);

	@Query("SELECT u FROM UserEntity u WHERE u.isMod = :isMod AND u.isApplying = :isApplying")
	List<UserEntity> findByIsModAndIsApplying(@Param("isMod") boolean isMod, @Param("isApplying") boolean isApplying);
}