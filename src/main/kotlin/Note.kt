data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val privacyView: String,
    val privacyComment: String,
    val isDelete: Boolean = false,
)

data class Comment(
    val noteId: Int,
    val replTo: Int,
    val message: String,
    val isDelete: Boolean = false,
)