package hashcode;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//  Scanning Facility Class
public class ScanningFacility{

    private int remDays;
    private int maxDays;

    //      Variable containing the list of signedUpLibraries
    private HashSet<hashcode.Library> signedUpLibraries = new HashSet<hashcode.Library>();
    //      Contains the books scanned by libraries till now
    private HashSet<Integer> booksScanned = new HashSet<Integer>();
    //      Contains a mapping of books scanned against library
    private HashMap<hashcode.Library, List<Integer>> lib2BookIDs = new HashMap<hashcode.Library, List<Integer>>();
    //    Contains bookScores mapping
    private ArrayList<Integer> bookScores;


    public ScanningFacility(int maxDays, ArrayList<Integer> bookScores){
        this.remDays = maxDays;
        this.maxDays = maxDays;
        this.bookScores = bookScores;
    }

    //      Function for signing up library to the scanning facility
    private void addLibrary(hashcode.Library l, int d){
        l.setSignedUpAt(d + l.getSignUpProcess());
        signedUpLibraries.add(l);
    }

    //      Contains the scanning strategy for the scanning facility
    public void scanStrategy(ArrayList<hashcode.Library> allLibraries){

        while(allLibraries.size() != 0){

            Collections.sort(allLibraries, signUpLibraryComparator);

            hashcode.Library curr = allLibraries.get(0);
            signedUpLibraries.add(curr);
            remDays = remDays - curr.getSignUpProcess();
            if(remDays*curr.getBooksPerDay() < 0){
                break;
            }

            HashSet<Integer> tempBooks = curr.getBookIDs();
            tempBooks.removeAll(booksScanned);
            List<Integer> temp = new LinkedList<Integer>(tempBooks);
            Collections.sort(temp, bookScoreComparator);
            int tempBookCounts = Math.min(temp.size(), Math.min(curr.getTotalBooks(), remDays*curr.getBooksPerDay()));

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
    public void makeSubmission(String currentFile){

        try{

            FileWriter writer = new FileWriter("output_"+currentFile);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write(String.valueOf(lib2BookIDs.size()));
            buffer.newLine();

            for(Map.Entry<hashcode.Library, List<Integer>> m: lib2BookIDs.entrySet()){
                hashcode.Library temp = m.getKey();
                List<Integer> tempBooks = m.getValue();
                buffer.write(temp.getId()+" "+tempBooks.size());
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

    public final Comparator<Integer> bookScoreComparator = new Comparator<Integer>(){
        public int compare(Integer a, Integer b){
            return bookScores.get(b) - bookScores.get(a);
        }
    };

    public final Comparator<hashcode.Library> signUpLibraryComparator = new Comparator<hashcode.Library>(){

        public double priorityScore(hashcode.Library l){

            double priorityScore = 0.0;

            double avgScore = 1.0;
//
//            for(Integer i: l.bookIDs){
//                avgScore += bookScores.get(i);
//            }
//
//            avgScore = avgScore/l.totalBooks;

            priorityScore = Math.min(l.getTotalBooks(), (maxDays - l.getSignUpProcess())*l.getBooksPerDay())*avgScore;

            return priorityScore;
        }

        public int compare(hashcode.Library l1, hashcode.Library l2){
            return Double.compare(priorityScore(l2), priorityScore(l1));
        }

    };


}