public class Solution {
	HashMap<String,Integer> graph = new HashMap<String,Integer>();
	List<List<String>> final_ans = new LinkedList<List<String>>();
	List<String> path = new LinkedList<String>();
	void bfs(int word_len, String start, String end, Set<String> dict)
	{
		Queue <String> que = new LinkedList <String>();
		que.add(start);
		graph.put(start, 1);
		int deep = 1;
		while (!que.isEmpty()){
			String head = que.poll();
			if (head.equals(end)){
				continue;
			}
			for (int i = 0; i < word_len; i ++){
				for (char j = 'a'; j <= 'z'; j ++){
					String tail = head.substring(0,i)+j+head.substring(i+1);
					if (dict.contains(tail)&&!graph.containsKey(tail)){
						deep = graph.get(head);
						graph.put(tail, deep+1);
						que.add(tail);
					}
				}
			}
		}
	}
	void dfs(int word_len, String cur, String end, Set<String> dict, List<String> newPath){
		
		
		if (cur.equals(end)){
			newPath.add(end);
			Collections.reverse(newPath);
			final_ans.add(newPath);
			return ;
		}
		newPath.add(cur);
		int next_deep = 0;
		if (graph.get(cur)!=null)  next_deep = graph.get(cur);
		for (int i = 0; i < word_len; i ++){
			for (char j = 'a'; j <= 'z'; j ++){
				String next = cur.substring(0,i)+j+cur.substring(i+1);
				if (graph.get(next)!=null && graph.get(next) == next_deep-1){
					List<String> newPathArray = new LinkedList<String>(newPath);  
					dfs(word_len, next, end, dict, newPathArray);
					
				}
			}
		}
	}
	public List<List<String>> findLadders(String start, String end, Set<String> dict)
    {
		bfs(start.length(), start, end, dict);
		dfs(start.length(), end, start, dict, path);
		return final_ans;
    }
}