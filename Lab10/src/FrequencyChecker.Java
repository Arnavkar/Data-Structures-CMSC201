import edu.princeton.cs.algs4.*;
public class FrequencyChecker{
	private FrequencyChecker(){}

	public static void main(String[] args){
		int distinct = 0, hashes = 0;
		
		SelfOrganizingST<Integer, Integer> st = new SelfOrganizingST<>();

		while (!StdIn.isEmpty()) {
            int hash = Integer.parseInt();
            if (st.contains(hash)) {
                st.put(hash, st.get(hash) + 1);
            } else {
                st.put(hash, 1);
            }
        }

        StdOut.println(timer.elapsedTime()); // find elapsed time after the linked list has been fully constructed

        distinct = st.size(); //Checks for distinct words by calling st.size rather than adding as a counter
        StdOut.println("distinct = " + distinct);

        StdOut.println();
        st.mergesort(); //use mergesort to sort the linked list

        int count = 0;
        for (String word : st.keys()) {
           StdOut.println(word + "/" + st.get(word));
            count++;
            if (count == 50) break; // print the top 50 words
        }
    }
}
	

