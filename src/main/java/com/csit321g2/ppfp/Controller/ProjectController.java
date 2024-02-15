package com.csit321g2.ppfp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.ppfp.Entity.ProjectEntity;
import com.csit321g2.ppfp.Service.ProjectService;

@RestController
@RequestMapping("/Project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

	@Autowired
	ProjectService sserv;

	@GetMapping("/print")
	public String printHello() {
		return "Hello! Test Project";
	}

	// C - Create a Project record
	@PostMapping("/insertProject")
	public ProjectEntity insertProject(@RequestBody ProjectEntity project) {
		return sserv.insertProject(project);
	}

	// R - Read all Projects
	@GetMapping("/getAllProjects")
	public List<ProjectEntity> getAllProjects() {
		return sserv.getAllProjects();
	}

	// U - Update a Project record
	@PutMapping("/updateProject")
	public ProjectEntity updateProject(@RequestParam int sid, @RequestBody ProjectEntity newProjectDetails) {
		return sserv.updateProject(sid, newProjectDetails);
	}

	// Moderator approve pending project
	@PutMapping("/approveProjectModerator")
	public ProjectEntity approveProjectModerator(@RequestParam int pid) {
		return sserv.approveProjectModerator(pid);
	}

	// Moderator reject pending project
	@PutMapping("/rejectProjectModerator")
	public ProjectEntity rejectProjectModerator(@RequestParam int pid) {
		return sserv.rejectProjectModerator(pid);
	}

	// Filtered read projects for dashboard - isApproved = true
	@GetMapping("/getAllApprovedProjects")
	public List<ProjectEntity> getAllApprovedProjects() {
		return sserv.getAllApprovedProjects();
	}

	// Filtered read projects for dashboard - isApproved = false and isDeleted =
	// false
	@GetMapping("/getAllProjectsDisplay")
	public List<ProjectEntity> getAllProjectsDisplay() {
		return sserv.getAllProjectsDisplay();
	}

	// Filtered project list by User ID
	@GetMapping("/getAllUserProjects")
	public List<ProjectEntity> getAllUserProjects(@RequestParam int uid) {
		return sserv.getAllUserProjects(uid);
	}

	// Read project by Id
	@PutMapping("/findById")
	public Optional<ProjectEntity> findById(@RequestParam int pid) {
		return sserv.findById(pid);
	}

	@PutMapping("/deleteProject")
	public ProjectEntity deleteProject(@RequestParam int sid) {
		return sserv.deleteProject(sid);
	}

	@PutMapping("/adminSoftDeleteProject/{pid}")
	public ProjectEntity adminSoftDeleteProject(@PathVariable int pid) {
		return sserv.adminSoftDeleteProject(pid);
	}
}
