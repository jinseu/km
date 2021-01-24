class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> magaDict = new HashMap<Character,Integer>();
        for (Character m : magazine.toCharArray()) {
            Integer cnt = magaDict.get(m);
            if (cnt == null) {
                cnt = 0;
            }
            magaDict.put(m, cnt + 1);
        }
        for (Character r : ransomNote.toCharArray()){
            Integer cnt = magaDict.get(r);
            if (cnt == null || cnt <= 0) {
                return false;
            } else {
                magaDict.put(r, cnt - 1);
            }
        }
        return true;
    }
}