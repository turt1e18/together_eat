package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dao.MailDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.MailDTO
import keeper.project.et.dto.Message
import keeper.project.et.dto.response.auth.CodeDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.mail.internet.MimeMessage

@Service
class MailService(val emailSender: JavaMailSender) {

    @Autowired
    lateinit var mailDAO: MailDAO

    @Autowired
    lateinit var authDAO : AuthDAO
//
//    fun random(): String = List(6) {
//        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
//    }.joinToString("")
//
//    fun createSimpleMessage(mail: MailDTO): MimeMessage {
//        val message: MimeMessage = emailSender.createMimeMessage()
//        val helper = MimeMessageHelper(message)
//
//        setupMessage(helper, mail)
//
//        return message
//    }
//
//    fun setupMessage(helper: MimeMessageHelper, mail: MailDTO) {
//        helper.setTo(mail.to)
//        helper.setSubject(mail.subject)
//        helper.setText(mail.text.toString())
//    }
//
//    fun sendMail(mail: MailDTO): ResponseEntity<Any> {
//        mail.text = random()
//        return try {
//            val checkMail = authDAO.checkUserEmail(mail.to)
//            if (!checkMail)
//                return ResponseEntity.status(400).body(DataSet(Message("Wrong Value")))
//
//            val msg = createSimpleMessage(mail)
//            emailSender.send(msg)
//            mailDAO.loggingOTPCode(mail.to, mail.text)
//
//            ResponseEntity.status(200).body(DataSet(Message("Ok")))
//        } catch (e: RuntimeException) {
//            ResponseEntity.status(500).body(DataSet(Message("Server Error")))
//        }
//    }
//
//    fun checkOTPCode(mail: String): ResponseEntity<Any> {
//        val code = CodeDTO(mailDAO.getLastOTPCode(mail))
//
//        return ResponseEntity.status(200).body(DataSet(code))
//    }

}
