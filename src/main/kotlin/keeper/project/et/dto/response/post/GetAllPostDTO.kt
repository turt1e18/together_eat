package keeper.project.et.dto.response.post

import java.sql.Timestamp

data class GetAllPostDTO (
    val postNum: Int,
    val postCategory: Int,
    val nameStore: String,
    val postTitle: String,
    val postState: Int,
    val postUpTime: Timestamp
)