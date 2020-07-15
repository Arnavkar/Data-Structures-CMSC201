/* 

Arnav Shirodkar CMSC201:Data Structures

Email: as9086@bard.edu

Date: 13/02/2020

Assignment Description: Create an ADT for fractions using the BigInteger class, functions as described in th lab handout

Collaboration Statement: I worked on this file with Elliot and Marco, also approaching Michael Ventoso for help with the boolean
equals function. I frequently used the javadoc for BigInteger to explore the various functions associated with BigInteger. I also looked online 
for the various functions used for JUnit testing.

http://junit.sourceforge.net/javadoc/org/junit/Assert.html
https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html#equals(java.lang.Object)
*/

import java.math.BigInteger;

public class Fraction extends Number{

	private BigInteger numerator; 
	private BigInteger denominator;
	
	Fraction(BigInteger n, BigInteger d){ // Constructor that takes 2 big int arguements

        if (d.intValue() == 0){ 
            throw new IllegalArgumentException("/ by zero");
        }

        BigInteger divisor = n.gcd(d); //Simplify
        numerator = n.divide(divisor);
        denominator = d.divide(divisor);

        if(numerator.compareTo(BigInteger.ZERO) == -1 && denominator.compareTo(BigInteger.ZERO) == -1){ //Make positive if numerator and denominator are negative
            numerator = numerator.multiply(BigInteger.valueOf(-1));
            denominator = denominator.multiply(BigInteger.valueOf(-1));
        }
    }

    Fraction(int n, int d){ //Constructor for 2 int arguments

        if(d == 0){
            throw new IllegalArgumentException("/ by zero");
        } 
        BigInteger x = BigInteger.valueOf(n);
        BigInteger y = BigInteger.valueOf(d);

        BigInteger divisor = x.gcd(y);
        numerator = x.divide(divisor);
        denominator = y.divide(divisor);

        if(numerator.compareTo(BigInteger.ZERO) == -1 && denominator.compareTo(BigInteger.ZERO) == -1){
            numerator = numerator.multiply(BigInteger.valueOf(-1));
            denominator = denominator.multiply(BigInteger.valueOf(-1));
        }

    }

    Fraction(int n){ // constructor for 1 int argument
        numerator = BigInteger.valueOf(n);
        denominator = BigInteger.ONE;
    }

    Fraction(BigInteger n){ // constructor for 1 big in argument
        numerator = n ;
        denominator = BigInteger.ONE;
    }

    public BigInteger getNumerator(){
      return numerator;

    }

    public BigInteger getDenominator(){
      return denominator;

    }

    public Fraction add(Fraction x){ 
        BigInteger tempNum = numerator;
        BigInteger tempDen = denominator;
        numerator = (tempNum.multiply(x.getDenominator())).add (tempDen.multiply(x.getNumerator()));
        denominator = tempDen.multiply(x.getDenominator());
        return this.simplify(); //simplify fraction after the relevant operation
    }

    public Fraction subtract(Fraction x){
        BigInteger tempNum = numerator;
        BigInteger tempDen = denominator;
        numerator = (tempNum.multiply(x.getDenominator())).subtract(tempDen.multiply(x.getNumerator()));
        denominator = tempDen.multiply(x.getDenominator());
        return this.simplify(); //
    } 

    public Fraction multiply(Fraction x){
        numerator = numerator.multiply(x.getNumerator());
        denominator = denominator.multiply(x.getDenominator());
        return this.simplify();

    }

    public Fraction divide(Fraction x){
        numerator = numerator.multiply(x.getDenominator());
        denominator = denominator.multiply(x.getNumerator());
        return this.simplify();

    }

    public String toString(){ // convert to String value "x/y"
        if(denominator != BigInteger.ONE){
            String s = String.valueOf(numerator) + "/" + String.valueOf(denominator);
            return s;
        } else {
            String s = String.valueOf(numerator);
            return s;
        }
    }

    public long longValue(){ //convert to relevant type
        BigInteger x = numerator.divide(denominator);
        return x.longValue();
    }

    public int intValue(){
        BigInteger x = numerator.divide(denominator);
        return x.intValue();
    }

    public double doubleValue(){
     double x = numerator.doubleValue();
     double y = denominator.doubleValue();
     return x/y;
    }

    public float floatValue(){
     float x = numerator.floatValue();
     float y = denominator.floatValue();
     return x/y;
    }
        
    public boolean equals (Object o){ //check if object o equals to this fraction
        if (o.getClass() != this.getClass()){
            return false;
        }

        Fraction x = ((Fraction)o);  // whatever o is, it attempts to cast o to Fraction and if o is indeed a fraction, test will be true
        if(x.getNumerator().equals(numerator) == true && x.getDenominator().equals(denominator) == true){
            return true;
        } else  {
            return false;
        }
    }

    public Fraction simplify(){ // find GCD and simplify the fraction (also in the constructor)
        BigInteger divisor = numerator.gcd(denominator);
        numerator = numerator.divide(divisor);
        denominator = denominator.divide(divisor);

        if(numerator.compareTo(BigInteger.ZERO) == -1 && denominator.compareTo(BigInteger.ZERO) == -1){
            numerator = numerator.multiply(BigInteger.valueOf(-1));
            denominator = denominator.multiply(BigInteger.valueOf(-1));
            return this;
        } else if (numerator.compareTo(BigInteger.ZERO )== 0){
            return new Fraction(0);
        }
        return this;
    }

    public static Fraction valueOf(int n, int d){ //Used for quick values to avoid the client having to constantly create new Fractions.
        return new Fraction(n,d);
    }

}


