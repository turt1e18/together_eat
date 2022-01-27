package keeper.project.et.dto.request.post

import java.sql.Timestamp

data class GetAllPostDTO (
    val userID: String,
    val postNum: Int,
    val nameStore: String,
    val postCategory: Int,
    val postTitle: String,
    val postState: Int,
    val postUpTime: Timestamp
)