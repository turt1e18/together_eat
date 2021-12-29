package keeper.project.et.controller

import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.response.auth.ResponseIdDTO
import keeper.project.et.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LoginController {

    @Autowired
    lateinit var authService : AuthService

    // home test
    @GetMapping("/")
    fun hello(): ResponseEntity<String>{
        var msg = "포트포워딩 테스트용";
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/users/login")
    fun loginController(@RequestBody accessRequestDTO: AccessRequestDTO): ResponseEntity<Message>? {
        return authService.loginService(accessRequestDTO)
    }

    // test
    @GetMapping("/users/login")
    fun testLoginController(@RequestParam("userID")id : String, @RequestParam("userPW")pw:String): ResponseEntity<Message>? {
        val accessRequestDTO = AccessRequestDTO(id,pw)
        return authService.loginService(accessRequestDTO)
    }

    @PutMapping("/users/register")
    fun signUpController(@RequestBody signUpDTO: SignUpDTO): ResponseEntity<Message> {
        return authService.signUpService(signUpDTO)
    }

    @PostMapping("/find/id")
    fun findUserIdController(@RequestBody findInfoDTO: FindInfoDTO): ResponseEntity<ResponseIdDTO> {
        return authService.findIdService(findInfoDTO)
    }

    @PostMapping("/find/pw")
    fun findUserPwController(){

    }
}
