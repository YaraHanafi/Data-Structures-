public class Problem10{
    //1_heap structure
    //bulid heap insertion
    //12, 19, 10, 4, 23, 7, 45, 8, 15
    /*Insert       Heap after insertion     Number of comparisons
--------------------------------------------------------------------
12           12                          0
19           19 12                       1
10           19 12 10                    1
4            19 12 10 4                  1
23           23 19 10 4 12               2
7            23 19 10 4 12 7             1
45           45 19 23 4 12 7 10          2
8            45 19 23 8 12 7 10 4        2
15           45 19 23 15 12 7 10 4 8     2*/

//heap deletion
/*Heap after deletion        Number of comparisons
-------------------------------------------------
23 19 10 15 12 7 8 4       4
19 15 10 4 12 7 8          4
15 12 10 4 8 7             4
12 8 10 4 7                4
10 8 7 4                   2
8 4 7                      2
7 4                        1
4                          0
empty                      0*/

//2_Then, given an integer k, we would like to print all the values in this heap that are greater than k.
public void printGreater(int[] H, int n, int k) {
    recursivePrintGreater(H, n, k, 0);
 }

 private void recursivePrintGreater(int[] H, int n, int k, int  rootIndex) {  
    if (rootIndex >= n) return; // out of bounds
    if (H[rootIndex] <= k) return; // all values <= k in this sub-heap
    System.out.println(H[rootIndex]); // print root value
    recursivePrintGreater(H, n, k, 2*rootIndex+1); // left sub-heap
    recursivePrintGreater(H, n, k, 2*rootIndex+2); // right sub-heap
 }

 //3_What would be the worst case running time (big O) of this update process on a heap with n entries?
 // log n + log (n-1) + log (n-2) + .... + 1 = log (n!) = O(n*log n) for both delete and insert so 
 //update is  O(n*log n)

 //4_ two heaps, stored in arrays. Merge them into a single heap, and return this heap. 
 public static <T extends Comparable<T>> T[] merge(T[] heap1, T[] heap2) {
    T[] res = (T[])new Object[heap1.length + heap2.length];
    for (int i=0; i < heap1.length; i++) {
        res[i] = heap1[i];
    }
    for (int i=0; i < heap2.length; i++) {
        res[i+heap1.length] = heap2[i];
    }

    // in res, sift up starting with entries copied from the second heap 
    for (int s=heap1.length; s < res.length; s++) {
        int k=s;
        // sift up res[k]
        while (k > 0) {
           int p = (k-1)/2;
           if (res[k].compareTo(res[p]) > 0) { // switch
              T temp = res[k]; res[k] = res[p]; res[p] = temp;
              k = p;
           } else {
              break; 
           }
        }

    }

    return res;
 }
}