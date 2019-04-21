class MyQueue {
    private Stack<Integer>[] st = new Stack[2];
    int curr = 0;
    int next = 1;
    public MyQueue()
    {
    	st[0] = new Stack<Integer>();
    	st[1] = new Stack<Integer>();
    	curr = 0;
    }
    public void push(int x) {
        st[next].push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!st[curr].isEmpty())
        	st[curr].pop();
        else
        {
           while(!st[next].isEmpty())
        	   st[curr].push(st[next].pop());
           st[curr].pop();
        }
    }
    public int peek() {

        if(!st[curr].isEmpty())
        	return st[curr].peek();
        else
        {
           while(!st[next].isEmpty())
        	   st[curr].push(st[next].pop());
           return st[curr].peek();
        }   
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(st[curr].isEmpty()&&st[next].isEmpty())
        	return true;
        else
        	return false;
    }
}