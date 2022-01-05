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

    @GetMapping("/posts")
    fun getAllPostController(): ResponseEntity<Any> {
        return postService.getAllPostService()
    }

    @PostMapping("/posts")
    fun uploadPostController(@RequestBody uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        return postService.postUploadService(uploadModifyPostDTO)
    }

    @PutMapping("/posts")
    fun modifyPostController(@RequestBody uploadModifyPostDTO: UploadModifyPostDTO): ResponseEntity<Any> {
        return postService.postModifyService(uploadModifyPostDTO)
    }

    @GetMapping("/posts/{postNum}")
    fun getSomePostController(@PathVariable postNum: Int): ResponseEntity<Any> {
        return postService.getSomePostService(postNum)
    }

    @DeleteMapping("/posts/{postNum}")
    fun deletePostController(@PathVariable postNum: Int): ResponseEntity<Any> {
        return postService.postDeleteService(postNum)
    }

}