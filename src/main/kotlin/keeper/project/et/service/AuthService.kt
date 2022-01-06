package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dto.DataSet
import keeper.project.et.dto.Message
import keeper.project.et.dto.request.auth.AccessLoginDTO
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
    val message = { result : String ->
        listOf(Message(result))
    }

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessLoginDTO: AccessLoginDTO): ResponseEntity<Any> {
        return try {
            val checkInfo = authDAO.getAccessInfo(accessLoginDTO)
            val result = LoginResponseDTO(checkInfo?.id!!, checkInfo.userID, checkInfo.userName, "success")

            if (checkInfo.userID == accessLoginDTO.userID && encoder.matches(accessLoginDTO.userPW, checkInfo.userPW)) {
                ResponseEntity.status(200).body(DataSet(listOf(result)))
            } else {
                ResponseEntity.status(403).body(DataSet(message("fail")))
            }
        } catch (e: Exception) {
            ResponseEntity.status(403).body(DataSet(message("fail")))
        }

    }

    fun signUpService(signUpDTO: SignUpDTO): ResponseEntity<Any> {
        return try {
            signUpDTO.userPW = encoder.encode(signUpDTO.userPW)
            val result = authDAO.setUserInfo(signUpDTO).toString()
            ResponseEntity.status(200).body(DataSet(message("success")))
        } catch (e: Exception) {
            ResponseEntity.status(403).body(DataSet(message("fail")))
        }
    }

    fun duplicateCheckUserID(value: String): ResponseEntity<Any> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserID(value))
        return if (!resultObject.result) {
            ResponseEntity.status(200).body(DataSet(listOf(resultObject)))
        }
        else{
            ResponseEntity.status(400).body(DataSet(listOf(resultObject)))
        }
    }

    fun duplicateCheckUserName(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserName(value))
        return if (!resultObject.result) {
            ResponseEntity.status(200).body(DataSet(listOf(resultObject)))
        }
        else{
            ResponseEntity.status(400).body(DataSet(listOf(resultObject)))
        }
    }

    fun duplicateCheckUserEmail(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserEmail(value))
        return if (!resultObject.result) {
            ResponseEntity.status(200).body(DataSet(listOf(resultObject)))
        }
        else{
            ResponseEntity.status(400).body(DataSet(listOf(resultObject)))
        }
    }

    fun findIDWithEmail(email: String): ResponseEntity<Any> {
        return try{
            val result = ResponseIdDTO(authDAO.getUserIdWithEmail(email))
            ResponseEntity.status(200).body(DataSet(listOf(result)))
        }catch (e : DataAccessException){
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }

    fun changePwWithID(id:String, pw:String): ResponseEntity<Any> {
        return try {
            authDAO.resetUserPW(id, encoder.encode(pw))
            ResponseEntity.status(200).body(DataSet(message("success")))
        }catch (e : DataAccessException){
            ResponseEntity.status(400).body(DataSet(message("fail")))
        }
    }
}
