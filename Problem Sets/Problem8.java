Problem8{
    //1_binary search tree
    // h+1 node at every level, we have h+1 levels
    //1+2*h
    //2^h

    //2_recursive same shape method of tree
    public class BTNode<T> {
        T data;
        BTNode<T> left, right;
        BTNode(T data, BTNode<T> left, BTNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static <T> boolean isomorphic(BTNode<T> T1, BTNode<T> T2) {
        if (T1 == null && T2 == null) {
           return true;
        }
        if (T1 == null || T2 == null || !isomorphic(T1.left, T2.left)) {
           return false;
        }
        return isomorphic(T1.right, T2.right);
     }

     //3_radix tree
     /*1            x
                /       \
                /          \
                x             x
              /   \          /
             x    01         x
              \  /  \      /    \
               x x  011   x      x
              /   \      /     /   \
            0010  0101  1000 1010  1011*/
     //2 4 + 2 + 4 + 4 + 3 + 4 + 4 = 25
     //4 k time not checked
     //5 n time not checked

     //5_general tree
     //find x parent
     public class BTNode<T> {
        T data;
        BTNode<T> left, right, parent;
     }
     public static <T> BTNode<T> genTreeParent(BTNode<T> x) {
        while (x.parent != null) {
           if (x == x.parent.left) {
              return x.parent;
           }
           x = x.parent;
        }
        return null;
     }

     //find kth child
     public static <T> BTNode<T> genTreekthChild(BTNode<T> x, int k) throws NoSuchElementException {
        if (k <= 0 || x.left == null) {
           throw new NoSuchElementException();
        }
        x = x.left;
        k--;
        while (k > 0 && x.right != null) {
           x = x.right;
           k--;
        }
        if (k > 0) { // k > #children
           throw new NoSuchElementException();
        }
        return x;
     }

     //6_BST inorder traversal
     // returns the first node that would be visited in an inorder traversal;
     // returns null if tree is empty
     public BTNode<T> firstInorder() {
        if (root == null) {
         return null;
        }
     // left most node in tree is the first node in inorder
     BTNode<T> prev = root, ptr = root.left;
     while (ptr != null) {
        prev = ptr;
        ptr = ptr.left;
     }
     return prev;
    }

    // returns the next node that would be visited in an inorder traversal;
    // returns null if there is no next node
    public BTNode<T> nextInorder(BTNode<T> currentNode) {
        if (currentNode == null) { // playing defense here
        return null;
        }
        // if there is a right subtree, then right turn, and left all the way to bottom
        if (currentNode.right != null) {
        BTNode<T> ptr = currentNode.right;
        while (ptr.left != null) {
            ptr = ptr.left;
        }
        return ptr;
        }
        // no right subtree
        BTNode<T> p = currentNode.parent;
        while (p != null && p.right == currentNode) {
        currentNode = p;
        p = p.parent;
        }
        return p;
    }

    //7_huffman coding
    /* 1 encode
    R    C    L    B    H    A    E
    0.06 0.06 0.06 0.1  0.15  0.2  0.37
   
            1.0
         0 /    \ 1
          /      \
         E       0.63
              0 /   \ 1
               /     \
            0.27     0.36
          0 /  \1   0/   \1
           /    \   /     \
         0.12    H 0.16    A
       0 /   \1  0/   \1
        /     \  /     \
       R      C  L      B
   
   R 1000
   C 1001
   L 1100
   B 1101
   A 111
   H 101
   E 0 */
   //3 average code length
   // 1 * 0.37 + 4 * 0.06 + 4 * 0.06 + 3 * 0.15 + 4 * 0.06 + 4 * 0.10 + 3 * 0.20 = 2.54
   //4 ratio encoded vs unencoded
   // Length of unencoded representation using 7 bits per character is 7 * 13 = 91,
   // while length of representation using Huffman codes is 39. The ratio of encoded to unencoded is 39/91.
   //5 decoding exercise
   // AHBEABLECAR

}