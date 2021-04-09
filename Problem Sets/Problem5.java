public class Problem5{
    
    //1_Sequential Search
    // (1) (9+1)/2 = 5
    // (2) 4.1
    // (3) 9 15 {3,5,18} {2,-5,7,8}

    //2_move item by one
    public class LinkedList<T> {
        private Node<T> front;
        int size;
        // moves the target one place toward the front
        // doesn't do anything if target is in the first node
        // returns true if target found, false otherwise
        public boolean moveTowardFront(T target) {
            Node ptr = front, prev = null;
            while (ptr != null) {
               if (ptr.data.equals(target)) {
                  break;
               }
               else {
                  prev = ptr;
                  ptr = ptr.next;
               }
            }
            if (ptr == null) {  // not found
               return false;
            }
            if (prev == null) { // front node, do nothing
               return true;
            }
            // switch with previous
            T temp = prev.data;
            prev.data = ptr.data;
            ptr.data = temp;
            return true;
        }
    }

    //3_big o analysis
    // (1) success = 7; failure= 8
    // (2) average for success = 5
    // (3) It would take 6 comparisons to get to any of the failure nodes at the last but one level, and 8 comparisons for the last. 
    //     So the average MUST be between 6 and 8, no matter what the probability distribution is over all of these possibilities.

    //4_binary search question
    // (1) took 4 comparisons while binary only one
    // (2) binary search 8; method 5
    // (3)-1 Worst case #comparisons for success = 5, same for failure
    // (3)-2  So the average MUST be in the range 4 to 5 for both success and failure
    // (3)-3 Lazy binary search is better when there are more failures than successes since failures take fewer comparisons on 
    //       average. While matches at certain positions now take longer to get to, the average number of comparisons is reduced 
    //       since the new tree is less than double the height of the old, but the number of comparisons is halved. 
    //        So, in sum, lazy binary search reduces the average number of comparisons for both successes and failures, and it 
    //        levels the playing field over successes and failures.



}