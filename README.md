# HashCode2020

![Google HashCode 2020](hashcode2020.jpg "Google HashCode 2020")

My Online Qualification Round Submission for Google HashCode 2020

## Files, Folders & Description
 - `datasets/` - Contains test cases
 - `output_*.txt` - Generated Output Files
 - `HashCode2020_ProblemStatement.pdf` - Problem Statement of HashCode 2020
 - `Library.java` - Library Class containing all details of Library
    - Getters and Setters for Library data as required
 - `ScanningFacility.java` - Blue Print of how Scanning Facility is designed
  - `addLibrary(Library, current_day)` - Function for signing up library to the scanning facility
  - `scanStrategy(allLibraries)` - Contains the scanning strategy for the scanning facility
  - `makeSubmission()` - Contains the code to write to `output_*.txt` files
  - `bookScores` - Variable containing mapping of scores against books
  - `signedUpLibraries` - Variable containing the list of signedUpLibraries
  - `booksScanned` - Contains the books scanned by libraries till now
  - `lib2BookIDs` - Contains a mapping of books scanned against library
  - `bookScoreComparator` - Comparator that sorts the books by scores currently in booksScores
  - `signUpLibraryComparator` - Comparator to sort and identify which library to sign up
 - `Main.java` - The main class file
   - `bookScores` - Global Variable contains mapping of scores against books
   - `books, libraries, maxDays` - Distinct Books, Libraries and Total Days given in input
   - `readAndExecute(filePath)` - Reads from input file and write result to file
   
## Flow

 - __Step 1__ : Add input data to variables
 - __Step 2__ : Identify Library to signup at the Scanning Facility using `signUpLibraryComparator`
 - __Step 3__ : Identify which Books to scan in the Library selected in __Step 2__ using `bookScoreComparator`
 - __Step 4__ : Remove books that are already scanned from __Step 3__ using `booksScanned`
 - __Step 5__ : Add newly scanned books to `booksScanned` and removing this library from the set of libraries
 - __Step 6__ : Update the results of __Step 2__ and __Step 3__ to `lib2BookIDs`
 - __Step 7__ : Write Output to `output_*.txt`

## Standing
   - Online Round Standing - 9295th Rank 
   - Extended Round Standing - 2834th Rank

## Todos
- [x] Fill README.md
- [x] Comment the code