package keeper.project.et.controller

import keeper.project.et.dto.MailDTO
import keeper.project.et.dto.Message
import keeper.project.et.service.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MailController {

    @Autowired
    lateinit var mailService: MailService

    @GetMapping("/find/mail")
    fun findMailController(@RequestParam("email") email:String) {
        println(mailService.findUserMail(email))
    }

    @PostMapping("/send")
    fun sendMailController(@RequestBody mailDTO: MailDTO): ResponseEntity<Message> {
       return mailService.sendMail(mailDTO)
    }

}
