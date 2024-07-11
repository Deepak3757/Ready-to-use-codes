import java.util.*;
class DisjointSet{
    ArrayList<Integer> parent;
    ArrayList<Integer> rank;
    ArrayList<Integer> size;
    
    DisjointSet(int nodes){
        rank=new ArrayList<>();
        size=new ArrayList<>();
        parent=new ArrayList<>();

        //All ranks are initally 0
        //Initially each node is its parent.
        //⭐️Note: set below <= or < nodes as per the suitability
        for(int i=0; i<=nodes; i++){
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
            
    }
    //Finding ultimate parent
    public int findUParent(int x){
        if(x==parent.get(x))
            return x;
        int ulp=findUParent(parent.get(x));
        parent.set(x, ulp);
        return parent.get(x);
    }

    //Perform union by rank
    public void unionByRank(int u, int v){
        int u_up=findUParent(u);
        int v_up=findUParent(v);
        if(u_up==v_up)
            return;
        if(rank.get(u_up)>rank.get(v_up))
            parent.set(v_up, u_up);
        else if(rank.get(u_up)<rank.get(v_up))
            parent.set(u_up, v_up);
        else{
            parent.set(v_up, u_up);//We can also do vice-versa then set rank accordingly
            rank.set(u_up, rank.get(u_up)+1);
        }
    }

    //Perform union by size(size means no of nodes)
    public void unionBySize(int u, int v){
        int u_up=findUParent(u);
        int v_up=findUParent(v);
        if(u_up==v_up)
            return;
        if(size.get(u_up)<size.get(v_up)){
            parent.set(u_up, v_up);
            size.set(v_up, size.get(u_up)+size.get(v_up));
        }
        else{
            parent.set(v_up, u_up);
            size.set(u_up, size.get(u_up)+size.get(v_up));
        }
    }
}
class DisjointSetUnion{
    public static void main(String[] args) {
        DisjointSet ds=new DisjointSet(7);    
        ds.unionBySize(1,2);   
        ds.unionBySize(2,3);   
        ds.unionBySize(4,5);   
        ds.unionBySize(6,7);   
        ds.unionBySize(5,6);   
        for(int i=1; i<7; i++){
            System.out.println(i+" parent: "+ds.findUParent(i));
        }
    }
}