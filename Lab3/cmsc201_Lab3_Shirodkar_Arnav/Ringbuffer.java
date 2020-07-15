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

import java.util.NoSuchElementException;

public class Ringbuffer{

    private int n = 0;
    private int max= 0;
    private int first = 0;
    private int last = 0;
    private double[] buffer;

    Ringbuffer(int capacity){
        buffer = new double[capacity];
        max = capacity;
    }

    int size(){
        if (n >= max);{
            n = max;
        }
        return n;
    }

    boolean isEmpty(){
        if (n == 0){
            return true;
        } else {
            return false;
        }
    }

    boolean isFull(){
        if (n == buffer.length){
            return true;
        } else {
            return false;
        }
    }

    void enqueue(double x) {
        if (n == max && first == last){  // move first and last together when overwriting
            buffer[last++] = x;
            first++;
            if(last == buffer.length){ last = 0; }
            if(first == buffer.length){ first = 0; }
        } else {
            buffer[last++] = x;
            if (last == buffer.length) {
                last = 0;
            }
        }

        if(!isFull()) {
            n++;
        } else {
            n = max;
        }
    }

    double dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue Underflow");
        }
        //buffer[first] = null cannot be written here - Loitering is an issue for an array filled with object
        n--;
        double exitValue = buffer[first];
        first++;
        if(first == buffer.length){
            first=0;
        }
        return exitValue;
    }

    double peek(){
        if(!isEmpty()){
            return buffer[first];
        } else {
            throw new NoSuchElementException("Queue Underflow");
        }
    }

    void print(){ // print method I used to check and debug the Ringbuffer
        for(int i=0;i<max;i++){
            System.out.println(buffer[i]);
        }
    }
}
