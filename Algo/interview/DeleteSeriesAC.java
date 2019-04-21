package sample;

import java.util.Stack;

public class DeleteAC {
    public static String DeleteRepeatedAC(String str) {
        Stack<Character> s = new Stack<Character>();
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (c == 'b'){
                continue;
            } else if (c == 'a' || c == 'c'){
                if (s.empty()){
                    s.push(c);
                    continue;
                }
                Character cu = s.peek();
                if (cu != c) {
                    s.pop();
                } else {
                    s.push(c);
                }
            } else {
                s.push(c);
            }
        }
        Character[] array =new Character[s.size()];
        s.toArray(array);
        StringBuilder sb = new StringBuilder(array.length);
        for (Character c : array)
            sb.append(c.charValue());
        return sb.toString();

    }

    public static void main(String[] args){
        String t1 = DeleteRepeatedAC("aaabccc");
        System.out.println(t1);
        String t2 = DeleteRepeatedAC("aacbd");
        System.out.println(t2);

    }
}


