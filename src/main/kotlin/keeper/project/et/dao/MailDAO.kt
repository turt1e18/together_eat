package keeper.project.et.dao

import org.springframework.stereotype.Repository

@Repository
class MailDAO : SuperDAO() {

    fun loggingOTPCode(mail: String, otp: String) {
        val sql = "insert into otp_log (user_email, otp) values ('$mail','$otp') "
        db.execute(sql)
    }

    fun getLastOTPCode(mail: String): String {
        val sql =
            "select otp from otp_log where user_email = '$mail' order by send_time desc limit 1"
        return db.queryForObject(sql, String::class.java)!!

    }

}
