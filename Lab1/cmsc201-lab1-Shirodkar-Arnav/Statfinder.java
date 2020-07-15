/* 

Arnav Shirodkar CMSC201:Data Structures

Email: as9086@bard.edu

Date: 06/02/2020

Assignment Description: Using several data sets, explore Benford's law 
and create a program that returns relevant statistics from each data set.

Collaboration Statement: I worked on this file alone. I made this second file, Statfinder.java when adapting my
code to include the Welford method and I referred to https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Accumulator.java.html
The link prompted me to alter my code to be more object oriented.

*/

import edu.princeton.cs.algs4.*;

public class Statfinder{ 
    //must make these static to be referenced later
	static double count; //number of input values
	static double average; // mean value
    static double x; //variance * (n-1)
    static final double firstInput = StdIn.readDouble(); //Note: Reads the first input, next call of StdIn reads the second input onward!

    public Statfinder(){}

    public double mean(){
    	return average; 
    }	

    public double stdDev(){ 
    	return Math.sqrt(x/(count-1));
    }

    public double n(){
    	return count;
    }

    public static void main (String[] args){
    	Statfinder calc = new Statfinder();
    	double min = firstInput;
    	double max = firstInput;
            count = 1; //account for first number 
            average = firstInput/count; // starting average (which = to first)

            while (!StdIn.isEmpty()){
				double input = StdIn.readDouble(); //Note: Begins reading from 2nd value!
				count++;
				double temp = input - average; //Welford's method
				average += temp/count;
				x += (count-1)/count*temp*temp;

				if(input>max){
					max=input;
				}
				if(input<min){
					min=input;
				}
			}
			StdOut.printf("%10s %10s %10s %10s %23s","N","min","max","mean","Standard Deviation");
			StdOut.println();
			StdOut.println("--------------------------------------------------------------------");
			System.out.format("%10.0f %10.3f %10.3f %10.3f %10.3f ",calc.n(),min,max,calc.mean(),calc.stdDev());
			StdOut.println();

		} 
	}	