package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION

		int i = g.map.get(p1);
		int j = g.map.get(p2);
		boolean k;
		k = hasPath(i,j,g);
		if(k == false){
			return null;
		} else {
			int [] before = new int[g.members.length];
			BFSchain(g,i,j,before);
			ArrayList<String> chain = new ArrayList<String>();
        	int curr = j;
        	chain.add(g.members[curr].name);
        	while (before[curr] != -1) {
				int q = before[curr];
            	chain.add(0, g.members[q].name);
				curr = before[curr];
			}
			return chain;
		}
		
	}	

	private static void BFSchain(Graph g, int v,int w, int before[]){
		int n = g.members.length;
		boolean[] visited = new boolean[n];
		for (int i=0; i < n; i++) {
			visited[i] = false;
			before[i] = -1;
		}

		visited[v] = true;
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(v);

		while (!queue.isEmpty()) {
			int temp = queue.dequeue();
			for (Friend ptr=g.members[temp].first; ptr != null; ptr=ptr.next) {
				if (!visited[ptr.fnum]) {
					visited[ptr.fnum] = true;
					before[ptr.fnum] = temp;
					queue.enqueue(ptr.fnum);

					if (ptr.fnum == w){
						return;
					}
				}
			}
		}
	}

	private static boolean hasPath(int v, int w, Graph g) {
		if (v == w) return false;
		int n = g.members.length;
		boolean[] visited = new boolean[n];
		for (int i=0; i < n; i++) {
			visited[i] = false;
		}
		return pathDFS(v,w,visited,g);
	}

	private static boolean pathDFS(int current, int w, boolean[] visited, Graph g) {
		if (current == w) return true;
		visited[current] = true;
		for (Friend ptr=g.members[current].first; ptr != null; ptr=ptr.next) {
			if (!visited[ptr.fnum]) {
			   if (pathDFS(ptr.fnum, w, visited,g)) {
				  return true;
			   }
			}
		}
		return false;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION

		ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
		bfscli1(g, school, groups);
		return groups;
		
	}

	private static void bfscli1(Graph g, String school, ArrayList<ArrayList<String>> groups) {
		boolean[] visited = new boolean[g.members.length];
		for (int i=0; i < g.members.length; i++) {
			visited[i] = false;
		}
		Queue<Integer> queue = new Queue<Integer>();
		for (int v=0; v < visited.length; v++) {
			if (!visited[v]) {
				String n = g.members[v].school;
				if(n==null){
					visited[v] = true;
				}
				else if(n.equalsIgnoreCase(school)){
				queue.clear();
				bfscli2(v, visited, queue, g, school, groups);
				} else {
					visited[v] = true;
				}
			}
		}
	}
	
	private static void bfscli2(int start, boolean[] visited, Queue<Integer> queue, Graph g, String school, ArrayList<ArrayList<String>> groups) {
		ArrayList<String> group = new ArrayList<String>();
		group.add(g.members[start].name);
		visited[start] = true;
		queue.enqueue(start);

		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (Friend ptr=g.members[v].first; ptr != null; ptr=ptr.next) {
				if (!visited[ptr.fnum]) {
					int num = ptr.fnum;
					String n = g.members[num].school;
					if(n==null){
						visited[num] = true;
					}
					else if(n.equalsIgnoreCase(school)){
					group.add(g.members[num].name);
					visited[num] = true;
					queue.enqueue(num);
					} else {
						visited[num] = true;
					}
				}
			}
		}
		groups.add(group);
	}

	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		
		ArrayList<String> names = new ArrayList<String>();
		dfs(g,names);
		return names;
	}

	private static void dfs(Graph g, ArrayList<String> names) {
		boolean[] visited = new boolean[g.members.length];
		int[] dfsnum = new int[g.members.length];
		int[] back = new int[g.members.length];
		for (int l=0; l < visited.length; l++) {
			visited[l] = false;
			back[l] = -1;
			dfsnum[l] = -1;
		}
		for (int v=0; v < visited.length; v++) {
			if (!visited[v]) {
				int n =1;
				int next = v;
				boolean b = dfsr(v, visited, dfsnum, back, n, names, g, next);
			}
		}
	}

	private static boolean dfsr(int v, boolean[] visited, int[] dfsnum, int[] back, int n, ArrayList<String> names, Graph g, int next) {
		visited[v] = true;
		boolean BACK = false;
		dfsnum[v] = n;
		back[v] = n;
		for (Friend ptr=g.members[v].first; ptr != null; ptr=ptr.next) {
			int index = ptr.fnum;
			if (!visited[index]) {
				n++;
				next = index;
				BACK = dfsr(index, visited, dfsnum, back, n, names, g, next);
				if(BACK == true){
					if(dfsnum[v] > back[next]){
						back[v] = Math.min(back[v],back[next]);
					} else if(dfsnum[v]<=back[next]){
						int fri = 0;
						for (Friend f=g.members[v].first; f != null; f=f.next) {
							fri++;
						}
						if(fri>1 && !names.contains(g.members[v].name)){
							if(dfsnum[v] == 1){
								if(v>next){
									names.add(g.members[v].name);
								}
							} else{
							names.add(g.members[v].name);
							}
						}
					}
				}
			} else {
				back[v] = Math.min(back[v],dfsnum[index]);
			}
		}
		return true;
	}
}

