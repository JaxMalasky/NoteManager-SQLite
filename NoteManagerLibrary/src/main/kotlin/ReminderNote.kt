package notemanager.library

import java.time.LocalDateTime


class ReminderNote (

    override var title: String,
    override var noteBody: String,
    override var createDate: String = LocalDateTime.now().toString(),
    var deadLine: String,
    override var id: Int? = null
    ) : Note(title = title, noteBody = noteBody, id = id){
    override val noteType: String = "reminder_note"
}
