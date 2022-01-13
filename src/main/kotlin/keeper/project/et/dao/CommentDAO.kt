package keeper.project.et.dao

import keeper.project.et.dto.request.post.comment.UploadModifyCommentDTO
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class CommentDAO : SuperDAO() {
    fun uploadCommentInfo(uploadModifyCommentDTO: UploadModifyCommentDTO): Any {
        val values =
            "${uploadModifyCommentDTO.comNum}, ${uploadModifyCommentDTO.postNum}, '${uploadModifyCommentDTO.comName}', '${uploadModifyCommentDTO.comMenu}', ${uploadModifyCommentDTO.comOrderCost}, '${uploadModifyCommentDTO.comContent}'"
        val sql =
            "insert into post_comment (com_num, post_num, com_name, com_menu, com_order_cost, com_content) values ($values)"
        return try {
            val result = db.update(sql)
            result
        } catch (e: Exception) {
            throw (e)
        }
    }

    fun modifyCommentInfo(uploadModifyCommentDTO: UploadModifyCommentDTO): Any {
        val sql =
            "update post_comment set com_menu = '${uploadModifyCommentDTO.comMenu}', com_order_cost = ${uploadModifyCommentDTO.comOrderCost}, com_content = '${uploadModifyCommentDTO.comContent}' where com_num = ${uploadModifyCommentDTO.comNum} and post_num = ${uploadModifyCommentDTO.postNum}"
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
            UploadModifyCommentDTO(
                rs.getInt("com_num"),
                rs.getInt("post_num"),
                rs.getString("com_name"),
                rs.getString("com_menu"),
                rs.getInt("com_order_cost"),
                rs.getString("com_content"),
                rs.getTimestamp("create_date"),
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