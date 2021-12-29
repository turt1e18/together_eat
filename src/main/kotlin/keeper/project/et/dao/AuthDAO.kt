package keeper.project.et.dao

import keeper.project.et.dto.request.auth.AccessRequestDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.response.auth.AccessResponseDTO
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class AuthDAO : SuperDAO() {

    fun getAccessInfo(accessRequestDTO: AccessRequestDTO): AccessResponseDTO? {

        val mapper = RowMapper<AccessResponseDTO> { rs, _ ->
            AccessResponseDTO(
                rs.getString("user_id"),
                rs.getString("user_pw")
            )
        }

        val sql =
            "select user_id, user_pw from user_info where user_id = '${accessRequestDTO.userId}' and user_pw = '${accessRequestDTO.userPw}'"

        return try {
            db.queryForObject(sql, mapper)
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

    fun setUserInfo(signUpDTO: SignUpDTO): Int? {

        val values =
            "'${signUpDTO.userId}', '${signUpDTO.userPw}', '${signUpDTO.userEmail}', '${signUpDTO.userName}', '${signUpDTO.userTel}'"
        val sql =
            "insert into user_info (user_id, user_pw, user_email, user_name, user_tel) values ($values)"

        return try {
            db.let {
                it.execute(sql)
                it.queryForObject("select max(id) from user_info", Int::class.java)
            }
        } catch (e: Exception) {
            println(e)
            throw e
        }

    }

    fun getUserIdWithTel(findInfoDTO: FindInfoDTO): String? {
        val sql = "select user_id from user_info where user_tel = '${findInfoDTO.userTel}'"
        return try{
            db.queryForObject(sql,String::class.java)
        }catch (e : Exception){
            println(e)
            throw e
        }
    }

}
