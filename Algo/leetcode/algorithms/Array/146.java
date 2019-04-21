public class LRUCache {
	class CacheInfo
	{
		CacheInfo next;
		CacheInfo prev;
		int Key;
		int Value;
	}
	private int cap;
	private int size = 0;
	private TreeMap<Integer,CacheInfo> heapGet;//key   CacheInfo
	private CacheInfo head;
	private CacheInfo last;

    public LRUCache(int capacity) 
    {
    	cap = capacity;
        size = 0;
        heapGet = new TreeMap<Integer,CacheInfo>();
        head = null;
        last = null;
    }
    public void putHead(CacheInfo ca)
    {
    	if(head == null)
    	{
    		head = ca;
    		last = ca;
    		
    	}
    	else if(ca == head)
    		return ;
    	else if(ca == last)
    	{
    		last = ca.prev;
    		last.next = null;
    		ca.next = head;
    		ca.prev = null;
    		head.prev = ca;
    		head = ca;
    		return ;
    	}
    	else if(ca.prev == null)
    	{
    		ca.next = head;
    		ca.next.prev = ca;
    		head = ca;
    	}
    	else
    	{
    		ca.prev.next = ca.next;
    		ca.next.prev = ca.prev;
    		ca.next = head;
    		head.prev = ca;
    		ca.prev = null;
    		head = ca;
    	}
    	if(size > cap)
    	{
    		heapGet.remove(last.Key);
    		last = last.prev;
    		last.next = null;
    		size--;
    		
    	}
    }
    public int get(int key) 
    {
    	CacheInfo ca = heapGet.get(key);
    	if(ca == null)
    		return -1;
    	else
    	{
    		putHead(ca);
    		return ca.Value;
    	}
    }
    
    public void set(int key, int value) 
    {
    	CacheInfo in = heapGet.get(key);
    	if(in != null)
    	{
    		in.Value = value;
    		putHead(in);
    		return ;
    	}
    	if(in == null)
    	{
    		in = new CacheInfo();
    		in.Key = key;
    		in.Value = value;
    		heapGet.put(in.Key, in);
    	    size++;
    		putHead(in);
    		
    	}
		return ;        
    }
}