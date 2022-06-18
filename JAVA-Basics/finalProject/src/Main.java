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
                int currElem = scan.nextInt();
                while(currElem != (int) currElem){
                    System.out.println("You must enter an integer");
                    System.out.print("Enter element on index (" + i + ", " + j + "): ");
                    currElem = scan.nextInt();
                }
                matrix[i][j] = currElem;
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

    public static int[][] sumTwoMatrices(){
        int[][] matrix1 = enterMatrix();
        int[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        int rowsMatrix2 = matrix2.length;
        int colsMatrix2 = matrix2[0].length;
        int[][] resultMatrix = new int[rowsMatrix1][colsMatrix1];
        if(rowsMatrix1 == rowsMatrix2 && colsMatrix1 == colsMatrix2){
            for (int i = 0; i < rowsMatrix1; i++) {
                for (int j = 0; j < colsMatrix1; j++) {
                    resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        } else{
            System.out.println("Invalid matrices' size. The size of the matrices must be equal.");
            return sumTwoMatrices();
        }
        return resultMatrix;
    }

    public static int[][] subtractionTwoMatrices(){
        int[][] matrix1 = enterMatrix();
        int[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        int rowsMatrix2 = matrix2.length;
        int colsMatrix2 = matrix2[0].length;
        int[][] resultMatrix = new int[rowsMatrix1][colsMatrix1];
        if(rowsMatrix1 == rowsMatrix2 && colsMatrix1 == colsMatrix2){
            for (int i = 0; i < rowsMatrix1; i++) {
                for (int j = 0; j < colsMatrix1; j++) {
                    resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
        } else{
            System.out.println("Invalid matrices' size. The size of the matrices must be equal.");
            return subtractionTwoMatrices();
        }
        return resultMatrix;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("-> 1.Sum two matrices");
        System.out.println("-> 2.Subtraction of two matrices");
        System.out.println("-> 3.Multiplication of two matrices");
        System.out.println("-> 4.Find a determinant of a matrix");
        System.out.println("-> 5.Inverse a matrix");
        System.out.print("Choose an option from the menu (1-5) - ");
        int choice = scan.nextInt();

        while(choice >= 6 || choice <= 0){
            System.out.println("Invalid choice!");
            System.out.print("Choose an option from the menu (1-5) - ");
            choice = scan.nextInt();
        }
        switch (choice){
            case 1:
                int[][] sumMatrix = sumTwoMatrices(); printMatrix(sumMatrix); break;
            case 2:
                int[][] subsMatrix = subtractionTwoMatrices(); printMatrix(subsMatrix);break;
            case 3: break;
            case 4: break;
            case 5: break;
        }



    }
}