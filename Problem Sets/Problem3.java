public class Problem3 {

    // 1_ delete string occurrence in Circular Linked List
    public boolean delete(String target) {
        if (rear == null) { // list is empty
           return false;
        }
        if (rear == rear.next) { // list has only one node
           if (target.equals(rear.data)) { // found, delete, leaves empty list
              rear = null;
              return true;
           }
           else {   // not found
              return false;
           }
        }
        Node prev = rear, curr = rear.next;
        do {
           if (target.equals(curr.data)) {
              prev.next = curr.next;
              if (curr == rear) { // if curr is last node, prev becomes new last node
                 rear = prev;
              }
              return true;
           }
           // skip to next node
           prev = curr;
           curr = curr.next;
        }
        while (prev != rear);
        return false; // not found
     }


     // 2_Add item after first occurrence
     public boolean addAfter(String newItem, String afterItem) {
         if (rear == null){ //empty
             return false;
         }
         Node ptr = rear;
         do {
             if(afterItem.equals(ptr.data)){
                Node temp = new Node(newItem,ptr.next);
                ptr.next = temp;
                if(ptr == rear){ //new node becomes last
                    rear = temp;
                }
                return true;
             } 
             ptr = ptr.next;
         }
         while (ptr != rear);
         return false; //afterItem not in list
     }


     // 3_moves target to front of Doubly Linked List
     public static DLLNode moveToFront(DLLNode front, DLLNode target) {
        if (target == null || front == null || target == front) {
            return target;
        }
        // delink the target from the list
        target.prev.next = target.next;
        // make sure there is something after target before setting its prev
        if (target.next != null) {
            target.next.prev = target.prev;
        }
        target.next = front;
        target.prev = null;
        front.prev = target;
        return target;
    }


    //4_ Reverse DLL
    public static DLLNode reverse(DLLNode front) {
        if(front == null ){
            return null;
        }
        DLLNode rear = front, prev = null;
        while(rear!=null){
            DLLNode temp = rear.next;
            rear.next = rear.prev;
            rear.prev = temp;
            prev = rear;
            rear = temp;
        }
    }


    //5_Reursive, Delete all occurrences
    public static Node deleteAll(Node front, String target) {
        if(front==null){
            return null;
        }
        if(front.data.equals(target)){
            return deleteAll(front.next, target);
        }
        front.next = deleteAll(front.next, target);
        return front;
    }


    //6_Recursive, Merge two linked lists into sorted list without duplicates
    public static Node merge(Node frontL1, Node frontL2) {
        if (frontL1 == null) {
           return frontL2;
        }
        if (frontL2 == null) {
           return frontL1;
        }
        if (frontL1.data == frontL2.data) {
           // keep one copy
           frontL1.next = merge(frontL1.next, frontL2.next);
           return frontL1;
        }
        if (frontL1.data < frontL2.data) {
           frontL1.next = merge(frontL1.next, frontL2);
           return frontL1;
        }
        frontL2.next = merge(frontL2.next, frontL1);
        return frontL2;
     }