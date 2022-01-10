package keeper.project.et.dto.request.post

data class UploadModifyPostDTO (
    val userID: String,
    val postNum: Int,
    val nameStore: String,
    val postCategory: Int,
    val postTitle: String,
    val postContent: String,
    val postURL: String,
    val costOrderMin: Int,
    val costOrderRemain: Int,
    val postState: Int,
    val userName: String
)