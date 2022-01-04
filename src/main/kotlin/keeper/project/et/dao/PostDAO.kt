package keeper.project.et.dao

import keeper.project.et.dto.request.post.UploadModifyPostDTO
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class PostDAO : SuperDAO() {
    fun uploadPostInfo(uploadModifyPostDTO: UploadModifyPostDTO): String {
        val values =
            "'${uploadModifyPostDTO.userID}', ${uploadModifyPostDTO.postNum}, '${uploadModifyPostDTO.nameStore}', ${uploadModifyPostDTO.postCategory}, '${uploadModifyPostDTO.postTitle}', '${uploadModifyPostDTO.postContent}', '${uploadModifyPostDTO.postURL}', ${uploadModifyPostDTO.costOrderMin}, ${uploadModifyPostDTO.costOrderRemain}"

        val sql =
            "insert into posts (user_id, post_num, name_store, post_category, post_title, post_content, post_url, cost_order_min, cost_order_remain) values ($values)"

        return try {
            db.let {
                it.execute(sql)
                it.queryForObject("select user_id from posts where user_id=?", String::class.java, uploadModifyPostDTO.userID)
            }
        } catch (e: Exception) {
            throw(e)
        }
    }

    fun deletePostInfo(postNum: Int): Int {
        return try {
            val sql = "delete from posts where post_num = ${postNum};"
            val result = db.update(sql)!!
            result

        } catch (e: DataAccessException) {
            0
        }
    }

    fun modifyPostInfo(uploadModifyPostDTO: UploadModifyPostDTO): Int {
        return try {
            val values =
                "'${uploadModifyPostDTO.userID}', ${uploadModifyPostDTO.postNum}, '${uploadModifyPostDTO.nameStore}', ${uploadModifyPostDTO.postCategory}, '${uploadModifyPostDTO.postTitle}', '${uploadModifyPostDTO.postContent}', '${uploadModifyPostDTO.postURL}', ${uploadModifyPostDTO.costOrderMin}, ${uploadModifyPostDTO.costOrderRemain}"
            val sql =
                "update posts set name_store='${uploadModifyPostDTO.nameStore}', post_category=${uploadModifyPostDTO.postCategory}, post_title='${uploadModifyPostDTO.postTitle}', post_content='${uploadModifyPostDTO.postContent}', post_url='${uploadModifyPostDTO.postURL}', cost_order_min=${uploadModifyPostDTO.costOrderMin}, cost_order_remain=${uploadModifyPostDTO.costOrderRemain} where post_num=${uploadModifyPostDTO.postNum}"
            val result = db.update(sql)!!
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }
}