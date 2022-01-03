package keeper.project.et.dto.request.auth

data class FindInfoDTO(
    val userID: String? = null,
    val userEmail : String,
    val userPhone : String
)
