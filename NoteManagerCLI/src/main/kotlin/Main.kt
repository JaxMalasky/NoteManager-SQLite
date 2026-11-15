import notemanager.library.Note
import notemanager.library.*
import java.security.Principal

fun main() {
    var choice = 0;
    val noteManager = NoteManager()
    println("Welcome to Note Manager")

    while(true) {
        choice = printMenu()
        manageChoice(choice, noteManager)
        if(choice == 6){
            println("Goodbye!")
            break
        }
    }


}



//prints menu options and returns users choice
fun printMenu(): Int {
    var choice: Int = 0;
    println("Choose an option:")
    println("1. Create a new note")
    println("2. View/Edit Notes")
    println("3. Delete a note")
    println("4. Delete all notes")
    println("5. Print Summary")
    println("6. Exit")
    choice = readln().trim().toInt();
    return choice
}

//passes user choice and note manager, user can initiate the creation, modification, and deletion of notes
fun manageChoice(choice: Int, noteManager: NoteManager ) {

    when (choice) {
        1 -> {
            println("Enter note type")
            println("1. General Note")
            println("2. Mental Note")
            println("3. Reminder Note")
            val choice : Int = readln().trim().toInt()
           // nested when statements for different note creation
            when(choice) {

                1 ->{
                    println("Enter note title:")
                    val title = readln()
                    println("Enter note:")
                    val note = readln()
                    noteManager.createGeneralNote(title, note)
                    println(".Note created!")
                }
                2-> {
                    println("Enter note title:")
                    val title = readln()
                    println("Enter note:")
                    val note = readln()
                    println("Enter emotion:")
                    val emotion = readln()
                    noteManager.createMentalNote(title, note, emotion)
                    println(" Mental Note created!")
                }
                3-> {
                    println("Enter note title:")
                    val title = readln()
                    println("Enter note:")
                    val note = readln()
                    println("Enter dead line:")
                    val deadline = readln()
                    noteManager.createReminderNote(title, note, deadline)
                    println(" reminder Note created!")
                }

            }

        }

        2 -> {
            //check if notes list is empty
            if (noteManager.getNotes().isEmpty()) {
                println("No notes found")
                return
            }


            //print out note list
                printNotes(noteManager)

            //Edit notes option given
                println("would you like to edit notes y/n")
                val choice = readln()

                if (choice == "y" || choice == "yes") {

                    println("enter note number to edit")

                    val noteNumber = readln().trim().toInt()
                    val note = noteManager.getNoteById(noteNumber)
                    when(note){
                        is MentalNote ->{
                            editMentalNote(noteNumber ,noteManager)
                        }
                        is ReminderNote ->{
                            editReminderNote(noteNumber ,noteManager)

                        }
                        is GeneralNote ->{
                            editNote(noteNumber, noteManager)
                        }
                    }




                } else {
                    return
                }


        }

        3 -> {
            //check if notes list is empty
            if (noteManager.getNotes().isEmpty()) {
                println("No notes found")
                return
            }
            println("Enter note number to be deleted:")
            try{
                printNotes(noteManager)
                val toDelete = readln().trim().toInt()
                noteManager.deleteNote(toDelete)
            } catch (e: NumberFormatException){
                println("Enter note number to be deleted:")

            }

        }

        4->{
            if (noteManager.getNotes().isEmpty()) {
                println("No notes found")
                return
            }
            println("Type DELETE/delete to delete all notes")
            val choice = readln()
                if(choice.equals("delete", ignoreCase = true)){
                    noteManager.clearAllNotes()
                }else{
                    println("Notes not deleted returning to menu")
                    return
                }
            }

        5-> {
            printSummary(noteManager)
            readln()
        }


    }

}
//  prints notes by ID and description
fun printNotes(noteManager: NoteManager) {

    for (note in noteManager.getNotes()) {
        when(note){

            is MentalNote -> {
                println("--------[" + note.id + "]----------")
                println("Title: " + note.title + " | Emotion: " + note.emotion)
                println("Note :" + note.noteBody)
                println("-------------------------------")
            }
            is ReminderNote -> {
                println("--------[" + note.id + "]----------")
                println("Title: " + note.title + " | Dead Line: " + note.deadLine)
                println("Note :" + note.noteBody)
                println("-------------------------------")
            }
            is GeneralNote -> {
                println("--------[" + note.id + "]----------")
                println("Title: " + note.title)
                println("Note :" + note.noteBody)
                println("-------------------------------")
            }

        }

    }


}

// prints summary of notes with calculated percentages of how much each note type is present in the Database
fun printSummary(noteManager: NoteManager) {
    val noteCount = noteManager.getNoteCount()
    if (noteCount == 0) {
        println("No notes found")
        return
    }
    val mentalNoteCount = noteManager.getMentalNoteCount()
    val reminderCount = noteManager.getReminderNoteCount()
    val generalNoteCount = noteManager.getGeneralNoteCount()

    var mentalPercentage = 0.0
    var reminderPercentage = 0.0
    var generalPercentage = 0.0


    if(mentalNoteCount != 0) {
         mentalPercentage = (noteManager.getMentalNoteCount().toDouble()/noteManager.getNoteCount()) * 100
    }
    if(reminderCount != 0) {
        reminderPercentage = (noteManager.getReminderNoteCount().toDouble()/noteManager.getNoteCount()) * 100
    }
    if(generalNoteCount != 0) {
        generalPercentage = (noteManager.getGeneralNoteCount().toDouble()/noteManager.getNoteCount()) * 100
    }

    println("--------[SUMMARY]----------")
    println("Total Note Count: " + noteManager.getNoteCount())
    println("Total Mental Note Count: " + noteManager.getMentalNoteCount() + "|" + "%.2f".format(mentalPercentage) + "%")
    println("Total Reminder Note Count: " + noteManager.getReminderNoteCount()+ "|" + "%.2f".format(reminderPercentage) + "%")
    println("Total General Note Count: " + noteManager.getGeneralNoteCount()+ "|" + "%.2f".format(generalPercentage) + "%")


}


///CLI EDIT FUNCTION coordinates with Notemanager to edit different type of notes.
fun editNote(noteNumber: Int, noteManager: NoteManager ) {
    println("New note title:")
    val title = readln()
    println("New note :")
    val note = readln()
    noteManager.editNote(noteNumber , title, note)
}

fun editMentalNote(noteId: Int, noteManager: NoteManager) {
    println("New note title:")
    val title = readln()
    println("New note body:")
    val note = readln()
    println("New Emotion:")
    val emotion = readln()
    noteManager.editMentalNote(noteId, title, note, emotion)

}

fun editReminderNote(noteId: Int, noteManager: NoteManager) {
    println("New note title:")
    val title = readln()
    println("New note body:")
    val note = readln()
    println("New Dead Line:")
    val deadLine = readln()
    noteManager.editReminderNote(noteId, title, note, deadLine)

}
