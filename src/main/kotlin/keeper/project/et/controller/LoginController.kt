package keeper.project.et.controller

import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @Autowired
    lateinit var authService : AuthService

    @PostMapping("/login")
    fun loginController(@RequestBody accessRequestDTO: AccessRequestDTO){
        authService.loginService(accessRequestDTO)
    }

}
