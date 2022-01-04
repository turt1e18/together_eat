package keeper.project.et.controller

import keeper.project.et.dto.request.post.UploadModifyPostDTO
import keeper.project.et.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PostController {

    @Autowired
    lateinit var postService: PostService

    @PostMapping("/posts")
    fun uploadPostController(@RequestBody uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        return postService.postUploadService(uploadModifyPostDTO)
    }

    @PutMapping("/posts")
    fun modifyPostController(@RequestBody uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        return postService.postModifyService(uploadModifyPostDTO)
    }

    @DeleteMapping("/posts/{postNum}")
    fun deletePostController(@PathVariable postNum: Int): ResponseEntity<Any> {
        return postService.postDeleteService(postNum)
    }

}