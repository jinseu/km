public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n + 1];
        int maxArea = 0;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++) 
            {
                if(matrix[i][j] == '0')
                {
                    height[i][j] = 0;
                }
                else 
                {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }
        for(int i = 0; i < m; i++)
        {
            int area = maxAreaInHist(height[i]);
            if(area > maxArea)
            {
                maxArea = area;
            }
        }
        return maxArea;        
    }
         private int maxAreaInHist(int[] height)
         {
		int area = 0;
		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			if (heightStack.empty() || heightStack.peek() <= height[i]) {
				heightStack.push(height[i]);
				indexStack.push(i);
			} else if (heightStack.peek() > height[i]) {
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