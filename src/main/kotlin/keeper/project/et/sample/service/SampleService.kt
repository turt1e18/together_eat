package keeper.project.et.sample.service

import keeper.project.et.sample.dao.SampleDAO
import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleLoginDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SampleService {

    @Autowired
    lateinit var sampleDAO: SampleDAO

    fun getLogin(id: String, pw: String) {
        val loginDTO = SampleLoginDTO(id,pw)

        sampleDAO.getLogin(loginDTO)


    }

    fun postSignUp(sampleSignUpDTO: SampleSignUpDTO) {
        sampleDAO.setUser(sampleSignUpDTO)
    }

    fun putChangePw(sampleChangePwDTO: SampleChangePwDTO) {
        sampleDAO.changePw(sampleChangePwDTO)
    }

    fun deleteUser(id : String) {
        sampleDAO.deleteUser(id)
    }
}
