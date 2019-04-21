class Solution {
private:
    vector<vector<int> > ret;
    vector<int> a;
public:
    void solve(int dep, int maxDep, int n, int start)
    {
        if (dep == maxDep)
        {
            ret.push_back(a);
            return;
        }
        
        for(int i = start; i <= n; i++)
        {
            a[dep] = i;
            solve(dep + 1, maxDep, n, i + 1);
        }      
    }
    
    vector<vector<int> > combine(int n, int k) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        a.resize(k);
        ret.clear();
        solve(0, k, n, 1);
        return ret;
    }
};