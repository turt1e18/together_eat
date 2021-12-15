package keeper.project.et.controller

import keeper.project.et.dto.MailDTO
import keeper.project.et.service.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController {

    @Autowired
    lateinit var mailService: MailService

    @PostMapping("/send")
    fun sendMailController(@RequestBody mailDTO: MailDTO) {
        mailService.sendMail(mailDTO)
    }

}
