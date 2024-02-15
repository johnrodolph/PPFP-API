package com.csit321g2.ppfp.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit321g2.ppfp.Entity.ProjectEntity;
import com.csit321g2.ppfp.Repository.ProjectRepository;

@Service

public class ProjectService {

	@Autowired
	ProjectRepository prepo;

	// C - Create or insert project record in tblProject
	public ProjectEntity insertProject(ProjectEntity project) {
		return prepo.save(project);
	}

	// R - Read all records in tblProject
	public List<ProjectEntity> getAllProjects() {
		return prepo.findAll();
	}

	// U - update a project
	@SuppressWarnings("finally")
	public ProjectEntity updateProject(int pid, ProjectEntity newProjectDetails) {
		ProjectEntity project = new ProjectEntity();
		try {
			// Search by ID
			project = prepo.findById(pid).get();
			project.setProjectTitle(newProjectDetails.getProjectTitle());
			project.setProjectDesc(newProjectDetails.getProjectDesc());
			project.setProjectCode(newProjectDetails.getProjectCode());
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Project " + pid + " does not exist!");
		} finally {
			return prepo.save(project);
		}
	}

	// Approve a project
	@SuppressWarnings("finally")
	public ProjectEntity approveProjectModerator(int pid) {
		ProjectEntity project = new ProjectEntity();
		try {
			// Search by ID
			project = prepo.findById(pid).get();
			// Check if isPending is true
			if (project.isPending()) {
				// Set isApproved and isRejected
				project.setApproved(true);
				project.setRejected(false);
				project.setPending(false);
			} else {
				// Set isApproved and isRejected automatically based on the condition
				project.setApproved(false);
				project.setRejected(false);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Project " + pid + " does not exist!");
		} finally {
			// Save changes to the database if not already saved
			return prepo.save(project);
		}
	}

	// Reject a project
	@SuppressWarnings("finally")
	public ProjectEntity rejectProjectModerator(int pid) {
		ProjectEntity project = new ProjectEntity();
		try {
			// Search by ID
			project = prepo.findById(pid).get();
			// Check if isPending is true
			if (project.isPending()) {
				// Set isApproved and isRejected
				project.setRejected(true);
				project.setApproved(false);
				project.setPending(false);
			} else {
				// Set isApproved and isRejected automatically based on the condition
				project.setApproved(false);
				project.setRejected(false);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Project " + pid + " does not exist!");
		} finally {
			// Save changes to the database if not already saved
			return prepo.save(project);
		}
	}

	// Filtered read projects for dashboard - isApproved = true
	public List<ProjectEntity> getAllApprovedProjects() {
		return prepo.findByIsApprovedAndIsDeleted(true, false);
	}

	// Filtered read projects for dashboard - isApproved = false and isRejected =
	// false
	public List<ProjectEntity> getAllProjectsDisplay() {
		return prepo.findByIsApprovedAndIsRejected(false, false);
	}

	public List<ProjectEntity> getAllUserProjects(int uid) {
		return prepo.findByUserIdAndIsDeleted(uid, false);
	}

	public Optional<ProjectEntity> findById(int pid) {
		return prepo.findById(pid);
	}

	// delete a project using update isdelete to true
	@SuppressWarnings("finally")
	public ProjectEntity deleteProject(int pid) {
		ProjectEntity project = new ProjectEntity();
		try {
			// Search by ID
			project = prepo.findById(pid).get();
			project.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Project " + pid + " does not exist!");
		} finally {
			return prepo.save(project);
		}
	}

	// AdminPage delete
	@SuppressWarnings("finally")
	public ProjectEntity adminSoftDeleteProject(int pid) {
		ProjectEntity project = new ProjectEntity();
		try {
			// Search by ID
			project = prepo.findById(pid).get();
			project.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Project " + pid + " does not exist!");
		} finally {
			return prepo.save(project);
		}
	}
}
