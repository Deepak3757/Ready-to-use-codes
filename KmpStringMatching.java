class Solution {
    String text, pat;
    public int findMatching(String text, String pat) {
        // Code here
        this.text=text;
        this.pat=pat;
        int[] pi=new int[pat.length()];
        computePrefixFunction(pi);
        return findMatch(pi);
    }
    public void computePrefixFunction(int[] pi){
        int len=0, i=1;
        pi[0]=0;
        while(i<pat.length()){
            if(pat.charAt(len)==pat.charAt(i)){
                len++;
                pi[i]=len;
                i++;
            }
            else{
                if(len!=0){
                    len=pi[len-1];
                }
                else{
                    pi[i]=0;
                    i++;
                }
            }
        }
    }
    public int findMatch(int[] pi){
        int j=0;
        for(int i=0; i<text.length(); i++){
            while(j>0&&text.charAt(i)!=pat.charAt(j)){
                    j=pi[j-1];
                }
            if(text.charAt(i)==pat.charAt(j)){
                j++;
            }
            if(j==pat.length())
                return i-j+1;
        }
        return -1;
    }
}
