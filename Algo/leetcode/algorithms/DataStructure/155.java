class MinStack {
	private Stack<Integer> s;
	private Stack<Integer> mins;
	public MinStack()
	{
		s = new Stack<Integer>();
		mins = new Stack<Integer>();
	}
    public void push(int x) 
    {
        s.push(x);
        if(mins.empty())
        {
        	mins.push(x);
        }
        else
        {
        	if(x <= mins.peek())
        	{
        		mins.push(x);
        	}
        }
    }

    public void pop() 
    {
    	int x = mins.peek();
    	int y = s.peek();
    	if(y > x)
    	{
           s.pop();
           
    	}
    	else
    	{
    		s.pop();
    		mins.pop();
    	}
    }

    public int top() {
        return s.peek();
    }

    public int getMin() 
    {
    	return mins.peek();
        
    }
}
