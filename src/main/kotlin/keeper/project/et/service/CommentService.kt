package keeper.project.et.service

import keeper.project.et.dao.CommentDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.post.comment.UploadModifyCommentDTO
import keeper.project.et.dto.response.post.PostDataSetDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CommentService {

    @Autowired
    lateinit var commentDAO: CommentDAO
    val message = { result : String ->
        listOf(Message(result))
    }

    fun commentUploadService(uploadModifyCommentDTO: UploadModifyCommentDTO): ResponseEntity<Any> {
        val resultObject = commentDAO.uploadCommentInfo(uploadModifyCommentDTO)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        } else {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun commentModifyService(uploadModifyCommentDTO: UploadModifyCommentDTO): ResponseEntity<Any> {
        val resultObject = commentDAO.modifyCommentInfo(uploadModifyCommentDTO)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        } else {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun commentDeleteService(com: Int, post: Int): ResponseEntity<Any>{
        val resultObject = commentDAO.deleteCommentInfo(com, post)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        } else {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun commentCancelService(post: Int): ResponseEntity<Any> {
        return  try {
            val resultObject = commentDAO.cancelCommentInfo(post)
            ResponseEntity.status(200).body(PostDataSetDTO(resultObject))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
        val resultObject = commentDAO.cancelCommentInfo(post)
    }
}