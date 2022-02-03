package keeper.project.et.dao

import keeper.project.et.dto.request.post.comment.UploadModifyCommentDTO
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class CommentDAO : SuperDAO() {
    fun uploadCommentInfo(uploadModifyCommentDTO: UploadModifyCommentDTO): Any {
        val values =
            "${uploadModifyCommentDTO.postNum}, '${uploadModifyCommentDTO.comName}', '${uploadModifyCommentDTO.comMenu.joinToString()}', '${uploadModifyCommentDTO.comOrderCost.joinToString()}', '${uploadModifyCommentDTO.userID}'"

        val sql =
            "insert into post_comment (post_num, com_name, com_menu, com_order_cost, user_id) values ($values)"
        return try {
            val result = db.update(sql)
            result
        } catch (e: Exception) {
            throw(e)
        }
    }

    fun modifyCommentInfo(uploadModifyCommentDTO: UploadModifyCommentDTO): Any {
        val sql =
            "update post_comment set com_menu = '${uploadModifyCommentDTO.comMenu.joinToString()}', com_order_cost = '${uploadModifyCommentDTO.comOrderCost.joinToString()}' where com_num = ${uploadModifyCommentDTO.comNum} and post_num = ${uploadModifyCommentDTO.postNum}"
        return try {
            val result = db.update(sql)
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun deleteCommentInfo(com: Int, post: Int): Any {
        val sql =
            "delete from post_comment where post_num = $post and com_num = $com"
        return try {
            val result = db.update(sql)
            result
        } catch (e: DataAccessException) {
            throw (e)
        }
    }


    fun cancelCommentInfo(post: Int): Any {
        val mapper = RowMapper<UploadModifyCommentDTO> { rs, _ ->
            val cm = rs.getString("com_menu").split(", ")
            val coc = rs.getString("com_order_cost").split(", ").map { it.toInt() }
            UploadModifyCommentDTO(
                rs.getInt("com_num"),
                rs.getInt("post_num"),
                rs.getString("com_name"),
                cm,
                coc,
                rs.getTimestamp("create_date"),
                rs.getString("user_id")
            )
        }

        return try {
            val result = db.query("select * from post_comment where post_num = $post",mapper)

            result

        } catch (e: DataAccessException) {
            throw (e)
        }
    }
}
