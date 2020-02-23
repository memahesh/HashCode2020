import java.io.*;
import java.util.*;
import hashcode.ScanningFacility;
import hashcode.Library;

// Main Class For The Solution
public class Main {

//  Global Variable for total distinct books, libraries and total days
    public static int books, libraries, maxDays;

    public static String currentFile = "";

//  Mapping of bookIDs against their scores
    public static ArrayList<Integer> bookScores;

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

            ScanningFacility scF = new ScanningFacility(maxDays, bookScores);

            scF.scanStrategy(allLibraries);
            scF.makeSubmission(currentFile);

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


        String cwd = System.getProperty("user.dir");

        for(String str : filePath){
            currentFile = str;
            readAndExecute(cwd+"\\src\\datasets\\"+str);
            System.out.println(currentFile + " executed");
        }

    }
}