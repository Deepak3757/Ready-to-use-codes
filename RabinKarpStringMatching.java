class Solution {
    String text, pattern;
    int n, m;
    public int strStr(String text, String pattern) {
        this.text=text;
        this.pattern=pattern;
        this.n=text.length();
        this.m=pattern.length();
        if(m>n)
            return -1;

        int hashPat=0, hashText=0;
        int d=26, q=101, h=1;

        //d: Total no. of chars
        //q: A large prime no.
        //h: d^m-1(computed below)

        for(int i=0; i<m-1; i++)
            h=(h*26)%q;

        for(int i=0; i<m; i++){
            hashPat=(d*hashPat+(pattern.charAt(i)-'a'))%q;
            hashText=(d*hashText+(text.charAt(i)-'a'))%q;
        }

        for(int i=0; i<n-m+1; i++){
            if(hashPat==hashText){
                if(areEqual(i))
                    return i;
            }
            
            //Avoid when mth char exceeds text range(occurs in the last pattern)
            if(i+m<n){
                hashText=(hashText-(text.charAt(i)-'a')*h)%q;
                if(hashText<0) 
                    hashText+=q;
                hashText=(hashText*d)%q+text.charAt(i+m)-'a';
            }
        }
        return -1;
    }
    public boolean areEqual(int i){
        for(int j=0; j<pattern.length(); j++){
            if(text.charAt(i++)!=pattern.charAt(j))
                return false;
        }
        return true;
    }
}
