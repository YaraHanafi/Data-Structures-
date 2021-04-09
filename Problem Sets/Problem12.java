public class Problem12{
 
 //Q1_You are given a directed graph:
 class Neighbor {
    public int vertex;
    public Neighbor next;
    ...
 }

 class Vertex {
    String name;
    Neighbor neighbors; // adjacency linked lists for all vertices
 }

 public class Graph {
    Vertex[] vertices;

    // returns an array of indegrees of the vertices, i.e. return[i] is the
    // number of edges that are directed IN TO vertex i
   
//Assuming that the graph has already been read in, complete the indegrees method.
    public int[] indegrees() {
       int[] indeg = new int[vertices.length];
       for (int i=0; i < vertices.length; i++) {
           for (Neighbor nbr=vertices[i].neighbors; nbr != null; nbr=nbr.next) {
               indeg[nbr.vertex]++;   
           }
       }
       return indeg;
    }
}
//What is the big O running time of your indegrees implementation if the graph has n vertices and e edges? 
/*
Accessing the front of a vertex's neighbors list, updating the indegree of a vertex, and accessing 
the neighbor of a vertex are each unit time operations.
There are e neighbors in all, for all vertices put together, so the neighbor access part contributes 
e units of time. Accessing the front of a vertex's neighbors list is done n times in all, once per vertex. 
There are e indegree updates, one per edge.
Total is e + n + e = n + 2e, which is O(n+e)
*/

//Q2_toplogically sort the vertices using using BFS (breadth-first search)
public class Queue<T> {
    ...
    public Queue() {...}
    public void enqueue(T item) {...}
    public T dequeue() throws NoSuchElementException {...}
    public boolean isEmpty() {...}
    ...
 }

// returns an array with the names of vertices in topological sequence
public String[] topsort() 
throws Exception {

   // compute indegrees        
   int[] indeg = indegrees();

   int topnum=0;
   String[] tops = new String[vertices.length];
   Queue queue = new Queue();

   // find all vertices with indegree zero, assign them topological numbers, and enqueue
   for (int i=0; i < indeg.length; i++) { 
       if (indeg[i] == 0) {
          tops[topnum++] = vertices[i].name;
          queue.enqueue(i);
       }
   }
  
   // loop until queue is empty
   while (!queue.isEmpty()) {
       int v = queue.dequeue();
       for (Neighbor nbr=vertices[v].neighbors; nbr != null; nbr=nbr.next) {
           indegrees[nbr.vertex]--;
           if (indegrees[nbr.vertex] == 0) {
              tops[topnum++] = vertices[nbr.vertex].name;
              queue.enqueue(nbr.vertex);
           }    
       }
   }
   
   return tops;
}


//Q3_A strongly connected directed graph is one in which every vertex can reach all other vertices. 
//In the following Graph class, implement a method stronglyConnected that returns true if the graph is 
//strongly connected, and false otherwise. What is the worst case big O running time of your implementation?
    public boolean stronglyConnected() {
    boolean[] visited = new boolean[vertices.length];
    for (int i=0; i < vertices.length; i++) {
        for (int j=0; j < visited.length; j++) {
            visited[i] = false;
        }

        dfs(i, visited);

        for (int j=0; j < visited.length; j++) {
            if (!visited[j]) {
               return false;
            }
        }
    }
    return true;
 } 
//In the worst case, every vertex can reach all other vertices. The dfs method is called once for 
//each vertex, and the time for a dfs run is O(n+e). So the total time is n*O(n+e) = O(n2+ne).
//Note: The worst case would occur when the graph is strongly connected, otherwise the algorithm 
//terminates earlier. And any strongly connected graph must have at least n edges. Which means that the 
//term ne in the big O is at least n2, so the other n2 term can be discarded. Which means the big O can 
//be simplified to O(ne).


//Q4_Use Dijsktra's shortest paths algorithm to determine the minimum toll route from A to all other cities.
/*
Done   D[B]    D[C]    D[D]    D[E]    D[F]    D[G]    D[H]    D[I]
   ---------------------------------------------------------------------
     A     4,A       ∞      ∞       ∞       ∞        ∞     8,A       ∞
     B            12,B      ∞       ∞       ∞        ∞     8,A       ∞

     H            12,B      ∞       ∞       ∞       9,H            15,H
     G            12,B      ∞       ∞       11,G                   15,H
     F            12,B     25,F    21,F                          15,H
     C                     19,C    21,F                          14,C
     I                     19,C    21,F
     D                             21,F
     E
*/

}