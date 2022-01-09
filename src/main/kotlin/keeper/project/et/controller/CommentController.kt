package keeper.project.et.controller

import keeper.project.et.dto.request.post.comment.UploadModifyCommentDTO
import keeper.project.et.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CommentController {

    @Autowired
    lateinit var commentService: CommentService

    @PostMapping("/posts/comment")
    fun uploadCommentController(@RequestBody uploadModifyCommentDTO: UploadModifyCommentDTO): ResponseEntity<Any> {
        return commentService.commentUploadService(uploadModifyCommentDTO)
    }

    @PutMapping("/posts/comment")
    fun modifyCommentController(@RequestBody uploadModifyCommentDTO: UploadModifyCommentDTO): ResponseEntity<Any> {
        return commentService.commentModifyService(uploadModifyCommentDTO)
    }

    @DeleteMapping("/posts/comment")
    fun deleteCommentController(@RequestParam("com") com: Int, @RequestParam post: Int): ResponseEntity<Any> {
        return commentService.commentDeleteService(com, post)
    }
}