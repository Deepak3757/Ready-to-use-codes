import java.util.*;
class MinHeapAllOperations{
    public static void main(String[] args) {
        //Implementation of min heap operations

        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0; i<args.length; i++)
            arr.add(Integer.parseInt(args[i]));

        //To convert min heap construction to max heap, do the changes mentioned in the construct function
        //1: construct min heap
        construct(arr);
        System.out.println(arr);

        // 2: Insert
        insert(6, arr);
        System.out.println("After insertion: "+" "+arr);

        //3: get min
        System.out.println("Min element: "+ arr.get(0));

        //4: extract min
        int del=extractMin(arr);
        System.out.println(String.format("List after extracting min elem %d: ", del)+" "+arr);

        //5: delete(given ptr.)
        delete(arr, 4);
        System.out.println(String.format("List after deleting at index %d: ", 4)+" "+arr);
        
        //6: key increment(given ptr.)
        keyIncrement(arr, 1, 5);
        System.out.println(String.format("List after incrementing elem at index %d by %d: ", 1, 5)+" "+arr);

        //7: key decrement(given ptr.)
        keyDecrement(arr, 3, 5);
        System.out.println(String.format("List after decrementing elem at index %d by %d: ", 3, 5)+" "+arr);
    }

    //we can also construct by calling minHeapify operation for all the internal nodes    
    public static void construct(ArrayList<Integer> arr){
        int n=arr.size();
        int i=n/2-1;
        while(i>=0){
            int ind=i, elem=arr.get(i), left=2*i+1, right=2*i+2;
            while(left<n){
                if(right<n){
                    if(elem>Math.min(arr.get(left), arr.get(right))){ //for max heap: min->max and > -> <
                        
                        if(arr.get(left)<arr.get(right)){ //for max heap: < -> >
                            arr.set(ind, arr.get(left));
                            ind=left;
                        }
                        else{
                            arr.set(ind, arr.get(right));
                            ind=right;
                        }
                    }
                    else
                        break;
                }
                else{
                    //for max heap: > -> <
                    if(elem>arr.get(left)){
                        arr.set(ind, arr.get(left));
                        ind=left;
                    }
                    else
                        break;
                }
                left=2*ind+1;
                right=2*ind+2;
            }
            arr.set(ind, elem);
            i--;
        }
    }
    /*
    Array version
    public static void construct(int[] arr){
        int n=arr.length;
        int i=n/2-1;
        while(i>=0){
            int ind=i, elem=arr[i], left=2*i+1, right=2*i+2;
            while(left<n){
                if(right<n){
                    if(elem>Math.min(arr[left], arr[right])){ //for max heap: min->max and > -> <
                        
                        if(arr[left]<arr[right]){ //for max heap: < -> >
                            arr[ind]=arr[left];
                            ind=left;
                        }
                        else{
                            arr[ind]=arr[right];
                            ind=right;
                        }
                    }
                    else
                        break;
                }
                else{
                    //for max heap: > -> <
                    if(elem>arr[left]){
                        arr[ind]=arr[left];
                        ind=left;
                    }
                    else
                        break;
                }
                left=2*ind+1;
                right=2*ind+2;
            }
            arr[ind]=elem;
            i--;
        }
    }
    */
    public static void insert(int elem, ArrayList<Integer> arr){
        arr.add(elem);
        int ind=arr.size()-1, parent;
        while(ind>0){
            parent=(ind-1)/2;
            if(arr.get(parent)>elem){
                arr.set(ind, arr.get(parent));
                ind=parent;
            }
            else
                break;
        }
        arr.set(ind, elem);
    }
    public static int extractMin(ArrayList<Integer> arr){
        int elem=arr.get(0);
        
        arr.set(0, arr.remove(arr.size()-1));
        minHeapify(arr, 0);
        
        return elem;
    }
    //Min heapify is nothing but construction code when ind=0
    public static void minHeapify(ArrayList<Integer> arr, int pos){
        int ind=pos, elem=arr.get(pos), left=2*ind+1, right=2*ind+2, n=arr.size();
        while(left<n){
            if(right<n){
                if(elem>Math.min(arr.get(left), arr.get(right))){
                    if(arr.get(left)<arr.get(right)){
                        arr.set(ind, arr.get(left));
                        ind=left;
                    }
                    else{
                        arr.set(ind, arr.get(right));
                        ind=right;
                    }
                }
                else
                    break;
            }
            else{
                if(elem>arr.get(left)){
                    arr.set(ind, arr.get(left));
                    ind=left;
                }
                else
                    break;
            }
            left=2*ind+1;
            right=2*ind+2;
        }
        arr.set(ind, elem);
    }
    //Note: delete need not to call minHeapify always, either of upsifting or downsifting will be required
    public static int delete(ArrayList<Integer> arr, int pos){
        int temp=arr.get(arr.size()-1);
        arr.remove(arr.size()-1);
        if(arr.get(pos)<=temp){
            arr.set(pos, temp);
            minHeapify(arr, pos);    
        }
        else{
            keyDecrement(arr, pos, arr.get(pos)-temp);
        }
        return temp;
    }
    public static void keyIncrement(ArrayList<Integer> arr, int pos, int howMuch){
        arr.set(pos, arr.get(pos)+howMuch);
        minHeapify(arr, pos);
    }
    //key decrement means sifting up so we can reuse the code of insertion
    public static void keyDecrement(ArrayList<Integer> arr, int pos, int howMuch){
        arr.set(pos, arr.get(pos)-howMuch);
        int elem=arr.get(pos);
        int ind=pos, parent;
        while(ind>0){
            parent=(ind-1)/2;
            if(arr.get(parent)>elem){
                arr.set(ind, arr.get(parent));
                ind=parent;
            }
            else
                break;
        }
        arr.set(ind, elem);
    }
}