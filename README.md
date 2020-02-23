# HashCode2020

![Google HashCode 2020](hashcode2020.jpg "Google HashCode 2020")

My Online Qualification Round Submission for Google HashCode 2020

## Files, Folders & Description
 - `datasets/` - Contains test cases
 - `output_*.txt` - Generated Output Files
 - `HashCode2020_ProblemStatement.pdf` - Problem Statement of HashCode 2020
 - `Main.java` - The main class file
   - `Library` - Library Class containing all details of Library
   - `Scanning Facility` - Blue Print of how Scanning Facility is designed
     - `addLibrary(Library, current_day)` - Function for signing up library to the scanning facility
     - `scanStrategy(allLibraries)` - Contains the scanning strategy for the scanning facility
     - `makeSubmission()` - Contains the code to write to `output_*.txt` files
     - `signedUpLibraries` - Variable containing the list of signedUpLibraries
     - `booksScanned` - Contains the books scanned by libraries till now
     - `lib2BookIDs` - Contains a mapping of books scanned against library
   - `bookScores` - Global Variable contains mapping of scores against books
   - `books, libraries, maxDays` - Distinct Books, Libraries and Total Days given in input
   - `bookScoreComparator` - Comparator that sorts the books by scores currently in booksScores
   - `signUpLibraryComparator` - Comparator to sort and identify which library to sign up

## Standing
   - Online Round Standing - 9295th Rank 
   - Extended Round Standing - 2834th Rank

## Todos
- [x] Fill README.md
- [x] Comment the code