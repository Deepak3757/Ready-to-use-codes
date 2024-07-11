import java.util.*;
class SortArrayListOfPairs{
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> al1=new ArrayList<Integer>();
		al1.add(5);
		al1.add(1);
		ArrayList<Integer> al2=new ArrayList<Integer>();
		al2.add(1);
		al2.add(4);
		ArrayList<Integer> al3=new ArrayList<Integer>();
		al3.add(3);
		al3.add(2);

		al.add(al1);
		al.add(al2);
		al.add(al3);
		Collections.sort(al, new Comparator<ArrayList<Integer>>(){
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2){
				return o1.get(0).compareTo(o2.get(0));
			}
		});
		System.out.println(al);
	}
}