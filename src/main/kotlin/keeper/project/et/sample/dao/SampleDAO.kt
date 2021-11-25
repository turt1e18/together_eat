package keeper.project.et.sample.dao

import keeper.project.et.dao.SuperDAO
import keeper.project.et.sample.dto.request.SampleChangePwDTO
import keeper.project.et.sample.dto.request.SampleLoginDTO
import keeper.project.et.sample.dto.request.SampleSignUpDTO
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class SampleDAO : SuperDAO() {

    fun getLogin(sampleLoginDTO: SampleLoginDTO): SampleLoginDTO? {
        val mapper = RowMapper<SampleLoginDTO> { rs, _ ->
            SampleLoginDTO(
                rs.getString("id"),
                rs.getString("pw")
            )
        }
        val sql = "select id, pw from sample where id = '${sampleLoginDTO.id}';"
        return db.queryForObject(sql, mapper)
    }

    fun setUser(sampleSignUpDTO: SampleSignUpDTO) {

    }

    fun changePw(sampleChangePwDTO: SampleChangePwDTO) {

    }

    fun deleteUser(id: String) {

    }
}
