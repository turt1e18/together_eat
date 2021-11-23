package keeper.project.et.sample.service

import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleLoginDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import org.springframework.stereotype.Service

@Service
class SampleService {

    fun getLogin(id: String, pw: String) {
        val loginDTO = SampleLoginDTO(id,pw)



    }

    fun postSignUp(sampleSignUpDTO: SampleSignUpDTO) {

    }

    fun putChangePw(sampleChangePwDTO: SampleChangePwDTO) {

    }

    fun deleteUser(id : String) {

    }
}
