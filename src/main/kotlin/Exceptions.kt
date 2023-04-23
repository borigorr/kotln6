class NoteNotFount(override val message: String?): RuntimeException(message)

class NoteDeleted(override val message: String?): RuntimeException(message)

class CommentNotFount(override val message: String?): RuntimeException(message)

class CommentDeleted(override val message: String?): RuntimeException(message)