//Code By Tarun Ravi

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
  private static double close = .000001;
  private static int bisectionTrials = 0;
  private static int newtonTrials = 0;
  private static int secantTrials = 0;
  private static int equation = 0;

  public static void main(String args[]) {

    try {
      System.setOut(new PrintStream(new FileOutputStream("results.txt")));
    }
    catch (IOException e){
      System.out.println("error creating file");
    }
    System.out.println("Code by Tarun Ravi");

    print(1, "x^2 - 5x - 6", -4, 5);
    System.out.println();
    print(2, "x^3 + 4x^2 + 2", -8, 5);
    System.out.println();
    print(3, "3cos(x) - 2sin(x)", 0, 2);
    System.out.println();
    print(4, "e^x + x^2 - 5", 0, 2);
  }

  public static void print(int eq, String eqS, int a, int b) {
    bisectionTrials = 0;
    newtonTrials = 0;
    secantTrials = 0;
    equation = eq;
    System.out.println("For the equation " + eqS);
    System.out.println(
        "Using the bisection method, the root is on: ("
            + bisection(a, b)
            + ", 0) and it took "
            + bisectionTrials
            + " runs to calculate it.");
    System.out.println(
        "Using the newton method, the root is on: ("
            + newton(a)
            + ", 0) and it took "
            + newtonTrials
            + " runs to calculate it.");
    System.out.println(
        "Using the secant method, the root is on: ("
            + secant(a, b)
            + ", 0) and it took "
            + secantTrials
            + " runs to calculate it.");
  }

  public static double function(double x) {
    switch (equation) {
      case 1:
        return Math.pow(x, 2) - (5 * x) - 6; // x^2 -5x -6
      case 2:
        return Math.pow(x, 3) + 4 * (Math.pow(x, 2)) + 2; // x^3 +4x^2 +2
      case 3:
        return (3 * Math.cos(x)) - (2 * Math.sin(x)); // 3cos(x) - 2sin(x)
      case 4:
        return (Math.pow(Math.E, x)) + (Math.pow(x, 2)) - 5; // e^x + x^2 -5
      default:
        return Math.pow(x, 2) - (5 * x) - 6; // x^2 -5x -6
    }
  }

  public static double derivative(double x) {
    switch (equation) {
      case 1:
        return 2 * x - 5; // 2x-5
      case 2:
        return 3 * (Math.pow(x, 2)) + 8 * x; // 3x^2+8x
      case 3:
        return (-3 * Math.sin(x)) - (2 * Math.cos(x)); // -3sin(x) - 2cos(x)
      case 4:
        return (Math.pow(Math.E, x)) + 2 * x; // e^x + 2x
      default:
        return Math.pow(x, 2) - (5 * x) - 6; // x^2 -5x -6
    }
  }

  public static double bisection(double a, double b) {
    double midPoint = (a + b) / 2;
    /*
    This didn't work :(
    if (close >= y && y >= -close) {
      return midPoint;
    } else if (y > close) {
      return bisection(a, midPoint);
    } else {
      return bisection(midPoint, b);
    }*/
    while (Math.abs(function(midPoint)) > close) {
      bisectionTrials++;
      if (function(midPoint) * function(a) > 0) a = midPoint;
      else b = midPoint;
      midPoint = (a + b) / 2;
    }
    return midPoint;
  }

  public static double newton(double x0) {
    newtonTrials++;
    double x1 = x0 - (function(x0) / derivative(x0));
    double y = function(x1);
    if (close >= y && y >= -close) {
      return x1;
    } else {
      return newton(x1);
    }
  }

  public static double secant(double x1, double x2) {
    secantTrials++;
    double x0 = (x1 * function(x2) - x2 * function(x1)) / (function(x2) - function(x1));
    double y = function(x0);
    if (close >= y && y >= -close) {
      return x0;
    } else {
      return secant(x2, x0);
    }
  }
}
