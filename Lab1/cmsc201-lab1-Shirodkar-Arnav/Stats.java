/* 

Arnav Shirodkar CMSC201:Data Structures

Email: as9086@bard.edu

Date: 06/02/2020

Assignment Description: Using several data sets, explore Benford's law 
and create a program that returns relevant statistics from each data set.

Collaboration Statement: I worked on this with Marco. I made a second
file, Statfinder.java while trying to include the Welford method and I 
referred to https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Accumulator.java.html
for the Statfinder.java file


*/

import edu.princeton.cs.algs4.*;

public class Stats{

	public static void main (String[] args){
		double count = 0; //number of input values
		double sum = 0.0; // sum of input values
		double sum_sqs = 0.0; //variance*(n-1)

		//Initialise Min and Max as the first values read

        double min=StdIn.readDouble(); //Note: Reads the first input, next call of StdIn reads the second input onward!
        double max = min;
        double first = min;
		//Read and compute various stats
        while (!StdIn.isEmpty()){
			//Average  Standard Deviation Stats
			double temp = StdIn.readDouble(); //Note: Begins reading from 2nd value!
			sum += temp;
			sum_sqs += (temp*temp);
			count++;
			//Max and Min
			if(temp > max){
				max = temp;
			} 

			if(temp < min){
				min = temp;
			}
		}

		//Account for 1st value not included
		sum = sum + first;
		count++;
		//calculate stats
		double average = sum/count;
		double variance_sqs = (sum_sqs - ((sum*sum)/count))/count;
		double variance = Math.sqrt(variance_sqs);

		//convert to Scientific Notation or Not
		StdOut.printf("%10s %10s %10s %10s %23s","N","min","max","mean","Standard Deviation");
		StdOut.println();
		StdOut.println("--------------------------------------------------------------------");
		System.out.format("%10.0f %10.3f %10.3f %10.3f %10.3f ",count,min,max,average,variance);
		StdOut.println();

	} 
}