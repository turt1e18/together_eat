package keeper.project.et.sample.service

import keeper.project.et.sample.dao.SampleDAO
import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleLoginDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import keeper.project.et.sample.dto.response.StringMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SampleService {

    @Autowired
    lateinit var sampleDAO: SampleDAO

    fun getLogin(id: String, pw: String) {
        val loginDTO = SampleLoginDTO(id, pw)
        try {
            val res = sampleDAO.getLogin(loginDTO)
            if (res != null) {
                if(res.id == loginDTO.id && res.pw == loginDTO.pw)
                    ResponseEntity.status(200).body(StringMessage("Welcome"))
                else
                    ResponseEntity.status(403).body(StringMessage("Wrong data"))
            }
        }catch (e : Exception){

        }


    }

    fun postSignUp(sampleSignUpDTO: SampleSignUpDTO) {
        sampleDAO.setUser(sampleSignUpDTO)
    }

    fun putChangePw(sampleChangePwDTO: SampleChangePwDTO) {
        sampleDAO.changePw(sampleChangePwDTO)
    }

    fun deleteUser(id: String) {
        sampleDAO.deleteUser(id)
    }
}
