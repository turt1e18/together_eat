package keeper.project.et.sample.dao

import keeper.project.et.dao.SuperDAO
import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleLoginDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import org.springframework.stereotype.Repository

@Repository
class SampleDAO : SuperDAO() {

    fun getLogin(sampleLoginDTO: SampleLoginDTO) {
        db.queryForObject("",)
    }

    fun setUser(sampleSignUpDTO: SampleSignUpDTO) {

    }

    fun changePw(sampleChangePwDTO: SampleChangePwDTO) {

    }

    fun deleteUser(id: String) {

    }
}
