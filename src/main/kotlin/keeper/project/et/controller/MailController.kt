package keeper.project.et.controller
import keeper.project.et.dto.MailDTO
import keeper.project.et.service.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MailController {

    @Autowired
    lateinit var mailService: MailService

    @GetMapping("/email/send")
    fun sendMailController(@RequestParam userEmail: String): ResponseEntity<Any> {
        val mailDTO = MailDTO(userEmail)
        return mailService.sendMail(mailDTO)
    }

    @GetMapping("/email/check")
    fun checkMailCodeController(@RequestParam("userEmail")userEmail:String, @RequestParam("code")code:String): ResponseEntity<Any> {
        return mailService.checkOTPCode(userEmail,code)
    }

}
