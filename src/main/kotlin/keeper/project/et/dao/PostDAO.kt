package keeper.project.et.dao

import keeper.project.et.dto.response.post.GetAllPostDTO
import keeper.project.et.dto.request.post.UploadModifyPostDTO
import keeper.project.et.dto.request.post.comment.UploadModifyCommentDTO
import keeper.project.et.dto.response.post.GetPostCategoryDTO
import keeper.project.et.dto.response.post.GetSomePostDTO
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Repository
import java.lang.Exception
import org.springframework.jdbc.core.RowMapper

@Repository
class PostDAO : SuperDAO() {
    fun uploadPostInfo(uploadModifyPostDTO: UploadModifyPostDTO): Any {
        val values =
            "'${uploadModifyPostDTO.userID}', '${uploadModifyPostDTO.nameStore}', ${uploadModifyPostDTO.postCategory}, '${uploadModifyPostDTO.postTitle}', '${uploadModifyPostDTO.postContent}', '${uploadModifyPostDTO.postURL}', ${uploadModifyPostDTO.costOrderMin}, ${uploadModifyPostDTO.costOrderRemain}, ${1}, '${uploadModifyPostDTO.userName}'"

        val sql =
            "insert into posts (user_id, name_store, post_category, post_title, post_content, post_url, cost_order_min, cost_order_remain, post_state, user_name) values ($values)"

        return try {
            val result = db.update(sql)
            result

        } catch (e: Exception) {
            e
        }
    }

    fun deletePostInfo(postNum: Int): Int {
        return try {
            val sql = "delete from posts where post_num = ${postNum};"
            val result = db.update(sql)
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun modifyPostInfo(uploadModifyPostDTO: UploadModifyPostDTO): Int {
        return try {
            val sql =
                "update posts set name_store='${uploadModifyPostDTO.nameStore}', post_category=${uploadModifyPostDTO.postCategory}, post_title='${uploadModifyPostDTO.postTitle}', post_content='${uploadModifyPostDTO.postContent}', post_url='${uploadModifyPostDTO.postURL}', cost_order_min=${uploadModifyPostDTO.costOrderMin}, cost_order_remain=${uploadModifyPostDTO.costOrderRemain} where post_num=${uploadModifyPostDTO.postNum}"
            val result = db.update(sql)
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun getAllPost(): Any {
        val postMapper = RowMapper<Any> { rs, _ ->
            GetAllPostDTO(
                rs.getInt("post_num"),
                rs.getInt("post_category"),
                rs.getString("name_store"),
                rs.getString("post_title"),
                rs.getInt("post_state"),
                rs.getTimestamp("create_date"),
            )
        }

        val categoryMapper = RowMapper<GetPostCategoryDTO> { rs, _ ->
            GetPostCategoryDTO(
                rs.getInt("post_category"),
                rs.getString("category_name")
            )
        }

        return try {
            val poList = db.query("select * from posts", postMapper)
            val cate = db.query("select * from category", categoryMapper)
            val postHash = HashMap<String, Any>()
            postHash["category"] = cate
            postHash["postList"] = poList
            postHash

        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun getSomePost(postNum: Int): Any {
        val postMapper = RowMapper<GetSomePostDTO> { rs, _ ->
            GetSomePostDTO(
                rs.getInt("post_num"),
                rs.getString("user_id"),
                rs.getString("name_store"),
                rs.getString("post_title"),
                rs.getInt("post_state"),
                rs.getTimestamp("create_date"),
                rs.getInt("post_category"),
                rs.getString("post_content"),
                rs.getInt("cost_order_min"),
                rs.getInt("cost_order_remain"),
                rs.getString("post_url"),
                rs.getString("user_name")
            )
        }

        val commentMapper = RowMapper<UploadModifyCommentDTO> { rs, _ ->
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
            val post = db.query("select * from posts where post_num=${postNum}", postMapper)
            val com = db.query("select * from post_comment where post_num=${postNum}", commentMapper)
            val postComHash = HashMap<String, Any>()
            postComHash["content"] = post
            postComHash["comment"] = com
            postComHash

        } catch (e: DataAccessException) {
            throw(e)
        }
    }

}