public class Solution {
	List<Integer> numbers = new LinkedList<>();
	List<Character> opraters = new ArrayList<>();
    public List<Integer> diffWaysToCompute(String input) {
        StringBuilder num = new StringBuilder();
        for(int i=0;i<input.length();i++){
        	if('0'<=input.charAt(i)&&input.charAt(i)<='9'){
        		num.append(input.charAt(i));
        	}else{
        		numbers.add(Integer.parseInt(num.toString()));
        		opraters.add(input.charAt(i));
        		num = new StringBuilder();
        	}
        }
        numbers.add(Integer.parseInt(num.toString()));
        List<Integer> res = dfs(numbers,opraters);
        Collections.sort(res);
        return res;
    }
    
    private List<Integer> dfs(List<Integer> numbers,List<Character> opraters){
    	List<Integer> res = new ArrayList<>();
    	if(numbers.size()==1){
    		res.add(numbers.get(0));
    		return res;
    	}
    	for(int i=0;i<opraters.size();i++){
    		char c = opraters.get(i);
    		List<Integer> leftRes = dfs(numbers.subList(0, i+1),opraters.subList(0, i));
    		List<Integer> rightRes = dfs(numbers.subList(i+1,numbers.size()),opraters.subList(i+1,opraters.size()));
    		for(int left:leftRes){
    			for(int right:rightRes){
    				res.add(opRes(left,right,c));
    			}
    		}
    	}
    	return res;
    }
    
    private int opRes(int a,int b,char op){
    	switch(op){
    		case '*' :
    			return a*b;
    		case '+':
    			return a+b;
    	}
    	return a-b;
    }
}