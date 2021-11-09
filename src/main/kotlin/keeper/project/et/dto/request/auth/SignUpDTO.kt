package keeper.project.et.dto.request.auth

data class SignUpDTO(
    val userId : String,
    val userPw : String,
    val userEmail : String,
    val userName : String,
    val userBirth : String,
    val userTel : String
)
