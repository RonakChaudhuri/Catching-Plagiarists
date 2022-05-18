
/**
 * Write a description of class Comparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Comparator implements Comparable<Comparator>
{
    private String fileA;
    private String fileB;
    private int hits;
    
    public Comparator()
    {
        fileA = "";
        fileB = "";
        hits = 0;
    }
    
    public Comparator(String a, String b, int h)
    {
        fileA = a; 
        fileB = b; 
        hits = h; 
    }
    
    public int getHits()
    {
        return hits;
    }
    
    public int compareTo(Comparator directory)
    {
        return directory.getHits() - hits;
    }
    
    public String toString(){
        return "[" + fileA + ",   " + fileB + "]   -> " + hits; 
    } 
}
