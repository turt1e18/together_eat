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
    var dataResult: MutableList<Any> = mutableListOf()

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessLoginDTO: AccessLoginDTO): ResponseEntity<Any> {
        return try {
            val checkInfo = authDAO.getAccessInfo(accessLoginDTO)
            val response = LoginResponseDTO(checkInfo?.id!!, checkInfo.userID, checkInfo.userName, "success")
            dataResult = mutableListOf()

            if (checkInfo.userID == accessLoginDTO.userID
                && encoder.matches(accessLoginDTO.userPW, checkInfo.userPW)
            ) {
                dataResult.add(response)
                ResponseEntity.status(200).body(DataSet(dataResult))
            } else {
                dataResult.add(Message("failed"))
                ResponseEntity.status(403).body(DataSet(dataResult))
            }
        } catch (e: Exception) {
            dataResult.add(Message("failed"))
            ResponseEntity.status(403).body(DataSet(dataResult))
        }

    }

    fun signUpService(signUpDTO: SignUpDTO): ResponseEntity<Any> {
        dataResult = mutableListOf()
        return try {
            signUpDTO.userPW = encoder.encode(signUpDTO.userPW)
            val result = authDAO.setUserInfo(signUpDTO).toString()
            // ResponseEntity.status(200).body(DataSet(result))
            dataResult.add(Message("success"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        } catch (e: Exception) {
            dataResult.add(Message("failed"))
            ResponseEntity.status(403).body(DataSet(dataResult))
        }
    }

    fun duplicateCheckUserID(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserID(value))
        dataResult = mutableListOf()
        return if (resultObject.result) {
            // ResponseEntity.status(200).body(DataSet(resultObject))
            dataResult.add(Message("failed"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        }
        else{
            dataResult.add(Message("success"))
            ResponseEntity.status(400).body(DataSet(dataResult))
        }
    }

    fun duplicateCheckUserName(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserName(value))
        dataResult = mutableListOf()
        return if (resultObject.result) {
            // ResponseEntity.status(200).body(DataSet(resultObject))
            dataResult.add(Message("failed"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        }
        else{
            dataResult.add(Message("success"))
            ResponseEntity.status(400).body(DataSet(dataResult))
        }
    }

    fun duplicateCheckUserEmail(value: String): ResponseEntity<DataSet> {
        val resultObject = DuplicateCheckDTO(authDAO.checkUserEmail(value))
        dataResult = mutableListOf()
        return if (resultObject.result) {
            // ResponseEntity.status(200).body(DataSet(resultObject))
            dataResult.add(Message("failed"))
            ResponseEntity.status(200).body(DataSet(dataResult))
        }
        else{
            dataResult.add(Message("success"))
            ResponseEntity.status(400).body(DataSet(dataResult))
        }
    }

    fun findIDWithEmail(email: String): ResponseEntity<Any> {
        return try{
            val result = ResponseIdDTO(authDAO.getUserIdWithEmail(email))
            ResponseEntity.status(200).body(DataSet(result))
        }catch (e : DataAccessException){
            ResponseEntity.status(400).body(DataSet(Message("Wrong Email")))
        }
    }

    fun changePwWithID(id:String, pw:String): ResponseEntity<Any> {
        return try {
            authDAO.resetUserPW(id, encoder.encode(pw))
            ResponseEntity.status(200).body(DataSet(Message("Success")))
        }catch (e : DataAccessException){
            ResponseEntity.status(400).body(DataSet(Message("Wrong Value")))
        }
    }
}
