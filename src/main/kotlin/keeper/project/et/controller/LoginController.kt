package keeper.project.et.controller

import keeper.project.et.dto.DataSet
import keeper.project.et.dto.request.auth.AccessLoginDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LoginController {

    @Autowired
    lateinit var authService: AuthService

    // home test
    @GetMapping("/")
    fun hello(): ResponseEntity<String> {
        val msg = "포트포워딩 테스트용";
        return ResponseEntity.ok(msg);
    }

    /** login은 post, get 둘다 열어 놓고 나중에 하나 없앨 예정 */

    @PostMapping("/users/login")
    fun loginController(@RequestBody accessLoginDTO: AccessLoginDTO): ResponseEntity<Any> {
        return authService.loginService(accessLoginDTO)
    }

    @GetMapping("/users/login")
    fun testLoginController(
        @RequestParam("userID") id: String,
        @RequestParam("userPW") pw: String
    ): ResponseEntity<Any> {
        val accessRequestDTO = AccessLoginDTO(id, pw)
        return authService.loginService(accessRequestDTO)
    }

    @PutMapping("/users/register")
    fun signUpController(@RequestBody signUpDTO: SignUpDTO): ResponseEntity<Any> {
        return authService.signUpService(signUpDTO)
    }

    @GetMapping("/check/name")
    fun checkUserName(@RequestParam("userName") userName: String): ResponseEntity<DataSet> {
        return authService.duplicateCheckUserName(userName)
    }

    @GetMapping("/check/id")
    fun checkUserID(@RequestParam("userID") userID: String): ResponseEntity<Any> {
        return authService.duplicateCheckUserID(userID)
    }

    @GetMapping("/check/email")
    fun checkUserEmail(@RequestParam("userEmail") userEmail: String): ResponseEntity<DataSet> {
        return authService.duplicateCheckUserEmail(userEmail)
    }

    /**
     *
     * ID/PW 찾기 부분 공사중
     *
     */

    @GetMapping("/users/find/id")
    fun findIDWithEmailController(@RequestParam("userEmail")userEmail: String): ResponseEntity<Any> {
        return authService.findIDWithEmail(userEmail)
    }

    @GetMapping("/users/change/pw")
    fun changePwWithController(@RequestParam("userID")userID: String, @RequestParam("userPW")userPW:String): ResponseEntity<Any> {
        return authService.changePwWithID(userID,userPW)
    }
}
