package keeper.project.et.dto.request.post.comment

data class UploadModifyCommentDTO (
   val comNum: Int,
   val postNum: Int,
   val comName: String?,
   val comMenu: String,
   val comOrderCost: Int,
   val comContent: String,
        )