package keeper.project.et.service

import keeper.project.et.dao.AuthDAO
import keeper.project.et.dto.MessageDTO
import keeper.project.et.dto.request.auth.AccessRequestDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    lateinit var authDAO: AuthDAO

    fun loginService(accessRequestDTO: AccessRequestDTO): ResponseEntity<MessageDTO>? {
        return try{
            val checkInfo = authDAO.getAccessInfo(accessRequestDTO)

            if(checkInfo?.userId == accessRequestDTO.userId
                && checkInfo.userPw == accessRequestDTO.userPw){
                ResponseEntity.status(200).body(MessageDTO("Success"))
            }else{
                ResponseEntity.status(403).body(MessageDTO("Wrong ID & PW"))
            }
        }catch (e : Exception){
            ResponseEntity.status(403).body(MessageDTO("Wrong ID & PW"))
        }

    }
}
