package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.response.auth.ResponseIdDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {

    val encoder = BCryptPasswordEncoder()

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessRequestDTO: AccessRequestDTO): ResponseEntity<Message>? {
        return try {
            val checkInfo = authDAO.getAccessInfo(accessRequestDTO)

            if (checkInfo?.userID == accessRequestDTO.userID
                && encoder.matches(accessRequestDTO.userPW, checkInfo.userPW)
            ) {
                ResponseEntity.status(200).body(Message("Success"))
            } else {
                ResponseEntity.status(403).body(Message("Wrong ID & PW"))
            }
        } catch (e: Exception) {
            ResponseEntity.status(403).body(Message("Wrong ID & PW"))
        }

    }

    fun signUpService(signUpDTO: SignUpDTO): ResponseEntity<Message> {
        return try {
            signUpDTO.userPW = encoder.encode(signUpDTO.userPW)
            val result = authDAO.setUserInfo(signUpDTO).toString()
            ResponseEntity.status(200).body(Message(result))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(Message("Client Error"))
        }
    }

    fun findIdService(findInfoDTO: FindInfoDTO): ResponseEntity<ResponseIdDTO> {
        return try {
            val result = authDAO.getUserIdWithTel(findInfoDTO).toString()
            ResponseEntity.status(200).body(ResponseIdDTO(result))
        } catch (e: Exception){
            ResponseEntity.status(400).body(ResponseIdDTO(userID="0", msg = "testTest"))
        }
    }
}
