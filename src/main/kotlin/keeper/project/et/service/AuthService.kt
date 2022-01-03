package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessLoginDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.response.auth.DuplicateCheckDTO
import keeper.project.et.dto.response.auth.LoginResponseDTO
import keeper.project.et.dto.response.auth.ResponseIdDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {

    val encoder = BCryptPasswordEncoder()

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessLoginDTO: AccessLoginDTO): ResponseEntity<Any> {
        return try {
            val checkInfo = authDAO.getAccessInfo(accessLoginDTO)
            val response = LoginResponseDTO(checkInfo?.id!!, checkInfo.userID, checkInfo.userName)

            if (checkInfo.userID == accessLoginDTO.userID
                && encoder.matches(accessLoginDTO.userPW, checkInfo.userPW)
            ) {
                ResponseEntity.status(200).body(DataSet(response))
            } else {
                ResponseEntity.status(403).body(DataSet(Message("Wrong ID & PW")))
            }
        } catch (e: Exception) {
            ResponseEntity.status(403).body(DataSet(Message("Wrong ID & PW")))
        }

    }

    fun signUpService(signUpDTO: SignUpDTO): ResponseEntity<Any> {
        return try {
            signUpDTO.userPW = encoder.encode(signUpDTO.userPW)
            val result = authDAO.setUserInfo(signUpDTO).toString()
            ResponseEntity.status(200).body(DataSet(Message(result)))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(DataSet(Message("Error")))
        }
    }

    fun duplicateCheckUserID(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserID(value))

        return if (resultObject.result)
            ResponseEntity.status(200).body(DataSet(resultObject))
        else
            ResponseEntity.status(400).body(DataSet(resultObject))
    }

    fun duplicateCheckUserName(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserName(value))

        return if (resultObject.result)
            ResponseEntity.status(200).body(DataSet(resultObject))
        else
            ResponseEntity.status(400).body(DataSet(resultObject))
    }

    fun duplicateCheckUserEmail(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserEmail(value))

        return if (resultObject.result)
            ResponseEntity.status(200).body(DataSet(resultObject))
        else
            ResponseEntity.status(400).body(DataSet(resultObject))
    }

    fun findIdService(findInfoDTO: FindInfoDTO): ResponseEntity<Any> {
        return try {
            val result = authDAO.getUserIdWithTel(findInfoDTO).toString()
            ResponseEntity.status(200).body(DataSet(ResponseIdDTO(result)))
        } catch (e: Exception) {
            ResponseEntity.status(400).body(DataSet(Message("Error")))
        }
    }
}
