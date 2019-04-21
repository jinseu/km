import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solution {

    public boolean isMatch(String s, String p) {
    if (s == null || p == null) return false;
    if (s.equals(p)) return true;
    int m = s.length();
    int n = p.length();
    int posS = 0;
    int posP = 0;
    int posStar = -1;
    int posOfS = -1;
    //if posS == posP || posP == '?', ++posS and ++posP.
    //posOfS, posStar, record the positon of '*' in s and p, ++posP and go on.
    //if not match, go back to star, ++posOfS
    while (posS < m) {
      if (posP < n && (s.charAt(posS) == p.charAt(posP) || p.charAt(posP) == '?')) {
        ++posS;
        ++posP;
      }
      else if (posP < n && p.charAt(posP) == '*') {
        posStar = posP;
        posOfS = posS;
        ++posP;
        continue;
      }
      else if (posStar != -1) {
        posS = posOfS;
        posP = posStar + 1;
        ++posOfS;
      }
      else {
        return false;
      }
    }
    while (posP < n && p.charAt(posP) == '*') {
      ++posP;
    }
  
    return posS == m && posP == n;
    }
}