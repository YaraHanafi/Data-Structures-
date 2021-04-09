import java.io.*;
import java.util.*;

public class fdriver{
 public static void main(String[] args)
    throws FileNotFoundException {
    Graph g = new Graph(new Scanner(new File("t.txt")));
    //ArrayList<String> r = Friends.shortestChain(g,"rachel","rich");
    //ArrayList<ArrayList<String>> t = Friends.cliques(g,"rutgers");
    ArrayList<String> e = Friends.connectors(g);

 }
}