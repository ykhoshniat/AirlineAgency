import java.io.*;
import java.util.*;

public class Airline{

    static class Flight {
        String origin, dest;
        int day, price;
        Flight(String o, String d, int day, int price) {
            this.origin = o; this.dest = d; this.day = day; this.price = price;
        }
        @Override public String toString() {
            return origin + " " + dest + " " + day + " " + price;
        }
    }
    static int M;
    static int minBudget, maxBudget;
    static List<String> cities;
    static int legs;
    static int[] minStay, maxStay;
    static List<List<Flight>> domains;   // تغییر داده شد

    static Flight[] assignment;
    static boolean solutionFound = false;
    static long nodesVisited = 0;
    static long startTimeNs;
}