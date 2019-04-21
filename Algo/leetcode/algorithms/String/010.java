import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public boolean isMatch(String s, String p) {
        Pattern patt = Pattern.compile("^"+p+"$");
        Matcher mat = patt.matcher(s);
        return mat.find();        
    }
}