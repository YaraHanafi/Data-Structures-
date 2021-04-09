public class Problem9{
    //1_hash table analysis
    /*a_ 0 []->/

     1 []->/

     2 []->68->/

     3 []->/

     4 []->/

     5 []->/

     6 []->28->72->/

     7 []->/

     8 []->63->96->/

     9 []->/

     10[]->43->/*/

     //b   (1+1+2+1+2+1)/6 = 4/3

     //2_running time big(O) for inserting n keys
     //Chain is an unordered list --> For n, the total time is O(n)
     //Chain is an ordered list (entries stored in ascending order of keys) --> 
     //In the worst case, every new entry is to be added to the end of the chain. The first entry is added in 1 step, 
     //the second in 1 step, third in 2 steps, etc. The total time is: O(n^2)
     //Chain is an AVL tree (ordered by keys) --> Each insert takes logarithmic time in the size of the AVL tree 
     //(size includes the inserted entry). The first entry takes unit time to insert, 
     //the subsequent entries sum up to the following: O(nlog n)

     //3_Inserting and Rehashing
     class Node {
        int key;
        String value;
        Node next;
    }

    class Hashtable {
        Node[] entries;
        int numvalues;
        float max_load_factor;
        public Hashtable(float max_load_factor) { ... } // constructor
    }

    public void insert(int key, String value) {
        int index = key % entries.length;
        Node e = new Node();
        e.key = key;
        e.value = value;
        e.next = entries[index];
        entries[index] = e;
        numvalues++;
        float load_factor = (double)numvalues / entries.length;
        if (load_factor > max_load_factor) {
           rehash();
        }
    }
    
    private void rehash() {
        Node[] oldEntries = entries;
        int oldCapacity = oldEntries.length; 
        int newCapacity = 2*oldCapacity;
        entries = new Node[newCapacity];
        numvalues=0;
        for (int i = 0 ; i < oldCapacity ; i++) {
            for (Node e = oldEntries[i] ; e != null ; e = e.next) {
                insert(e.key, e.value);
            }
        }
    }


}