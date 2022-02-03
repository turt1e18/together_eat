package keeper.project.et.dto.request.post.comment

import java.sql.Timestamp

data class UploadModifyCommentDTO(
   val comNum: Int,
   val postNum: Int,
   val comName: String?,
   val comMenu: List<Any>,
   val comOrderCost: List<Any>,
   val comTime: Timestamp?,
   val userID: String
        )