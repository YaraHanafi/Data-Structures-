public class Problem4{  
    
    //1_number of items in stack
    public static <T> int size(Stack<T> S) {
        // COMPLETE THIS METHOD
        Stack<T> temp  = new Stack<>();
        int count = 0;
        while (!S.isEmpty()) {
           temp.push(S.pop());
           count++;
        }
        while (!temp.isEmpty()) {
           S.push(temp.pop());
        }
        return count;
    }
    //pop, loop and count, o(n)

    //2_Postfix
    public static float postfixEvaluate(String expr) { 
        /** COMPLETE THIS METHOD **/
        Stack<Float> stk = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
           char ch = expr.charAt(i);
           if (ch == ' ') {
              continue;
           }
           if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
              float second = stk.pop();
              float first  = stk.pop();
              switch (ch) {
                 case '+':
                    stk.push(first + second);
                 case '-':
                    stk.push(first - second);
                 case '*':
                    stk.push(first * second);
                 case '/':
                    stk.push(first / second);
              }
              continue;
           }
           stk.push((float) Character.digit(ch, 10));
        }
        return stk.pop();
    }

    //3_expanding array
    // 18 to allocate new space, 1000 to allocate items, each time it increases by 50 it allocates all items again
    // 100 + (1 + 100 + 50) + (1 + 150 + 50) + (1+ 200 + 50) + ... + (1 + 950 + 50)


    //4_array resize
    //total = 5 + 155 + 100 = 260

    //5_peek function for queue
    // way 1
    // returns the item at the front of the given queue, without
    // removing it from the queue
    public static <T> T peek(Queue<T> q) throws NoSuchElementException {
    if (q.isEmpty()) {
       throw new NoSuchElementException("Queue Empty");
    }
    T result = q.dequeue();
 
    Queue<T> temp = new Queue<>();
    temp.enqueue(result);
 
    while (!q.isEmpty()) {
       temp.enqueue(q.dequeue());
    }
 
    while (!temp.isEmpty()) {
       q.enqueue(temp.dequeue());
    }
    return result;
 }

 // way 2
 // returns the item at the front of the given queue, without
// removing it from the queue
public static <T> T peek(Queue<T> q) throws NoSuchElementException {
    if (q.isEmpty()) {
       throw new NoSuchElementException("Queue Empty");
    }
    T result = q.dequeue();
    q.enqueue(result);
 
    // dequeue an element and enqueue it again for (size-1) elements
    // if there was only 1 element, this loop will not execute
    for (int i = 0; i < q.size() - 1; i++) {
       q.enqueue(q.dequeue());
    }
    return result;
 }

 //6_split even
// extract the even position items from this queue into
// the result queue, and delete them from this queue
public Queue<T> evenSplit() {
    // Front of queue is at position 1, so we will extract 2nd, 4th, 6th, ...
    Queue<T> evenQueue    = new Queue<>();
    int      originalSize = size();  // size of this original queue
 
    // iterate once over each pair of queue elements
    for (int pos = 2; pos <= originalSize; pos += 2) {
       // the first in a pair is recycled
       enqueue(dequeue());
 
       // the second in a pair goes to result queue
       evenQueue.enqueue(dequeue());
    }
 
    // if original size was an odd number, we need to
    // recycle one more time
    if ((originalSize % 2) == 1) {
       enqueue(dequeue());
    }
 
    return evenQueue;
 }
}