package keeper.project.et.service

import keeper.project.et.dao.PostDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.post.DeletePostDTO
import keeper.project.et.dto.request.post.UploadModifyPostDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PostService {

    @Autowired
    lateinit var postDAO: PostDAO
    var dataResult: MutableList<Any> = mutableListOf()

    fun postUploadService(uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        dataResult = mutableListOf()
        return try {
            postDAO.uploadPostInfo(uploadModifyPostDTO)
            dataResult.add(Message("success"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        } catch (e: Exception) {
            dataResult.add(Message(e.toString()))
            ResponseEntity.status(404).body(DataSet(dataResult))
        }
    }

    fun postDeleteService(value: Int): ResponseEntity<Any> {
        val resultObject = DeletePostDTO(postDAO.deletePostInfo(value))
        dataResult = mutableListOf()

        return if (resultObject.result == 1) {
            dataResult.add(Message("success"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        }
        else{
            dataResult.add(Message("failed"))
            ResponseEntity.status(400).body(DataSet(dataResult))
        }
    }

    fun postModifyService(uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        dataResult = mutableListOf()
        return try {
            postDAO.modifyPostInfo(uploadModifyPostDTO)
            dataResult.add(Message("success"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        } catch (e: Exception) {
            dataResult.add(Message(e.toString()))
            ResponseEntity.status(404).body(DataSet(dataResult))
        }
    }
}