import java.util.Scanner;

abstract class Operations {
    public abstract double computeTValue(int[] preTest, int[] postTest);
    public abstract double computeMean(int[] preTest, int[] postTest);
    public abstract int[] computeDifference(int[] preTest, int[] postTest);
    public abstract int computeSummation(int[] difference);
    public abstract int computeSquaredSummation(int[] difference);
}

class Statistics extends Operations {
    public double computeTValue(int[] preTest, int[] postTest) {
        double tValue;
        int[] difference = computeDifference(preTest, postTest);
        int summation = computeSummation(difference);
        int squaredSummation = computeSquaredSummation(difference);

        double n = preTest.length;
        double denominator = Math.sqrt(((n * squaredSummation) - Math.pow(summation, 2)) / (n - 1));
        tValue = summation / denominator;
        return Math.round(tValue * 1000.0) / 1000.0;
    }

    public int[] computeDifference(int[] preTest, int[] postTest) {
        int[] difference = new int[preTest.length];
        for (int i = 0; i < preTest.length; i++) {
            difference[i] = preTest[i] - postTest[i];
        }
        return difference;
    }

    public int computeSummation(int[] difference) {
        int summation = 0;
        for (int i : difference) {
            summation += i;
        }
        return summation;
    }

    public int computeSquaredSummation(int[] difference) {
        int squaredSummation = 0;
        for (int i : difference) {
            squaredSummation += Math.pow(i, 2);
        }
        return squaredSummation;
    }

    public double computeMean(int[] preTest, int[] postTest) {
        double mean;
        int[] difference = computeDifference(preTest, postTest);
        double summation = computeSummation(difference);
        mean = summation / preTest.length;
        return Math.round(mean * 1000.0) / 1000.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Statistics statistics = new Statistics();
        int[] preTest = new int[15];
        int[] postTest = new int[15];

        System.out.println("Please input 15 values for the Pre-Test:");
        for (int i = 0; i < 14; i++) {
            System.out.print("Please Input Pre-Test Value #" + (i + 1) + ": ");
            preTest[i] = checkInput();
        }

        System.out.println();

        System.out.println("Please input 15 values for the Post-Test:");
        for (int i = 0; i < 14; i++) {
            System.out.print("Please Input Post-Test Value #" + (i + 1) + ": ");
            postTest[i] = checkInput();
        }

        System.out.println();

        System.out.println("The T-Value is: " + statistics.computeTValue(preTest, postTest));
    }

    public static int checkInput() {
        Scanner input = new Scanner(System.in);
        if(input.hasNextInt()) {
            return input.nextInt();
        } else {
            System.out.println("Error: Please enter a Valid Integer!");
            System.out.println("Please try again.");
            return checkInput();
        }
    }
}
