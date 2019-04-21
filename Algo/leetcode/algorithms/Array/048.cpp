class Solution {
public:
    void rotate(vector<vector<int> > &matrix) 
    {
      int n = matrix.size();
      int layers = n / 2;
         
    for(int layer = 0; layer < layers; layer++) {
      for(int i = layer; i < n - 1 - layer; i++) {           
         int temp = matrix[i][layer];   
         matrix[i][layer] = matrix[n - 1 - layer][i];
         matrix[n - 1 - layer][i] = matrix[n - 1 - i][n - 1 - layer];
         matrix[n - 1 - i][n - 1 - layer] = matrix[layer][n - 1 - i];
         matrix[layer][n - 1 - i] = temp;
       }    
    }        
    }
};