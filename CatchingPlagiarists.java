import java.util.*;
import java.io.*;
/**
 * Write a description of class CatchingPlagiarists here.
 *
 * @author Ronak Chaudhuri
 * @version May 22nd, 2020
 */
public class CatchingPlagiarists
{
    public static void main(String args[]) throws IOException
    {
        String docSize = "";
        int numWords = 0;
        int hits = 0;

        System.out.println("Size of directory?(Small, Medium, Large)");
        Scanner docSizeScanner = new Scanner(System.in);
        docSize = docSizeScanner.next() + " number of documents";
        System.out.println("Number of word sequences?");
        Scanner numWordsScanner = new Scanner(System.in);
        numWords = numWordsScanner.nextInt();
        System.out.println("Number of hits threshold?");
        Scanner hitsScanner = new Scanner(System.in);
        hits = hitsScanner.nextInt();
        System.out.println("Processing...");

        File dir = new File(".");
        ArrayList<File> directories = new ArrayList<File>();
        for(File folder : dir.listFiles())
        {
            if(folder.isDirectory())
                directories.add(folder);
        }

        File directory = new File(docSize);
        String[] temp = directory.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].endsWith(".txt"))
            {
                files.add(temp[i]);
            }
        }

        ArrayList<ArrayList<String>> unsortedList = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < files.size(); i++)
        {
            unsortedList.add(phrases(numWords, docSize, files.get(i)));
        }
        ArrayList<Comparator> sorted = new ArrayList<Comparator>();
        sorted = sort(unsortedList, files);
        Collections.sort(sorted);
        for(int i = 0; i < hits; i++)
        {
            System.out.println((i+1) + ". " + sorted.get(i).toString());
        }

    }

    private static ArrayList<String> phrases(int numWords, String directory, String filename) throws IOException
    {
        ArrayList<String> words = new ArrayList<String>();
        String dirName = directory + "/" + filename;
        Scanner file = new Scanner(new File(dirName));
        
        while(file.hasNext())
        {
            String phrase = "";
            // read 'numWords' words from file
            for(int j = 0; j < numWords; j++)
            {
                if(file.hasNext())
                {
                    // strip away all punctuation, and set to lowercase
                    phrase += file.next().replaceAll("[^A-z]","").toLowerCase();
                }
                else
                {
                    phrase = null; // not enough words at end of file
                }
            }
            file = new Scanner(new File(dirName));
            int c = 0;
            while(c < words.size())
            {
                file.next(); 
                c++;
            }
            words.add(phrase);
        }
        return words;
    }

    private static int hitList(ArrayList<String> File_A, ArrayList<String> File_B, int File_Asize,int File_Bsize)
    {
        int nwords = 0;
        for(int i = 0; i < File_Asize; i++)
        {
            for(int j = 0; j < File_Bsize; j++)
            {
                if(!(File_A.get(i) == null || File_B.get(j) == null) && (File_A.get(i).equals(File_B.get(j))))
                {
                    nwords++; 
                }
            }
        }
        return nwords;
    }

    private static ArrayList<Comparator> sort(ArrayList<ArrayList<String>> unsortedList,List<String> files)
    {
        ArrayList<Comparator> sorted = new ArrayList<Comparator>();
        int hits = 0;
        Comparator compare;
        for(int i = 0; i < unsortedList.size()-1; i++)
        {
            for(int j = i+1; j < unsortedList.size(); j++)
            {
                hits = hitList(unsortedList.get(i), unsortedList.get(j), unsortedList.get(i).size(), unsortedList.get(j).size()); 
                compare = new Comparator(files.get(i), files.get(j), hits);
                sorted.add(compare); 
            }
        }
        return sorted;
    }
}