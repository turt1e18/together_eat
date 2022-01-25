package keeper.project.et.dto.response.post

import java.sql.Timestamp

data class GetSomePostDTO (
    val postNum: Int,
    val nameStore: String,
    val postCategory: Int,
    val postTitle: String,
    val postContent: String,
    val postURL: String,
    val costOrderMin: Int,
    val costOrderRemain: Int,
    val postState: Int,
    val postUpTime: Timestamp,
    val userName: String,
        )