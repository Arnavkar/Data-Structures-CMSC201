import edu.princeton.cs.algs4.*;

public class HistG{

    private static int map (int value, int low1, int high1, int low2, int high2){
	return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
    }
    
    public static void main(String args[]){
	int nbins = 1;
	double min = 0;
	double max = 1;

	if (args.length > 0){
	    nbins = Integer.parseInt(args[0]);
	}

	int[] hist = new int[nbins];
	double[] data = StdIn.readAllDoubles();
	
	if (args.length > 1){
	    min = Double.parseDouble(args[1]);
	    max = Double.parseDouble(args[2]);
	}else{
	    min = StdStats.min(data);
	    max = StdStats.max(data) + 1;
	}
	double width = (max - min) / nbins;
	
	int N = 0;
	for (Double x: data) {
	    int idx = (int)((x-min)/width);
	    if (idx < 0 || idx >= hist.length){
		StdOut.println("ERROR: OUT OF Bounds: " + x);
		return;
	    }
	    hist[idx]++;
	    N++;
	}

	int maxb = 0;
	int minb = Integer.MAX_VALUE;
	for (int v: hist){
	    if (v > maxb) maxb = v;
	    if (v < minb) minb = v;
	}
	
	for (int k = 0; k < hist.length; k++){
	    System.out.format("%-14.3f%s%14.3f:%10d ",
			      min+k*width, "-", min+(k+1)*width, hist[k]);
	    
	    int s = 60*hist[k]/N; //map(hist[k], minb, maxb, 0, 30);
	    for (int l = 0; l < s; l++){
		StdOut.print("*");
	    }
	    StdOut.println("");
	}

	StdDraw.setCanvasSize(800, 400);
	StdDraw.setXscale(0, 3*hist.length);
        StdDraw.setYscale(0, maxb*1.75);
	
        for (int m = 0; m < hist.length; m++){
            StdDraw.filledRectangle(m*3 + 1,hist[m]/2, 1.5, hist[m]/2);
	}
    }    
}
