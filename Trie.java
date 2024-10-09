//Trie implementation to find longest prefix of strings
class Trie{
    class Node{
        HashMap<Character, Node> hm;
        boolean flag;
        Node(){
            hm=new HashMap<>();
            flag=false;
        }
    }
    Node root;
    Trie(){
        root=new Node();
    }
    public void insert(String str){
        Node ptr=root;
        for(int i=0; i<str.length(); i++){
            if(!ptr.hm.containsKey(str.charAt(i))){
                Node temp=new Node();
                ptr.hm.put(str.charAt(i), temp);
                ptr=temp;
            }
            else
                ptr=ptr.hm.get(str.charAt(i));  
        }
        ptr.flag=true;
    }
    public String findLongestPrefix(String ref){
        Node ptr=root;
        int i=0;
        while(i<ref.length()&&!ptr.flag&&ptr.hm.size()==1)
            ptr=ptr.hm.get(ref.charAt(i++));
        return ref.substring(0, i);
    }
}
