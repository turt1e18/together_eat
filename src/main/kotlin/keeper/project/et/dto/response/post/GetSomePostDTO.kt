package keeper.project.et.dto.response.post

import java.sql.Timestamp

data class GetSomePostDTO (
    val postNum: Int,
    val userID: String,
    val nameStore: String,
    val postTitle: String,
    val postState: Int,
    val postUpTime: Timestamp,
    val postCategory: Int,
    val postContent: String,
    val costOrderMin: Int,
    val costOrderRemain: Int,
    val postURL: String,
    val userName: String
        )