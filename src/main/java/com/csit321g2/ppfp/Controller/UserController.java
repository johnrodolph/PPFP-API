package com.csit321g2.ppfp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.ppfp.Entity.UserEntity;

import com.csit321g2.ppfp.Service.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService sserv;

	@GetMapping("/print")
	public String printHello() {
		return "Hello World!";
	}

	// C - Create a User record
	@PostMapping("/insertUser")
	public UserEntity insertUser(@RequestBody UserEntity user) {
		return sserv.insertUser(user);
	}

	// R - Read all User records in tblUser
	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers() {
		return sserv.getAllUsers();
	}

	// U - Update a User record
	@PutMapping("/updateUser")
	public UserEntity updateUser(@RequestParam int sid, @RequestBody UserEntity newUserDetails) {
		return sserv.updateUser(sid, newUserDetails);
	}

	// Moderator apply for admin position
	@PutMapping("/applyForAdmin")
	public UserEntity applyForAdmin(@RequestParam int sid) {
		return sserv.applyForAdmin(sid);
	}

	// User apply for moderator position
	@PutMapping("/applyForModerator")
	public UserEntity applyForModerator(@RequestParam int sid) {
		return sserv.applyForModerator(sid);
	}

	// Admin approve/reject pending user to moderator
	@PutMapping("/approveUserToModerator/{sid}")
	public UserEntity ApproveUserToModerator(@PathVariable int sid) {
		return sserv.ApproveUserToModerator(sid);
	}

	@PutMapping("/rejectUserToModerator/{sid}")
	public UserEntity RejectUserToModerator(@PathVariable int sid) {
		return sserv.RejectUserToModerator(sid);
	}

	@PutMapping("/approveModToAdmin/{sid}")
	public UserEntity ApproveModeratorToAdmin(@PathVariable int sid) {
		return sserv.ApproveModeratorToAdmin(sid);
	}

	@PutMapping("/rejectModToAdmin/{sid}")
	public UserEntity RejectModeratorToAdmin(@PathVariable int sid) {
		return sserv.RejectModeratorToAdmin(sid);
	}

	// D - Delete a User
	@DeleteMapping("/deleteUser/{sid}")
	public String deleteUser(@PathVariable int sid) {
		return sserv.deleteUser(sid);
	}

	@PostMapping("/authenticate")
	public UserEntity authenticate(@RequestBody UserEntity user) {
		// int isAuth = sserv.signIn(user.getEmail(), user.getPassword());

		/*
		 * if(isAuth) {
		 * return "Authentication successful";
		 * } else {
		 * return "Authentication failed";
		 * }
		 */
		return sserv.signIn(user.getEmail(), user.getPassword());
	}

	// - Create a User record in the SignUp Page
	@PostMapping("/signUpUser")
	public UserEntity signUpUser(@RequestBody UserEntity user) {
		return sserv.signUpUser(user);
	}

	// Read project by Id
	@PutMapping("/findByUid")
	public Optional<UserEntity> findById(@RequestParam int uid) {
		return sserv.findByUid(uid);
	}

	@GetMapping("/findUsersByIsApplyingAndIsNotMod")
	public List<UserEntity> findUsersByIsApplyingAndIsNotMod(@RequestParam boolean isApplying) {
		return sserv.findUsersByIsApplyingAndIsNotMod(isApplying);
	}

	@GetMapping("/findByIsModAndIsApplying")
	public List<UserEntity> findByIsModAndIsApplying(
			@RequestParam boolean isMod,
			@RequestParam boolean isApplying) {
		return sserv.findUsersByIsModAndIsApplying(isMod, isApplying);
	}

	// Soft Delete user
	@PutMapping("/softDeleteUser")
	public UserEntity softDeleteUser(@RequestParam int uid) {
		return sserv.softDeleteUser(uid);
	}

	@PutMapping("/adminSoftDeleteUser/{uid}")
	public UserEntity adminSoftDeleteUser(@PathVariable int uid) {
		return sserv.adminSoftDeleteUser(uid);
	}
}