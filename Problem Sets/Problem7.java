public class Problem7 {
//1_fill heights in recursive
public class BSTNode<T extends Comparable> {
    T data;
    BSTNode<T> left, right;
    int height;
    ...
}  

// Recursively fills height values at all nodes of a binary tree
  public static <T extends Comparable> 
  void fillHeights(BSTNode root) {
     if (root == null) { return; }
     fillHeights(root.left);
     fillHeights(root.right);
     root.height = -1;
     if (root.left != null) {
        root.height = root.left.height;
     }
     if (root.right != null) {
        root.height = Math.max(root.height, root.right.height);
     }
     root.height++;  
  }

    //2_AVL tree analysis
    //Heights/Balance factors
    //A:h+1/right, C:h/equal, E:h+2/left, G:h+1/equal, B:h+2/left, F:h+3/left, D:h+4/right
    //Height of the tree is h+4

    //5_AVL rebalancing
    public static <T extends Comparable<T>>
    void rotateCase1(AVLTreeNode<T> x) {
       // r is root of taller subtree of x
       r = x.balanceFactor == '\' ? x.right : x.left;
       if (x.parent.left == x) { x.parent.left = r; } else { x.parent.right = r; }
       r.parent = x.parent;      
       if (x.balanceFactor == '\') { // rotate counter-clockwise
          AVLTreeNode temp = r.left;
          r.left = x;
          x.parent = r;
          x.right = temp;
          x.right.parent = x;
       } else { // rotate clockwise
          AVLTreeNode temp = r.right;
          r.right = x;
          x.parent = r;
          x.left = temp;
          x.left.parent = x;
       }
       // change bfs of r and x
       x.balanceFactor = '-';
       r.balanceFactor = '-';
       // x's height goes down by 1, r's is unchanged
       x.height--;
 
    }
 

}