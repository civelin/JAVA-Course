import java.util.Scanner;

public class Main {

    public static int enterNumberOfRows() {
        Scanner scan = new Scanner(System.in);
        int rows = 0;
        while (rows <= 0) {
            System.out.println("Enter the number of rows of the matrix: ");
            rows = scan.nextInt();
        }
        return rows;
    }

    public static int enterNumberOfCols() {
        Scanner scan = new Scanner(System.in);
        int cols = 0;
        while (cols <= 0) {
            System.out.println("Enter the number of columns of the matrix: ");
            cols = scan.nextInt();
        }
        return cols;
    }

    public static int[][] enterMatrix() {
        Scanner scan = new Scanner(System.in);
        int numberOfRows = enterNumberOfRows();
        int numberOfCols = enterNumberOfCols();
        int[][] matrix = new int[numberOfRows][numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                System.out.print("Enter element on index (" + i + ", " + j + "): ");
                matrix[i][j] = scan.nextInt();
                //TODO: check if the entered number is an integer one
            }
        }
        return matrix;
    }

    public static int[] getTheLengthOfTheLongestElemInEachColumn(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxElemLength = 0;
        int[] registerOfTheLongestElemInEachColumn = new int[cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                String currentElemAsString = matrix[j][i] + "";
                if (maxElemLength < currentElemAsString.length()) {
                    maxElemLength = currentElemAsString.length();
                }
            }
            registerOfTheLongestElemInEachColumn[i] = maxElemLength;
            maxElemLength = 0;
        }
        return registerOfTheLongestElemInEachColumn;
    }

    public static void printMatrix(int[][] matrix) {
        int[] register = getTheLengthOfTheLongestElemInEachColumn(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int currentElemLength = (matrix[i][j] + "").length();
                System.out.print(matrix[i][j] + " ".repeat((register[j] - currentElemLength) + 2));
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] matrix = enterMatrix();
        printMatrix(matrix);

    }
}