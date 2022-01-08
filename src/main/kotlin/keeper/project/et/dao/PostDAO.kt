package keeper.project.et.dao

import keeper.project.et.dto.request.post.UploadModifyPostDTO
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
            "'${uploadModifyPostDTO.userID}', ${uploadModifyPostDTO.postNum}, '${uploadModifyPostDTO.nameStore}', ${uploadModifyPostDTO.postCategory}, '${uploadModifyPostDTO.postTitle}', '${uploadModifyPostDTO.postContent}', '${uploadModifyPostDTO.postURL}', ${uploadModifyPostDTO.costOrderMin}, ${uploadModifyPostDTO.costOrderRemain}, ${1}, '${uploadModifyPostDTO.userName}'"

        val sql =
            "insert into posts (user_id, post_num, name_store, post_category, post_title, post_content, post_url, cost_order_min, cost_order_remain, post_state, user_name) values ($values)"

        return try {
            val result = db.update(sql)!!
            result

        } catch (e: Exception) {
            e
        }
    }

    fun deletePostInfo(postNum: Int): Int {
        return try {
            val sql = "delete from posts where post_num = ${postNum};"
            val result = db.update(sql)!!
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun modifyPostInfo(uploadModifyPostDTO: UploadModifyPostDTO): Int {
        return try {
            val sql =
                "update posts set name_store='${uploadModifyPostDTO.nameStore}', post_category=${uploadModifyPostDTO.postCategory}, post_title='${uploadModifyPostDTO.postTitle}', post_content='${uploadModifyPostDTO.postContent}', post_url='${uploadModifyPostDTO.postURL}', cost_order_min=${uploadModifyPostDTO.costOrderMin}, cost_order_remain=${uploadModifyPostDTO.costOrderRemain} where post_num=${uploadModifyPostDTO.postNum}"
            val result = db.update(sql)!!
            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun getAllPost(): Any {
        val postMapper = RowMapper<UploadModifyPostDTO> { rs, _ ->
            UploadModifyPostDTO(
                rs.getString("user_id"),
                rs.getInt("post_num"),
                rs.getString("name_store"),
                rs.getInt("post_category"),
                rs.getString("post_title"),
                rs.getString("post_content"),
                rs.getString("post_url"),
                rs.getInt("cost_order_min"),
                rs.getInt("cost_order_remain"),
                rs.getInt("post_state"),
                rs.getString("user_name")
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
            postHash.put("category", cate)
            postHash.put("postList", poList)
            postHash

        } catch (e: DataAccessException) {
            throw(e)
        }
    }

    fun getSomePost(postNum: Int): Any {
        val mapper = RowMapper<GetSomePostDTO> { rs, _ ->
            GetSomePostDTO(
                rs.getInt("post_num"),
                rs.getString("name_store"),
                rs.getInt("post_category"),
                rs.getString("post_title"),
                rs.getString("post_content"),
                rs.getString("post_url"),
                rs.getInt("cost_order_min"),
                rs.getInt("cost_order_remain"),
                rs.getInt("post_state"),
                rs.getTimestamp("create_date"),
                rs.getString("user_name")
            )
        }
        return try {
            val result = db.query("select * from posts where post_num=${postNum}", mapper)

            result
        } catch (e: DataAccessException) {
            throw(e)
        }
    }

}