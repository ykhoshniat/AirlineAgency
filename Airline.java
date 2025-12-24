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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine().trim());
        String[] budget = br.readLine().trim().split("\\s+");
        minBudget = Integer.parseInt(budget[0]);
        maxBudget = Integer.parseInt(budget[1]);

        cities = Arrays.asList(br.readLine().trim().split("\\s+"));
        legs = cities.size() - 1;

        String[] stayTokens = br.readLine().trim().split("\\s+");
        minStay = new int[legs - 1];
        maxStay = new int[legs - 1];
        for (int i = 0; i < legs - 1; i++) {
            minStay[i] = Integer.parseInt(stayTokens[2 * i]);
            maxStay[i] = Integer.parseInt(stayTokens[2 * i + 1]);
        }

        List<Flight> allFlights = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] f = br.readLine().trim().split("\\s+");
            allFlights.add(new Flight(f[0], f[1], Integer.parseInt(f[2]), Integer.parseInt(f[3])));
        }

        // به جای آرایه، لیست تو در تو
        domains = new ArrayList<>();
        for (int i = 0; i < legs; i++) {
            domains.add(new ArrayList<>());
        }

        for (int i = 0; i < legs; i++) {
            String o = cities.get(i), d = cities.get(i + 1);
            for (Flight fl : allFlights) {
                if (fl.origin.equals(o) && fl.dest.equals(d)) domains.get(i).add(fl);
            }
            domains.get(i).sort(Comparator.comparingInt((Flight fl) -> fl.day).thenComparingInt(fl -> fl.price));
        }
            System.out.println();
        assignment = new Flight[legs];
        startTimeNs = System.nanoTime();
        backtrack(0, null, 0, domains.get(0));
        long elapsedMs = (System.nanoTime() - startTimeNs) / 1_000_000;

        if (!solutionFound) {
            System.out.println("No Solution");
        }
        System.out.println("Backtracks: " + nodesVisited);
        System.out.println("Runtime: " + elapsedMs + " ms");
    }
}