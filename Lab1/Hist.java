import edu.princeton.cs.algs4.*;

public class Hist{
    
    public static void main(String args[]){
	int nbins = 1;
	double min = 0;
	double max = 1;

	if (args.length > 0){
	    nbins = Integer.parseInt(args[0]);
	}

	if (args.length > 1){
	    min = Double.parseDouble(args[1]);
	    max = Double.parseDouble(args[2]);
	}
	
	int[] hist = new int[nbins];
	double width = (max - min) / (nbins);

	int N = 0;
	while (!StdIn.isEmpty()) {
	    double x = StdIn.readDouble();	    
	    int idx = (int)((x-min)/width);
	    if (idx < 0 || idx >= hist.length){
		StdOut.println("ERROR: OUT OF Bounds: " + x);
		return;
	    }
	    hist[idx]++;
	    N++;
	}
	
	for (int k = 0; k < hist.length; k++){
	    System.out.format("%-14.3f%s%14.3f:%10d ",
			      min+k*width, "-", min+(k+1)*width, hist[k]);
	    for (int l = 0; l < 60*hist[k]/N; l++){
		StdOut.print("*");
	    }
	    StdOut.println("");
	}       
    }    
}
