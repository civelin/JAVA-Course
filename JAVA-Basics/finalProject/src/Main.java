import java.util.Scanner;

public class Main {

    public static int enterNumberOfRows() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of rows of the matrix: ");
        int rows = validateMatrixSizeInput(scan);
        return rows;
    }

    public static int enterNumberOfCols() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of columns of the matrix: ");
        int cols = validateMatrixSizeInput(scan);
        return cols;
    }

    private static int validateMatrixSizeInput(Scanner scan) {
        while (true) {
            try {
                int input = Integer.parseInt(scan.nextLine());
                if (input <= 0) {
                    System.out.println("Invalid input. The size must be a positive integer number");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input. The size must be a positive integer number");
                continue;
            }
        }
    }

    private static int validateMatrixElements(Scanner scan) {
        while (true) {
            try {
                int input = Integer.parseInt(scan.nextLine());
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input. The element must be a integer number");
                continue;
            }
        }
    }


    public static int[][] enterMatrix() {
        Scanner scan = new Scanner(System.in);
        int numberOfRows = enterNumberOfRows();
        int numberOfCols = enterNumberOfCols();
        int[][] matrix = new int[numberOfRows][numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                System.out.print("Enter integer on index (" + i + ", " + j + "): ");
                matrix[i][j] = validateMatrixElements(scan);
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

    public static int[][] sumTwoMatrices() {
        int[][] matrix1 = enterMatrix();
        int[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        int rowsMatrix2 = matrix2.length;
        int colsMatrix2 = matrix2[0].length;
        int[][] resultMatrix = new int[rowsMatrix1][colsMatrix1];
        if (rowsMatrix1 != rowsMatrix2 || colsMatrix1 != colsMatrix2) {
            System.out.println("Invalid matrices' size. The size of the matrices must be equal.");
            return sumTwoMatrices();
        } else {
            for (int i = 0; i < rowsMatrix1; i++) {
                for (int j = 0; j < colsMatrix1; j++) {
                    resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        }
        return resultMatrix;
    }

    public static int[][] subtractionTwoMatrices() {
        int[][] matrix1 = enterMatrix();
        int[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        int rowsMatrix2 = matrix2.length;
        int colsMatrix2 = matrix2[0].length;
        int[][] resultMatrix = new int[rowsMatrix1][colsMatrix1];
        if (rowsMatrix1 == rowsMatrix2 && colsMatrix1 == colsMatrix2) {
            for (int i = 0; i < rowsMatrix1; i++) {
                for (int j = 0; j < colsMatrix1; j++) {
                    resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
        } else {
            System.out.println("Invalid matrices' size. The size of the matrices must be equal.");
            return subtractionTwoMatrices();
        }
        return resultMatrix;
    }


    public static int multiplyCell(int[][] matrix1, int[][] matrix2, int row, int col) {
        int cellResult = 0;
        for (int i = 0; i < matrix2.length; i++) {
            cellResult += matrix1[row][i] * matrix2[i][col];
        }
        return cellResult;
    }

    public static int[][] multiplyTwoMatrices() {
        int[][] matrix1 = enterMatrix();
        int[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix2 = matrix2[0].length;
        if (rowsMatrix1 == colsMatrix2) {
            int[][] resultMatrix = new int[rowsMatrix1][colsMatrix2];
            for (int i = 0; i < resultMatrix.length; i++) {
                for (int j = 0; j < resultMatrix[i].length; j++) {
                    resultMatrix[i][j] = multiplyCell(matrix1, matrix2, i, j);
                }
            }
            return resultMatrix;
        } else {
            System.out.println("Invalid matrices' sizes. The number of rows of first matrix must be equal to number of columns of second matrix.");
            return multiplyTwoMatrices();
        }
    }

    public static int[][] findCofactorMatrix(int[][] matrix, int row, int col) {
        int length = matrix.length;
        int[][] newMatrix = new int[length - 1][length - 1];
        int newMatrixCurrRow = 0;
        int newMatrixCurrCol = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != row && j != col) {
                    newMatrix[newMatrixCurrRow][newMatrixCurrCol] = matrix[i][j];
                    newMatrixCurrCol++;
                }
            }
            if (newMatrixCurrCol == length - 1) {
                newMatrixCurrCol = 0;
                newMatrixCurrRow++;
            }
        }
        return newMatrix;
    }

    public static int findDeterminant(int[][] matrix) {
        int det = 0;
        int length = matrix.length;
        if (length == 1) {
            det = matrix[0][0];
            return det;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int[][] cofactorMatrix = findCofactorMatrix(matrix, 0, j);
                det += Math.pow(-1, (i + j)) * matrix[i][j] * findDeterminant(cofactorMatrix);
            }
        }
        return det;
    }

    public static int[][] findTransposeMatrix(int[][] matrix) {
        int length = matrix.length;
        int[][] transposeMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                transposeMatrix[i][j] = matrix[j][i];
            }
        }
        return transposeMatrix;
    }

    public static double[][] findInverseMatrix() {
        int[][] matrix = enterMatrix();
        if (matrix.length == matrix[0].length) {
            int det = findDeterminant(matrix);
            if (det == 0) {
                System.out.println("The determinant is equal to 0. The matrix has no equal matrix");
                System.out.println("Enter another matrix.");
                return findInverseMatrix();
            } else {
                double[][] inverseMatrix = new double[matrix.length][matrix[0].length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        int[][] transposeMatrix = findTransposeMatrix(matrix);
                        int[][] cofactorMatrix = findCofactorMatrix(transposeMatrix, i, j);
                        int cofactorMatrixDet = findDeterminant(cofactorMatrix);
                        inverseMatrix[i][j] = cofactorMatrixDet * Math.pow(-1, (i + j)) * 1 / det;
                    }
                }
                return inverseMatrix;
            }
        } else {
            System.out.println("Invalid matrices' size. The size of the matrices must be equal.");
            return findInverseMatrix();
        }
    }

    public static void chooseFromMenu() {
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice) {
            case 0:
                return;
            case 1:
                System.out.println();
                int[][] sumMatrix = sumTwoMatrices();
                printMatrix(sumMatrix);
                break;
            case 2:
                System.out.println();
                int[][] subsMatrix = subtractionTwoMatrices();
                printMatrix(subsMatrix);
                break;
            case 3:
                System.out.println();
                int[][] resultMatrix = multiplyTwoMatrices();
                printMatrix(resultMatrix);
                break;
            case 4:
                System.out.println();
                int[][] matrix = enterMatrix();
                while (matrix.length != matrix[0].length) {
                    System.out.println("The matrix must be a square one");
                    matrix = enterMatrix();
                }
                int determinant = findDeterminant(matrix);
                System.out.println("Determinant of the entered matrix is " + determinant);
                break;
            case 5:
                double[][] inverseMatrix = findInverseMatrix();
                for (int i = 0; i < inverseMatrix.length; i++) {
                    for (int j = 0; j < inverseMatrix[0].length; j++) {
                        System.out.println(inverseMatrix[i][j]);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
                System.out.print("Choose an option from the menu (1-5) - ");
                chooseFromMenu();
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println("---> 1.Sum two matrices");
        System.out.println("---> 2.Subtraction of two matrices");
        System.out.println("---> 3.Multiplication of two matrices");
        System.out.println("---> 4.Find a determinant of a matrix");
        System.out.println("---> 5.Inverse a matrix");
        System.out.println("---> Enter 0 to return");
        System.out.print("Choose an option from the menu (1-5) - ");
        chooseFromMenu();

    }

}