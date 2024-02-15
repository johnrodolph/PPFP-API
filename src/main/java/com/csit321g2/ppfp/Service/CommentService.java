package com.csit321g2.ppfp.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.ppfp.Entity.CommentEntity;
import com.csit321g2.ppfp.Repository.CommentRepository;

@Service

public class CommentService {

	@Autowired
	CommentRepository crepo;

	// C - Create or insert Comment
	public CommentEntity insertComment(CommentEntity comment) {
		return crepo.save(comment);
	}

	// R - Read all records in tblComment
	public List<CommentEntity> getAllComments() {
		return crepo.findAll();
	}

	// U - update a Comment
	@SuppressWarnings("finally")
	public CommentEntity updateComment(int cid, CommentEntity newCommentDetails) {
		CommentEntity comment = new CommentEntity();
		try {
			// Search by ID
			comment = crepo.findById(cid).get();
			comment.setApproved(false);
			comment.setPending(true);
			comment.setComment(newCommentDetails.getComment());
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Comment " + cid + " does not exist!");
		} finally {
			return crepo.save(comment);
		}
	}

	// Update comment status (approve)
	@SuppressWarnings("finally")
	public CommentEntity approveCommentModerator(int cid) {
		CommentEntity comment = new CommentEntity();
		try {
			// Search by ID
			comment = crepo.findById(cid).get();
			// Check if isPending is true
			if (comment.isPending()) {
				// Set isApproved and isRejected
				comment.setApproved(true);
				comment.setRejected(false);
				comment.setPending(false);
			} else {
				// Set isApproved and isRejected based on your requirements
				comment.setApproved(false);
				comment.setRejected(false);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Comment " + cid + " does not exist!");
		} finally {
			return crepo.save(comment);
		}
	}

	// Update comment status (approve)
	@SuppressWarnings("finally")
	public CommentEntity rejectCommentModerator(int cid) {
		CommentEntity comment = new CommentEntity();
		try {
			// Search by ID
			comment = crepo.findById(cid).get();
			// Check if isPending is true
			if (comment.isPending()) {
				// Set isApproved and isRejected
				comment.setRejected(true);
				comment.setApproved(false);
				comment.setPending(false);
			} else {
				// Set isApproved and isRejected based on your requirements
				comment.setApproved(false);
				comment.setRejected(false);
			}
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Comment " + cid + " does not exist!");
		} finally {
			return crepo.save(comment);
		}
	}

	// Read all records in tblComment by ID
	public List<CommentEntity> getAllCommentsByProjectId(int projectId) {
		return crepo.findAllByProjectIdAndIsDeleted(projectId, false);
	}

	// Filtered read comments - isApproved = false and isRejected = false
	public List<CommentEntity> getAllProjectCommentsDisplay() {
		return crepo.findByIsApprovedAndIsRejected(false, false);
	}

	public Optional<CommentEntity> findById(int cid) {
		return crepo.findById(cid);
	}

	@SuppressWarnings("finally")
	public CommentEntity deleteComment(int cid) {
		CommentEntity comment = new CommentEntity();
		try {
			// Search by ID
			comment = crepo.findById(cid).get();
			comment.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Comment " + cid + " does not exist!");
		} finally {
			return crepo.save(comment);
		}

	}

	// AdminPage delete
	@SuppressWarnings("finally")
	public CommentEntity adminSoftDeleteComment(int cid) {
		CommentEntity comment = new CommentEntity();
		try {
			// Search by ID
			comment = crepo.findById(cid).get();
			comment.setDeleted(true);
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Comment " + cid + " does not exist!");
		} finally {
			return crepo.save(comment);
		}
	}

}
