package keeper.project.et.service

import keeper.project.et.dto.MailDTO
import keeper.project.et.dto.Message
import org.springframework.http.ResponseEntity
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.mail.internet.MimeMessage

@Service
class MailService(val emailSender: JavaMailSender) {

    fun random(): String = List(6) {
        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")

    fun sendMail(mail: MailDTO): ResponseEntity<Message> {
        mail.text = random()
        return try {
            val msg = createSimpleMessage(mail)
            emailSender.send(msg)
            ResponseEntity.status(200).body(Message(mail.text!!))
        } catch (e: RuntimeException) {
            ResponseEntity.status(500).body(Message("Server Error"))
        }
    }

    fun createSimpleMessage(mail: MailDTO): MimeMessage {
        val message: MimeMessage = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)

        setupMessage(helper, mail)

        return message
    }

    fun setupMessage(helper: MimeMessageHelper, mail: MailDTO) {
        helper.setTo(mail.to)
        helper.setSubject(mail.subject)
        helper.setText(mail.text.toString())
    }
}
