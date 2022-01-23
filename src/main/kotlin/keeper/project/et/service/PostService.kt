package keeper.project.et.service

import keeper.project.et.dao.PostDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.post.UploadModifyPostDTO
import keeper.project.et.dto.response.post.PostDataSetDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired
    lateinit var postDAO: PostDAO
    val message = { result : String ->
        listOf(Message(result))
    }

    fun postUploadService(uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        val resultObject = postDAO.uploadPostInfo(uploadModifyPostDTO)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        } else {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun postDeleteService(value: Int): ResponseEntity<Any> {
        val resultObject = postDAO.deletePostInfo(value)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        }
        else{
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun postModifyService(uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        val resultObject = postDAO.modifyPostInfo(uploadModifyPostDTO)

        return if (resultObject == 1) {
            ResponseEntity.status(200).body(DataSet(message("success")))
        } else{
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun getAllPostService(): ResponseEntity<Any> {
        return try {
            val resultObject = postDAO.getAllPost()
            ResponseEntity.status(200).body(DataSet(listOf(resultObject)))
        } catch (e: Exception) {
            ResponseEntity.status(400). body(DataSet(message("fail")))
        }
    }

    fun getSomePostService(postNum: Int): ResponseEntity<Any> {
        return try {
            val resultObject = postDAO.getSomePost(postNum)
            ResponseEntity.status(200).body(DataSet(listOf(resultObject)))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }
}