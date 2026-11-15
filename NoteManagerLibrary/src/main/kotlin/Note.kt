package notemanager.library
import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import java.time.Instant

// Id is a nullable object, so i can create a note with out first
abstract class Note (
    open var id: Int? = null,
   open var title: String,
   open var noteBody: String,
   open var createDate: String = LocalDateTime.now().toString(),

    val time: Long = System.currentTimeMillis()
){
    abstract val noteType: String

}









