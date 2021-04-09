public class Problem11{
    //Q1
    //weighted undirected graph and adjacency linked list
    //when will linked list take less storage than the weighted directed graph?
    //Space for adjacency matrix (AMAT) is n^2. Space for adjacency linked lists (ALL) is 
    //n + 3*2e = n + 6e. (Each node needs 3 units of space: 
    //1 for the neighbor number, 1 for the edge weight, and 1 for the next node reference. 
    //And there are 2e nodes.) The space required by AMAT and 
    //ALL is the same when n^2 = n + 6e, i.e. when e = (n^2 - n)/6.
    //The minimum value of e for which the adjacency matrix representation would require less space 
    //than the adjacency linked lists representation is one more than the e above, which would be 
    // (n^2 - n)/6+1.

    //Q2_The complement of an undirected graph
    //What would be the worst case running time (big O) of an implementation for a 
    //graph with n vertices and e edges?

   public Graph complement() {
      boolean[][] temp = new boolean[adjlists.length][adjlists.length];
      // in temp, fill in trues for the edges
      for (int v=0;v < adjlists.length;v++) {
         for (Edge e=adjlists[v];e != null;e = e.next) {
            temp[v][e.vnum] = true;
         }
      }
      // complement temp
      for (int i=0; i < adjlists.length; i++) {
          for (int j=0; j < adjlists.length; j++) {
              if (i != j) { // leave out the diagonal
                 temp[i][j] = !temp[i][j];
              }
          }
      }
      // now create the adjacency linked lists for the complement graph
      Edge[] compall = new Edge[adjlists.length];
      for (int v=0;v < compall.length;v++) {
         for (int j=0; j < adjlists.length; j++) { 
            if (temp[v][j]) {
               Edge e = new Edge();
               e.vnum = j; 
               e.next = compall[v];
               compall[v] = e;
            }
         }
      }
      // create new Graph and return
      Graph comp = new Graph();
      comp.adjlists = compall;
      return comp;
   }

//Running time is O(n^2) - this is the time needed to compute the complement matrix. 
//(A more abstract way of reasoning about this is to note that the original graph and its 
//complement would involve all possible edges between the n vertices, which is O(n^2).)


//Q_3This graph has n+2 vertices and 2n edges. For every vertex labeled i, 1 <= i <= n, 
//there is an edge from S to i, and an edge from i to T.
//How many different depth-first search sequences are possible if the start vertex is S?
//How many different breadth-first search sequences are possible if the start vertex is S?
/*
For instance, say n = 3. Here are all possible DFS sequences (3! = 6):


     S 1 T 2 3
     S 1 T 3 2
     S 2 T 1 3
     S 2 T 3 1
     S 3 T 1 2
     S 3 T 2 1
n!, similar to DFS. The only difference is that T will be the last vertex to be visited.
 So, if n = 3, the possible BFS sequences are:
     S 1 2 3 T
     S 1 3 2 T
     S 2 1 3 T
     S 2 3 1 T
     S 3 1 2 T
     S 3 2 1 T
*/

//Q_4 use DFS to check if there is a path from one vertex to another in a directed graph
public boolean hasPath(int v, int w) {
    if (v == w) return false;
    int n = adjlists.length;
    boolean[] visited = new boolean[n];
    for (int i=0; i < n; i++) {
        visited[i] = false;
    }
    return pathDFS(v,w,visited);
 }

 private boolean pathDFS(int current, int w, boolean[] visited) {
    if (current == w) return true;
    visited[current] = true;
    for (Neighbor ptr=adjlists[current]; ptr != null; ptr=ptr.next) {
        if (!visited[ptr.vertex]) {
           if (pathDFS(ptr.vertex, w, visited)) {
              return true;
           }
        }
    }
    return false;
 }

}