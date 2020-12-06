package Java_Core_2.Homework_2;

import java.util.Arrays;

public class Exceptions {

    private static final int FIXEDCOLUMNS = 4;
    private static final int FIXEDROWS = 4;

    //5. * Написать собственные классы исключений для каждого из случаев
    public static class RowsException extends RuntimeException{
        RowsException(int requiredRows, int actualRows){
            super("Row requirement: " + requiredRows + ". Received " + actualRows + " rows instead.");
        }
    }

    public static class ColumnsException extends RuntimeException{
        ColumnsException(int requiredColumns, int actualColumns){
            super("Column requirement: " + requiredColumns + ". Received " + actualColumns + " columns instead.");
        }
    }

    public static class NotANumberException extends NumberFormatException{
        NotANumberException(String value){
            super("Introduced value is not a number: " + value);
        }
    }

    public static void main(String[] args) {
        // Есть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4x4)
        String oneLineMatrix = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        //String shortRowsMatrix = "10 3 1 2\n2 3 2 2\n5 6 7 1";
        //String shortColumnsMatrix = "10 3 1 2\n2 3\n5 6 7 1\n300 3 1 0";
        //String notANumberMatrix = "10 3 1 2\n2 3 F 2\n5 6 7 1\n300 3 F 0";
        //String multiExceptionMatrix = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 F";

        try{
            //4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
            //getStringMatrix(oneLineMatrix);
            //getStringMatrix(shortRowsMatrix);
            //getStringMatrix(shortColumnsMatrix);
            //getIntMatrixFromStringArray(getStringMatrix(notANumberMatrix));
            System.out.println(matrixCalculations(getIntMatrixFromStringArray(getStringMatrix(oneLineMatrix))));
            //System.out.println(matrixCalculations(getIntMatrixFromStringArray(getStringMatrix(shortRowsMatrix))));
            //System.out.println(matrixCalculations(getIntMatrixFromStringArray(getStringMatrix(shortColumnsMatrix))));
            //System.out.println(matrixCalculations(getIntMatrixFromStringArray(getStringMatrix(multiExceptionMatrix))));
        }
        //Ваши методы должны бросить исключения в случаях:
        //Если размер матрицы, полученной из строки, не равен 4x4;
        catch (RowsException | ColumnsException e){
            if (e.getClass() == RowsException.class)
                System.out.println("Encountered RowsException:\n" + ((RowsException) e).getMessage());
            if (e.getClass() == ColumnsException.class)
                System.out.println("Encountered ColumnsException:\n" + ((ColumnsException) e).getMessage());
        }
        //Если в одной из ячеек полученной матрицы не число; (например символ или слово)
        catch (NotANumberException e){
            System.out.println("Encountered NotANumberException:\n" + e.getMessage());
        }
    }

    // Написать метод, на вход которого подаётся такая строка, метод должен преобразовать
    // строку в двумерный массив типа String[][];
    public static String[][] getStringMatrix(String matrix){
        String[] mRows = matrix.toString().split("\n");
        //System.out.println(Arrays.toString(mRows));
        if (mRows.length != FIXEDROWS)
            throw new RowsException(FIXEDROWS, mRows.length);

        String[][] resultingMatrix = new String[mRows.length][];
        for (int i = 0; i < mRows.length; i++){
            resultingMatrix[i] = mRows[i].split(" ");
            //System.out.println(Arrays.toString(resultingMatrix[i]));
            if (resultingMatrix[i].length != FIXEDCOLUMNS)
                throw new ColumnsException(FIXEDCOLUMNS, resultingMatrix[i].length);
        }

        return resultingMatrix;
    }

    //Преобразовать все элементы массива в числа типа int
    // eh, might need the int matrix at some point so why not
    public static int[][] getIntMatrixFromStringArray(String[][] stringMatrix){
        int[][] intMatrix = new int[stringMatrix.length][];
        for (int i = 0; i < stringMatrix.length; i++) {
            intMatrix[i] = new int[stringMatrix[i].length];
            for (int j = 0; j < stringMatrix[i].length; j++) {
                try {
                    intMatrix[i][j] = Integer.parseInt(stringMatrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new NotANumberException(stringMatrix[i][j]);
                }
            }
            //System.out.println(Arrays.toString(intMatrix[i]));
        }

        return intMatrix;
    }

    //просуммировать, поделить полученную сумму на 2, и вернуть результат
    public static float matrixCalculations(int[][] intMatrix){
        int result = 0;

        for (int i = 0; i < intMatrix.length; i++) {
            for (int j = 0; j < intMatrix[i].length; j++) {
                    result += intMatrix[i][j];
                //System.out.println(intMatrix[i][j]);
                //System.out.println(result + "\n");
            }
        }

        return result / 2f;
    }
}
