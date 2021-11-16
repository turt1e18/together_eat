package keeper.project.et.dto.request.auth

data class FindInfoDTO(
    val userId: String? = null,
    val userEmail : String,
    val userTel : String
)
