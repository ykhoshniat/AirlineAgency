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
}