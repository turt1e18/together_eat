package keeper.project.et.dto

data class MailDTO(
    val to: String,
    val subject: String = "i-Keeper Email Check System",
    var text: String? = null
)
