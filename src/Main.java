
import java.io.*;
import java.util.*;

// Main Class For The Solution
public class Main {

//  Global Variable for total distinct books, libraries and total days
    public static int books, libraries, maxDays;

    public static String currentFile = "";

//  Mapping of bookIDs against their scores
    public static ArrayList<Integer> bookScores;

//  Scanning Facility Class
    public static class ScanningFacility{

        private int remDays = maxDays;

//      Variable containing the list of signedUpLibraries
        private HashSet<Library> signedUpLibraries = new HashSet<Library>();
//      Contains the books scanned by libraries till now
        private HashSet<Integer> booksScanned = new HashSet<Integer>();
//      Contains a mapping of books scanned against library
        private HashMap<Library, List<Integer>> lib2BookIDs = new HashMap<Library, List<Integer>>();


        ScanningFacility(){

        }

//      Function for signing up library to the scanning facility
        private void addLibrary(Library l, int d){
            l.signedUpAt = d + l.signUpProcess;
            signedUpLibraries.add(l);
        }

//      Contains the scanning strategy for the scanning facility
        private void scanStrategy(ArrayList<Library> allLibraries){

            while(allLibraries.size() != 0){

                Collections.sort(allLibraries, signUpLibraryComparator);

                Library curr = allLibraries.get(0);
                signedUpLibraries.add(curr);
                remDays = remDays - curr.signUpProcess;
                if(remDays*curr.booksPerDay < 0){
                    break;
                }

                HashSet<Integer> tempBooks = curr.bookIDs;
                tempBooks.removeAll(booksScanned);
                List<Integer> temp = new LinkedList<Integer>(tempBooks);
                Collections.sort(temp, bookScoreComparator);
                int tempBookCounts = Math.min(temp.size(), Math.min(curr.totalBooks, remDays*curr.booksPerDay));

                if(tempBookCounts <= 0){
                    continue;
                }
                List<Integer> tempShippedBooks = temp.subList(0, tempBookCounts);
                booksScanned.addAll(tempShippedBooks);
                lib2BookIDs.put(curr, tempShippedBooks);

                allLibraries.remove(0);

            }


        }

//      Contains the code to write to `output_*.txt` files
        private void makeSubmission(){

            try{

                FileWriter writer = new FileWriter("output_"+currentFile);
                BufferedWriter buffer = new BufferedWriter(writer);

                buffer.write(String.valueOf(lib2BookIDs.size()));
                buffer.newLine();

                for(Map.Entry<Library, List<Integer>> m: lib2BookIDs.entrySet()){
                    Library temp = m.getKey();
                    List<Integer> tempBooks = m.getValue();
                    buffer.write(temp.id+" "+tempBooks.size());
                    buffer.newLine();
                    String tempStr = "";
                    for(Integer i: tempBooks){
                        tempStr = tempStr + i + " ";
                    }
                    buffer.write(tempStr);
                    buffer.newLine();
                }

                buffer.close();
            }catch(IOException e){
                System.out.println("IOException");
            }


        }

    }



//    Library Class
    public static class Library {

        private HashSet<Integer> bookIDs;
        private int booksPerDay, signUpProcess, totalBooks;
        private int signedUpAt = -1;
        private int id;

        Library(int id, HashSet<Integer> bookIDs, int totalBooks, int booksPerDay, int signUpProcess){
            this.id = id;
            this.totalBooks = totalBooks;
            this.bookIDs = bookIDs;
            this.booksPerDay = booksPerDay;
            this.signUpProcess = signUpProcess;
        }

    }


    public static final Comparator<Integer> bookScoreComparator = new Comparator<Integer>(){
        public int compare(Integer a, Integer b){
            return bookScores.get(b) - bookScores.get(a);
        }
    };

    public static final Comparator<Library> signUpLibraryComparator = new Comparator<Library>(){

        public double priorityScore(Library l){

            double priorityScore = 0.0;

            double avgScore = 1.0;
//
//            for(Integer i: l.bookIDs){
//                avgScore += bookScores.get(i);
//            }
//
//            avgScore = avgScore/l.totalBooks;

            priorityScore = Math.min(l.totalBooks, (maxDays - l.signUpProcess)*l.booksPerDay)*avgScore;

            return priorityScore;
        }

        public int compare(Library l1, Library l2){
            return Double.compare(priorityScore(l2), priorityScore(l1));
        }

    };

    public static void readAndExecute(String filePath){

        try{
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            books = sc.nextInt();
            libraries = sc.nextInt();
            maxDays = sc.nextInt();
            bookScores = new ArrayList<Integer>();

            for(int i = 0; i < books; i++){
                bookScores.add(sc.nextInt());
            }

            ArrayList<Library> allLibraries = new ArrayList<Library>();

            for(int i = 0; i < libraries; i++){
                int tBooks = sc.nextInt();
                int tsignUpProcess = sc.nextInt();
                int tBooksPerDay = sc.nextInt();
                HashSet<Integer> tBookIDs = new HashSet<Integer>();
                for(int j = 0; j < tBooks; j++){
                    tBookIDs.add(sc.nextInt());
                }

                Library temp = new Library(i, tBookIDs, tBooks, tBooksPerDay, tsignUpProcess);

                allLibraries.add(temp);
            }

            sc.close();

//            System.out.println(allLibraries.size());

            ScanningFacility scF = new ScanningFacility();

            scF.scanStrategy(allLibraries);
            scF.makeSubmission();

        }catch (FileNotFoundException e){
            System.out.println("File Not Found "+filePath);
            System.exit(123);
        }


    }

    public static void main(String[] args) {

        ArrayList<String> filePath = new ArrayList<String>(
                                            Arrays.asList("a_example.txt",
                                                            "b_read_on.txt",
                                                            "c_incunabula.txt",
                                                            "d_tough_choices.txt",
                                                            "e_so_many_books.txt",
                                                            "f_libraries_of_the_world.txt")
                                        );


        for(String str : filePath){
            currentFile = str;
            readAndExecute("C:\\Users\\DELL\\Desktop\\HashCode2020\\src\\datasets\\"+str);
            System.out.println(currentFile + " executed");
        }

    }
}