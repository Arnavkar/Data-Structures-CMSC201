package cmsc201_Lab3_Shirodkar_Arnav;/*

Arnav Shirodkar CMSC201:Data Structures

Email: as9086@bard.edu

Date: 20/02/2020

Assignment Description: Create a class Ringbuffer that creates a circular queue. Using the ring buffer create 2 additional
classes GuitarString and GuitarHeroLite that use the Ringbuffer as well as the Karplus-Strong update to create 2 octaves
a guitar audio output.

Collaboration Statement: I worked on this file with Mason and Tina, getting further insight into how to debug my
Ringbuffer class and GuitarHeroLite class from Mason and Tina respectively

*/

public class GuitarString {
     private int n;
     private Ringbuffer joe;
     private int count;

    GuitarString(double Frequency){
        n = (int)(44100/Frequency);
        joe = new Ringbuffer (n);
        for(int i =0; i<= n ; i++) {
            joe.enqueue(0);
        }
    }

    void pluck(){
       for(int i =0; i<=n ; i++){
           double white = (Math.random() - 0.5);
           joe.enqueue(white);
       }
       count++;
    }

    void tic(){
        double firstVal = joe.dequeue();
        double secondVal = joe.peek();
        double newValue = 0.994 *((firstVal+secondVal)/2);
        joe.enqueue(newValue);
        count++;
    }

    double sample(){
        return joe.peek();
    }

    int time(){
        return count;
    }
}

