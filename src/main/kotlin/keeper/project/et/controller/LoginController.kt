package keeper.project.et.controller

import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.response.auth.ResponseIdDTO
import keeper.project.et.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @Autowired
    lateinit var authService : AuthService

    @PostMapping("/login")
    fun loginController(@RequestBody accessRequestDTO: AccessRequestDTO): ResponseEntity<Message>? {
        return authService.loginService(accessRequestDTO)
    }

    @PostMapping("/addUser")
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
