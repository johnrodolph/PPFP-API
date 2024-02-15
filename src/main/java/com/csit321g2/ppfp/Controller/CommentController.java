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
import com.csit321g2.ppfp.Entity.CommentEntity;
import com.csit321g2.ppfp.Service.CommentService;

@RestController
@RequestMapping("/Comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

	@Autowired
	CommentService cserv;

	// C - Create a Comment record
	@PostMapping("/insertComment")
	public CommentEntity insertComment(@RequestBody CommentEntity comment) {
		return cserv.insertComment(comment);
	}

	// R - Read all Comment
	@GetMapping("/getAllComments")
	public List<CommentEntity> getAllComments() {
		return cserv.getAllComments();
	}

	// U - Update a Comment
	@PutMapping("/updateComment")
	public CommentEntity updateComment(@RequestParam int cid, @RequestBody CommentEntity newCommentDetails) {
		return cserv.updateComment(cid, newCommentDetails);
	}

	// Moderator approve/reject pending comment
	@PutMapping("/approveCommentModerator")
	public CommentEntity approveCommentModerator(@RequestParam int cid) {
		return cserv.approveCommentModerator(cid);
	}

	// Moderator approve/reject pending comment
	@PutMapping("/rejectCommentModerator")
	public CommentEntity rejectCommentModerator(@RequestParam int cid) {
		return cserv.rejectCommentModerator(cid);
	}

	// Read comments by Cid
	@PutMapping("/getAllProjectComments")
	public List<CommentEntity> getAllCommentsByProjectId(@RequestParam int pid) {
		return cserv.getAllCommentsByProjectId(pid);
	}

	// Filtered read comments - isApproved = false and isRejected = false
	@GetMapping("/getAllProjectCommentsDisplay")
	public List<CommentEntity> getAllProjectCommentsDisplay() {
		return cserv.getAllProjectCommentsDisplay();
	}

	// Read comments by Id
	@PutMapping("/findById")
	public Optional<CommentEntity> findById(@RequestParam int cid) {
		return cserv.findById(cid);
	}

	@PutMapping("/deleteComment")
	public CommentEntity deleteComment(@RequestParam int cid) {
		return cserv.deleteComment(cid);
	}

	@PutMapping("/adminSoftDeleteComment/{cid}")
	public CommentEntity adminSoftDeleteComment(@PathVariable int cid) {
		return cserv.adminSoftDeleteComment(cid);
	}

}
