package com.csit321g2.ppfp.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.ppfp.Entity.UserEntity;
import com.csit321g2.ppfp.Repository.UserRepository;

@Service

public class UserService {

	@Autowired
	UserRepository urepo;

	// C - Create or insert user record in tbluser
	public UserEntity insertUser(UserEntity user) {
		return urepo.save(user);
	}

	// R - Read all records in tbluser
	public List<UserEntity> getAllUsers() {
		return urepo.findAll();
	}

	// U - update a user
	// @SuppressWarnings("finally")
	public UserEntity updateUser(int sid, UserEntity newUserDetails) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			user.setFname(newUserDetails.getFname());
			user.setLname(newUserDetails.getLname());
			user.setGender(newUserDetails.getGender());
			user.setEmail(newUserDetails.getEmail());
			user.setPassword(newUserDetails.getPassword());
			user.setAdmin(newUserDetails.isAdmin());
			user.setMod(newUserDetails.isMod());
			user.setApplying(newUserDetails.isApplying());
			user.setApproved(newUserDetails.isApproved());
			user.setRejected(newUserDetails.isRejected());
			user.setDeleted(newUserDetails.isDeleted());
			return urepo.save(user);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		}
	}

	// Applying to become an Admin
	@SuppressWarnings("finally")
	public UserEntity applyForAdmin(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Checks if user is a moderator
			if (user.isMod()) {
				System.out.println("User " + sid + " is applying to become an admin.");
				user.setApplying(true);
			} else {
				System.out.println("User " + sid + " is not a moderator.");
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	// Apply to be mod

	@SuppressWarnings("finally")
	public UserEntity applyForModerator(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Checks if user is a moderator
			System.out.println("User " + sid + " is applying to become a moderator.");
			user.setApplying(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	// D - Delete a user
	public String deleteUser(int sid) {
		String msg = "";

		if (urepo.findById(sid) != null) {
			urepo.deleteById(sid);
			msg = "User " + sid + " is successfully deleted!";
		} else {
			msg = "User " + sid + " does not exist";
		}
		return msg;
	}

	// - Handles Sign In
	public UserEntity signIn(String email, String password) {
		try {
			System.out.println("Searching for user by email: " + email);
			UserEntity user = urepo.findByEmail(email)
					.orElseThrow(() -> new NoSuchElementException("User with email " + email + " does not exist!"));

			System.out.println("User found: " + user.toString());

			if (user.isDeleted()) {
				throw new NoSuchElementException("Account does not exist for user with email " + email);
			}

			// Verify the password
			if (!password.equals(user.getPassword())) {
				throw new NoSuchElementException("Invalid password for user with email " + email);
			}

			System.out.println("Password verified successfully");
			return user;

		} catch (NoSuchElementException ex) {
			System.err.println("Exception: " + ex.getMessage());
			throw ex;
		}
	}

	// - Handles Sign Up
	public UserEntity signUpUser(UserEntity user) {

		// Checks if email already exists
		if (urepo.findByEmail(user.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email " + user.getEmail() + " is already registered!");
		}

		if (isNullOrEmpty(user.getFname()) || isNullOrEmpty(user.getLname()) || isNullOrEmpty(user.getEmail()) ||
				isNullOrEmpty(user.getPassword()) || isNullOrEmpty(user.getGender())) {
			throw new IllegalArgumentException("First name, last name, email, password, and gender cannot be empty.");
		}

		// Validate password
		String password = user.getPassword();
		if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$")) {
			throw new IllegalArgumentException(
					"Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one special character.");
		}

		return urepo.save(user);
	}

	// - Checks is String is empty
	private boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// Finds User by ID
	public Optional<UserEntity> findByUid(int uid) {
		return urepo.findByUid(uid);
	}

	// Admin approves admin to be moderator

	@SuppressWarnings("finally")
	public UserEntity ApproveModeratorToAdmin(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Check if isApplying and isMod is true
			if (user.isApplying() && user.isMod()) {
				// Set isApproved and isRejected based on the value of newUserDetails
				user.setApproved(true);
				user.setApplying(false);
				user.setMod(false);
			}
			// Check if isApproved is true
			if (user.isApproved()) {
				user.setApproved(false);
				user.setAdmin(true);

			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	// Admin rejects admin to be moderator

	@SuppressWarnings("finally")
	public UserEntity RejectModeratorToAdmin(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Check if isApplying and isMod is true
			if (user.isApplying()) {
				// Set isApproved and isRejected based on the value of newUserDetails
				user.setRejected(true);
				user.setApplying(false);
				user.setMod(true);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	// Admin approves user to be moderator

	@SuppressWarnings("finally")
	public UserEntity ApproveUserToModerator(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Check if isApplying is true
			if (user.isApplying()) {
				// Set isApproved and isRejected based on the value of newUserDetails
				user.setApproved(true);
				user.setApplying(false);
			}
			// Check if isApproved is true
			if (user.isApproved()) {
				user.setMod(true);
				user.setApproved(false);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	@SuppressWarnings("finally")
	public UserEntity RejectUserToModerator(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			// Check if isApplying is true
			if (user.isApplying()) {
				// Set isApproved and isRejected based on the value of newProjectDetails
				user.setRejected(true);
				user.setApplying(false);
			}

		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);

		}
	}

	public List<UserEntity> getAllIsApplyingUsers() {
		return urepo.findByIsApplying(true);
	}

	public List<UserEntity> findUsersByIsApplyingAndIsNotMod(boolean isApplying) {
		return urepo.findByIsApplyingAndIsNotMod(isApplying);
	}

	public List<UserEntity> findUsersByIsModAndIsApplying(boolean isMod, boolean isApplying) {
		return urepo.findByIsModAndIsApplying(isMod, isApplying);
	}

	// Delete by setting isDeleted to true
	@SuppressWarnings("finally")
	public UserEntity softDeleteUser(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			user.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

	// Delete by setting isDeleted to true
	@SuppressWarnings("finally")
	public UserEntity adminSoftDeleteUser(int sid) {
		UserEntity user = new UserEntity();
		try {
			// Search by ID
			user = urepo.findById(sid).get();
			user.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("User " + sid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}

}