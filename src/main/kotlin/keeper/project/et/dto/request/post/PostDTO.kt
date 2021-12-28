package keeper.project.et.dto.request.post

data class PostDTO (
    val userId: String,
    val postNum : Int,
    val nameStore : String,
    val postCategory : Int,
    val postTitle : String,
    val postContent : String,
    val postUrl : String,
    val costOrderMin : Int,
    val costOrderRemain: Int
)