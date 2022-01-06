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
    val postUptime: Timestamp,
    // val comNum: Int,
    // val comName: String,
    // val comTime: String,
    // val comMenu: String,
    // val comOrderCost: Int,
    // val userName: String
        )