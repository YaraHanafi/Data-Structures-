public class Problem6 {
    
    //1_Draw BST
    /*                   10            // 0 comparisons for 1st item
                      /       \
                     3        17       // 2 comparisons each for 3 and 17
                      \      /   \
                       7    15   90    // 4 comparisons each for 7, 15, and 90
                                 /
                                22     // 6 comparisons
                                  \
                                  40   // 8 comparisons 
Total number of comparisons = 30 */

//2_Analysis BST
// Success =9 , Failure=10
// Average Success = 38/8
// So in all, comparisons=3, pointer assignments=4+4+1+1=10, for a total of 3+10=13 units of work.

//3_Recursive Insert function for BST
public class BSTN<T extends Comparable<T>> {
      public void insert(T target) 
      throws IllegalArgumentException {
              root = insert(target, root);
              size++;
      }

      private BSTNode<T> insert(T target, BST<T> root) 
      throws IllegalArgumentException {
             
              if (root == null) {
                      return new BSTNode(target);
              }

              int c = target.compareTo(root.data);
              if (c == 0) {
                      throw new IllegalArgumentException("Duplicate key");
              }
              if (c < 0) {
                      root.left = insert(target, root.left);
              } else {
                      root.right = insert(target, root.right);
              }
              return root;
      }
    }

//4_Compare node values and count within range
public static <T extends Comparable<T>> 
void keysInRange(BSTNode<T> root, T min, T max, ArrayList<T> result) {
    if (root == null) { 
       return;
    }
    int c1 = min.compareTo(root.data);
    int c2 = root.data.compareTo(max);
    if (c1 <= 0 && c2 <= 0) {  // min <= root <= max) 
       result.add(root.data);
    }
    if (c1 < 0) {
       keysInRange(root.left, min, max, result);
    }
    if (c2 < 0) {
       keysInRange(root.right, min, max, result);
    }
}

//5_Reverse BST Nodes
public static <T extends Comparable<T>> 
void reverseKeys(BSTNode<T> root) {
   if (root == null) { 
      return;
   }
   reverseKeys(root.left);
   reverseKeys(root.right);
   BSTNode<T> ptr = root.left;
   root.left = root.right;
   root.right = ptr;
}

//6_Recursive to find K largest in BST
public static <T extends Comparable<T>> 
T kthLargest(BSTNode<T> root, int k) {
   if (root.rightSize == (k-1)) {
       return root.data;
   }
   if (root.rightSize >= k) {
       return kthLargest(root.right, k);
   }
   return kthLargest(root.left, k-root.rightSize-1);
}

}
