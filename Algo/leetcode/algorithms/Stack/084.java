public class Solution {
    public int largestRectangleArea(int[] height) {
		int area = 0;
		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			if (heightStack.empty() || heightStack.peek() <= height[i]) {
				heightStack.push(height[i]);
				indexStack.push(i);
			} 
			else if (heightStack.peek() > height[i]) {
				int j = 0;
				while (!heightStack.empty() && heightStack.peek() > height[i]) {
					j = indexStack.pop();
					int currArea = (i - j) * heightStack.pop();
					if (currArea > area) {
						area = currArea;
					}
				}
				heightStack.push(height[i]);
				indexStack.push(j);
			}
		}
		while (!heightStack.empty()) {
			int currArea = (height.length - indexStack.pop())
					* heightStack.pop();
			if (currArea > area) {
				area = currArea;
			}
		}
		return area;      
    }
}