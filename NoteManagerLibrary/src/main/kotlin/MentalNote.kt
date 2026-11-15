package notemanager.library

import java.time.LocalDateTime

data class MentalNote (
    var emotion: String,
    override var title: String,
    override var noteBody: String,
    override var createDate: String = LocalDateTime.now().toString(),
    override var id: Int? = null

) : Note( title = title , noteBody = noteBody, id = id){
    override val noteType: String = "mental_note"

}