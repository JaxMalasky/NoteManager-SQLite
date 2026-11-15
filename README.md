# Overview


The software is built off my previous NoteLibrary which can be found under my repositories (called NoteMan). The goal for this project was to replace the JSON persistent storage
with a SQLite database. 

The software allows the user to create modify and remove notes that are represented in code as instnaces of the Note object. The Note Object is an abstract class with 3 concrete clasess, general note
reminder note, and mental note. The user can create these different types of notes through the CLI which are store in the database. The application layer(NoteManager.Kt) of this program handles the passing of information between the CLI and the
Data access layer. If a user chooses to create a note, the input is passed to NoteManger to create an instance of a note object which then can be passed to the Data access layer(NoteRepository) where
the note object's data is broken down and placed into a parameterized SQL Query which will store the data in notes.db database. Retreival is the opposite where the note is formed and returned by repository functions SelectNotes/Notes
that the NoteManager can call.  

this project I wanted to further my learning as a software engineer by practicing common software layer architecture (Presentation, Application, Business, Database) and the use of
relational databases as persistent storage.

How to use:
- The program uses NoteManagerLibrary-1.0-SNAPSHOT.jar as a Gradle dependency. This means the library could potentially be reused to
develop different types of interfaces, although this has not been tested.
- For this project everything was ran inside the Intellij terminal but provided Java installed it should be able to run in CMD, PowerShell or other CLI's with a little bit of work




Walkthrough and Demonstration Video
[https://www.youtube.com/watch?v=9HAnBbdG1Zk)

# Relational Database

SQLite - This database method was chosen because it conveniently operates out of an embedded database file in the project folder making
it a good choice to have the program be self-contained

Database Structure {Describe the structure (tables) of the relational database that you created.}
- Database - notes.db
- Tables 
  - Notes - noteID, title, body, timeCreated, noteType
  - GeneralNotes - noteId
  - ReminderNotes - noteId, deadline
  - MentalNotes - noteId, emotion 

# Development Environment

TOOLS USED
- Intellij IDEA 
  - Gradle
- SQLite

LANGUAGE AND LIBRARIES
- Kotlin, programming language
- JDBC / Java Database Connectivity Library
- Kotlin Seralization library

# Useful Websites

{Make a list of websites that you found helpful in this project}

- [SQLite Tutorial](https://www.sqlitetutorial.net/)
- [Kotlin Docs](https://kotlinlang.org/docs/home.html)
- [What Are the 5 Primary Layers in Software Architecture?](https://www.indeed.com/career-advice/career-development/what-are-the-layers-in-software-architecture)

# Future Work
- There is a lot of duplicated code because the way I handle the notes, this would not be good for scaleablility
so if I wanted to practice that I would look for a way to stream line it.
- The displayed notes use database noteID key in the CLI but when I delete a note and create a new one it will take the number after it, EX 2-deleted, 3 would be created.
The notes would be better displayed with consistant numbers 
- There is not much error handling so its can break easily
- The user interface could use some smoothing out, some menu selections have a wait period which kind of throws me off when others do not. Ex summary option. 
- The CLI and Controller are combined, it may be a good idea to seperate the concerns of those functions but at this point with the simplicity of the user interface its not too bad. 