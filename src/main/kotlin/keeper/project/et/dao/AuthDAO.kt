package keeper.project.et.dao

import keeper.project.et.dto.request.auth.AccessLoginDTO
import keeper.project.et.dto.request.auth.FindInfoDTO
import keeper.project.et.dto.request.auth.SignUpDTO
import keeper.project.et.dto.db.AccessUserInfoDTO
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class AuthDAO : SuperDAO() {

    fun getAccessInfo(accessLoginDTO: AccessLoginDTO): AccessUserInfoDTO? {

        val mapper = RowMapper<AccessUserInfoDTO> { rs, _ ->
            AccessUserInfoDTO(
                rs.getInt("id"),
                rs.getString("user_id"),
                rs.getString("user_pw"),
                rs.getString("user_name")
            )
        }

        val sql =
            "select id, user_id, user_pw, user_name from user_info where user_id = '${accessLoginDTO.userID}'"

        return try {
            db.queryForObject(sql, mapper)
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

    fun setUserInfo(signUpDTO: SignUpDTO): Int? {

        val values =
            "'${signUpDTO.userID}', '${signUpDTO.userPW}', '${signUpDTO.userEmail}', '${signUpDTO.userName}', '${signUpDTO.userTel}'"
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

    fun checkUserID(userId :String): Boolean {
        return try{
            val sql = "select user_id from user_info where user_id = '$userId';"
            val result = db.queryForObject(sql,String::class.java)!!
            userId == result
        }catch (e : DataAccessException){
            return false
        }
    }

    fun checkUserName(userName:String): Boolean {
        return try{
            val sql = "select user_name from user_info where user_name = '$userName';"
            val result = db.queryForObject(sql,String::class.java)!!
            userName == result
        }catch (e : DataAccessException){
            return false
        }
    }

    fun checkUserEmail(userEmail:String): Boolean {
        return try{
            val sql = "select user_email from user_info where user_email = '$userEmail';"
            val result = db.queryForObject(sql,String::class.java)!!
            userEmail == result
        }catch (e : DataAccessException){
            return false
        }
    }

    fun getUserIdWithEmail(email: String): String {
        val sql = "select user_id from user_info where user_email = '$email'"
        return try{
            db.queryForObject(sql,String::class.java)!!
        }catch (e : DataAccessException){
            throw e
        }
    }

    fun resetUserPW(id : String, pw:String){
        try {
            val sql = "update user_info set user_pw = '$pw' where user_id = '$id'"
            db.update(sql)
        }catch (e : DataAccessException){
            throw e
        }
    }

}
