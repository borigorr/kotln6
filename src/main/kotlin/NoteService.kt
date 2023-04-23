import NoteService.notes as notes

object NoteService {

    private var notes = mutableMapOf<Int, Note>()
    private var comments = mutableMapOf<Int, Comment>()

    public fun add(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String
    ): Int {
        val newId = notes.size + 1
        val newComment = Note(
            id = newId,
            title = title,
            text = text,
            privacy = privacy,
            commentPrivacy = commentPrivacy,
            privacyView = privacyView,
            privacyComment = privacyComment,
        )
        notes.put(newId, newComment)
        return newId
    }

    public fun createComment(noteId: Int, replyTo: Int, message: String): Int {
        if (!notes.containsKey(noteId)) {
            throw NoteNotFount("Note with id $noteId not found")
        }
        val commentId = comments.size + 1
        val comment = Comment(
            noteId = noteId,
            replTo = replyTo,
            message = message,
        )
        comments.put(commentId, comment)
        return commentId
    }

    public fun delete(noteId: Int): Boolean {
        val note =  notes.get(noteId) ?: throw NoteNotFount("Note with id $noteId not found")
        notes.put(noteId, note.copy(isDelete = true))
        return true
    }

    public fun deleteComment(commentId: Int): Boolean {
        val comment = comments.get(commentId) ?: throw CommentNotFount("Comment with id $commentId not found")
        comments.put(commentId, comment.copy(isDelete = true))
        return true
    }

    public fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: String,
        privacyComment: String
    ): Boolean {
        val note = notes.get(noteId) ?: throw NoteNotFount("Note with id $noteId not found")
        if (note.isDelete) {
            throw NoteDeleted("Note wit id $noteId deleted")
        }
        val newNote = note.copy(
            title = title,
            text = text,
            privacy = privacy,
            commentPrivacy = commentPrivacy,
            privacyView = privacyView,
            privacyComment = privacyComment
        )
        notes.put(noteId, newNote)
        return true
    }

    public fun editComment(commentId: Int, message: String): Boolean {
        val comment = comments.get(commentId) ?: throw CommentNotFount("Comment with id $commentId not found")
        if (comment.isDelete) {
            throw CommentDeleted("Comment wit id $commentId deleted")
        }
        val newComment = comment.copy(message = message)
        comments.put(commentId, newComment)
        return true
    }

    public fun get(noteIds: Set<Int>, offset: UInt, count: UInt, sort: Int): List<Note> {
        var result = mutableListOf<Note>()
        if (count == 0u) {
            return result
        }
        var notesFind = 0u
        val sortedNotes: List<Note>
        if (sort == 0) {
            sortedNotes = notes.toList().sortedBy { (_, value) -> value.id }
        } else {
            sortedNotes = notes.toList().sortedBy { (_, value) -> value.id }
        }
        for (note in sortedNotes) {
            if (noteIds.contains(note.id)) {
                notesFind++
                if (offset < notesFind) {
                    result.add(note)
                }
            }
        }
        return result
    }
}