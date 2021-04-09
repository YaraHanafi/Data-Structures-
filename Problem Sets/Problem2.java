public class Problem2 {

    // 1_add newItem before currItem
    public static IntNode addBefore(IntNode front, int target, int newItem) {
        IntNode prev = null, ptr=front;
        while (ptr != null && ptr.data != target) {
            prev = ptr;
            ptr=ptr.next;
        }
        if (ptr == null) {
            return front;
        }
        IntNode temp = new IntNode(newItem, ptr);
        if (prev == null){
            return temp;
        }
        prev.next = temp;
        return front;
    }

    // 2_newItem before last item
    public static IntNode addBeforeLast(IntNode front, int item) {
        IntNode ptr = front, prev=null;
        while (ptr.next != null) {
            prev = ptr;
            ptr=ptr.next;
        }
        if (ptr == null) {
            return null;
        }
        IntNode temp = new IntNode(item, ptr);
        if(prev==null){
            return temp;
        }
        prev.next = temp;
        return front;
    }

    //3_Search String Occurences
    public static int numberOfOccurrences(StringNode front, String target){
        int sum = 0;
        for (StringNode ptr = front; ptr != null; ptr = ptr.next) {
			if (target.equals(ptr.data)) {
				sum += 1;
			}
        }
        return sum;
    }   
   
    
    //4_Delete every other item from list
    public static void deleteEveryOther(IntNode front) {
        if (front == null) {
            return;
        }
        Node prev=front, ptr=front.next;
        boolean tbd=true;
        while (ptr != null) {
            if (tbd) {
               ptr = ptr.next;   // advance to after item to be deleted
               prev.next = ptr;  // bypass item to be deleted
               tbd = false;      // next item should not be deleted
            } else {
               prev = ptr;       // don't delete this (ptr) item, advance prev and ptr
               ptr = ptr.next;
               tbd = true;       // but mark next item for deletion
            }
        }
    } 

    //5_Delete String Occurences
    public static StringNode deleteAllOccurrences(StringNode front, String target) {
       
        if (front == null) {
            return null;
         }
       
         StringNode curr=front, prev=null;
       
         while (curr != null) {
            if (curr.data.equals(target)) {
               if (prev == null) {       // target is the first element 
                  front = curr.next;
               } else {
                  prev.next = curr.next;
               }
            } else {
               prev = curr;
            }
            curr = curr.next;
         }
       
         return front;
       } 
       

   // creates a new linked list consisting of the items common to the input lists
   // returns the front of this new linked list, null if there are no common items
   //6_Finding common elements
   public IntNode commonElements(IntNode frontL1, IntNode frontL2) {
    IntNode first=null, last=null;  
    while (frontL1 != null && frontL2 != null) {
       if (frontL1.data < frontL2.data) {
          frontL1 = frontL1.next;
       } else if (frontL1.data > frontL2.data) {
          frontL2 = frontL2.next;
       } else {
          IntNode ptr = new IntNode(frontL1.data, null);
          if (last != null) {
             last.next = ptr;
          } else {
             first = ptr;
          }
          last = ptr;
          frontL1 = frontL1.next;
          frontL2 = frontL2.next;
       }
    }
    return first;
 }


}
