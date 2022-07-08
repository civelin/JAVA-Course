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
                    System.out.print("Enter the size again - ");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input. The size must be a positive integer number");
                System.out.print("Enter the size again - ");
            }
        }
    }

    private static double validateMatrixElements(Scanner scan) {
        while (true) {
            try {
                double input = Double.parseDouble(scan.nextLine());
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input. The element must be a number");
            }
        }
    }


    public static double[][] enterMatrix() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a matrix: ");
        int numberOfRows = enterNumberOfRows();
        int numberOfCols = enterNumberOfCols();
        double[][] matrix = new double[numberOfRows][numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                System.out.print("Enter integer on index (" + i + ", " + j + "): ");
                matrix[i][j] = validateMatrixElements(scan);
            }
        }
        return matrix;
    }

    public static int[] getTheLengthOfTheLongestElemInEachColumn(double[][] matrix) {
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

    public static void printMatrix(double[][] matrix) {
        int[] register = getTheLengthOfTheLongestElemInEachColumn(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int currentElemLength = (matrix[i][j] + "").length();
                System.out.print(matrix[i][j] + " ".repeat((register[j] - currentElemLength) + 2));
            }
            System.out.println();
        }
    }

    public static boolean haveEqualSizes(double[][] matrix1, double[][] matrix2) {
        int matrix1Rows = matrix1.length;
        int matrix1Cols = matrix1[0].length;
        int matrix2Rows = matrix2.length;
        int matrix2Cols = matrix2[0].length;
        return (matrix1Rows == matrix2Rows && matrix1Cols == matrix2Cols);
    }

    public static double[][] sumTwoMatrices() {
        double[][] matrix1 = enterMatrix();
        double[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        double[][] resultMatrix = new double[rowsMatrix1][colsMatrix1];
        if (haveEqualSizes(matrix1, matrix2)) {
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

    public static double[][] subtractionTwoMatrices() {
        double[][] matrix1 = enterMatrix();
        double[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix1 = matrix1[0].length;
        double[][] resultMatrix = new double[rowsMatrix1][colsMatrix1];
        if (haveEqualSizes(matrix1, matrix2)) {
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


    public static int multiplyCell(double[][] matrix1, double[][] matrix2, int row, int col) {
        int cellResult = 0;
        for (int i = 0; i < matrix2.length; i++) {
            cellResult += matrix1[row][i] * matrix2[i][col];
        }
        return cellResult;
    }

    public static double[][] multiplyTwoMatrices() {
        double[][] matrix1 = enterMatrix();
        double[][] matrix2 = enterMatrix();
        int rowsMatrix1 = matrix1.length;
        int colsMatrix2 = matrix2[0].length;
        if (rowsMatrix1 == colsMatrix2) {
            double[][] resultMatrix = new double[rowsMatrix1][colsMatrix2];
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

    public static double[][] findCofactorMatrix(double[][] matrix, int row, int col) {
        int length = matrix.length;
        double[][] newMatrix = new double[length - 1][length - 1];
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

    public static double findDeterminant(double[][] matrix) {
        double det = 0;
        int length = matrix.length;
        if (length == 1) {
            det = matrix[0][0];
            return det;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                double[][] cofactorMatrix = findCofactorMatrix(matrix, 0, j);
                det += Math.pow(-1, (i + j)) * matrix[i][j] * findDeterminant(cofactorMatrix);
            }
        }
        return det;
    }

    public static double[][] findTransposeMatrix(double[][] matrix) {
        int length = matrix.length;
        double[][] transposeMatrix = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                transposeMatrix[i][j] = matrix[j][i];
            }
        }
        return transposeMatrix;
    }

    public static double[][] findInverseMatrix() {
        double[][] matrix = enterMatrix();
        if (matrix.length == matrix[0].length) {
            double det = findDeterminant(matrix);
            if (det == 0) {
                System.out.println("The determinant is equal to 0. The matrix has no equal matrix");
                System.out.println("Enter another matrix.");
                return findInverseMatrix();
            } else {
                double[][] inverseMatrix = new double[matrix.length][matrix[0].length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        double[][] transposeMatrix = findTransposeMatrix(matrix);
                        double[][] cofactorMatrix = findCofactorMatrix(transposeMatrix, i, j);
                        double cofactorMatrixDet = findDeterminant(cofactorMatrix);
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

    public static void canMatrixBeReducedToIdentityMatrix() {
        double[][] matrix = enterMatrix();
        double det = findDeterminant(matrix);
        if (det != 0) {
            System.out.println("The matrix can be reduced to the identity matrix");
        } else {
            System.out.println("The matrix cannot be reduced to the identity matrix");
        }
    }

    public static void chooseFromMenu() {
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                System.out.println();
                double[][] sumMatrix = sumTwoMatrices();
                printMatrix(sumMatrix);
                break;
            case 2:
                System.out.println();
                double[][] subsMatrix = subtractionTwoMatrices();
                printMatrix(subsMatrix);
                break;
            case 3:
                System.out.println();
                double[][] resultMatrix = multiplyTwoMatrices();
                printMatrix(resultMatrix);
                break;
            case 4:
                System.out.println();
                double[][] matrix = enterMatrix();
                while (matrix.length != matrix[0].length) {
                    System.out.println("The matrix must be a square one");
                    matrix = enterMatrix();
                }
                double determinant = findDeterminant(matrix);
                System.out.println("Determinant of the entered matrix is " + determinant);
                break;
            case 5:
                double[][] inverseMatrix = findInverseMatrix();
                printMatrix(inverseMatrix);
                break;
            case 6:
                 canMatrixBeReducedToIdentityMatrix();
                 break;
            default:
                System.out.println("Invalid choice!");
                System.out.print("Choose an option from 1 to 6 - ");
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
        System.out.println("---> 6. Check if a matrix can be reduced to the identity matrix");
        System.out.print("Choose an option from 1 to 6 - ");
        chooseFromMenu();

    }

}