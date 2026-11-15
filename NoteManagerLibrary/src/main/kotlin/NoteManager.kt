package notemanager.library
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.File


 class NoteManager {
    private val database = Database()
    private val repository = NoteRepository(database)


     //used to create note and hand it to the data access layer
    fun createGeneralNote(title: String, noteBody: String){
         val generalNote = GeneralNote(title, noteBody)
         repository.insertNote(generalNote) // insert note into the datbase

    }
     fun createReminderNote(title: String, noteBody: String, deadline: String){
         val reminderNote = ReminderNote(title = title, noteBody = noteBody, deadLine = deadline)
         repository.insertNote(reminderNote) // insert note into the datbase

     }

     fun createMentalNote(title: String, noteBody: String, emotion: String){
         val mentalNote = MentalNote(title = title, noteBody = noteBody, emotion = emotion)
         repository.insertNote(mentalNote)
     }




    //returns the note list
    fun getNotes(): List<Note>{

        return repository.selectNotes()
    }

    //expects an int to delete a note with
    fun deleteNote(i: Int){

        repository.deleteNote(i)

    }


     fun clearAllNotes(){
         repository.deleteAllNotes()

     }

    //Overloading edit functions.

     fun editNote(id: Int, title: String, body: String) {
         val note = repository.selectNote(id) ?:return  //evlis operator for potential null reception.
         note.title = title
         note.noteBody = body

         repository.updateNote(note)
     }

     fun editMentalNote(id: Int, title: String, body: String, emotion: String) {
         val note = repository.selectNote(id) ?:return  //evlis operator for potential null reception.
         when(note){
             is MentalNote ->{
                 note.title = title
                 note.noteBody = body
                 note.emotion = emotion
                 repository.updateNote(note)
             }
         }

     }

     fun editReminderNote(id: Int, title: String, body: String, deadline: String) {
         val note = repository.selectNote(id) ?:return  //evlis operator for potential null reception.
         when(note){
             is ReminderNote ->{
                 note.title = title
                 note.noteBody = body
                 note.deadLine = deadline
                 repository.updateNote(note)
             }
         }


     }


     //functions for summary

     fun getNoteById(id: Int): Note?{
         return repository.selectNote((id))
     }

     fun getNoteCount(): Int {
         return repository.getNoteCount()
     }
     fun getMentalNoteCount(): Int {
         return repository.getMentalNoteCount()
     }
    fun getReminderNoteCount(): Int {
        return repository.getReminderNoteCount()
    }
     fun getGeneralNoteCount(): Int {
         return repository.getGeneralNoteCount()
     }




}
