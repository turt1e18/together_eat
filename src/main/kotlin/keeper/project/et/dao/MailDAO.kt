package keeper.project.et.dao

import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Repository

@Repository
class MailDAO : SuperDAO() {

    fun getUserEmail(email: String): String? {
        return try {
            val sql = "select user_email from user_info where user_email = '${email}';"
            db.queryForObject(sql, String::class.java)
        } catch (e: DataAccessException) {
            "noting"
        }
    }
}

/*
1. 이메일 체크
    1. true -> 2
    2. false -> 없는 이메일 = false
2-1 이메일 받고 랜덤 상수 6 자리 생성 후 전송
2-2 이메일 + otp 상수 데이터 베이스에 저장
2-3 인증확인을 받으면 db에서 가장 최근 조회
    1. true
    2. false
*/
