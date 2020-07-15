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

import edu.princeton.cs.algs4.*;

public class GuitarHeroLite {
    public static void main(String[] args) {
        int capacity = 37;
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] Guitar = new GuitarString[capacity];


        for (int i = 0; i < capacity; i++) {
            Guitar[i] = new GuitarString((440) * Math.pow(1.05956, i - 24));
        }

        while (true) {

            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.indexOf(key)!= -1) { // returns -1 if any other key not in the string is pressed
                    int i = keyboard.indexOf(key);
                    Guitar[i].pluck();
                }
            }

            double sample=0;
            for (int i = 0; i < capacity; i++) {
               sample = sample + Guitar[i].sample();
            }

            StdAudio.play(sample);
            for (int i = 0; i < capacity; i++) {
                Guitar[i].tic();
            }
        }
    }
}


