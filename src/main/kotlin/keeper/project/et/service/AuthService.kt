package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessRequestDTO: AccessRequestDTO): ResponseEntity<Message>? {
        return try {
            val checkInfo = authDAO.getAccessInfo(accessRequestDTO)

            if (checkInfo?.userId == accessRequestDTO.userId
                && checkInfo.userPw == accessRequestDTO.userPw
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
            val result = authDAO.setUserInfo(signUpDTO).toString()
            ResponseEntity.status(200).body(Message(result))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(Message("Client Error"))
        }
    }

    fun findIdService(findInfoDTO: FindInfoDTO) {
        return try {
            val result = authDAO.getUserId(findInfoDTO).toString()
        } catch (e: Exception){

        }
    }
}
