package keeper.project.et.dao

import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.response.auth.AccessResponseDTO
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class AuthDAO : SuperDAO() {

    fun getAccessInfo(accessRequestDTO: AccessRequestDTO): AccessResponseDTO? {

        val mapper = RowMapper<AccessResponseDTO> {
            rs, _ ->
            AccessResponseDTO(
                rs.getString("userId"),
                rs.getString("userPw")
            )
        }

        val sql = "select userId, userPw from user_info where userId = '${accessRequestDTO.userId}' and userPw = '${accessRequestDTO.userPw}'"

        return try {
            db.queryForObject(sql,mapper)
        }catch (e : Exception){
            println(e)
            throw e
        }
    }

}
