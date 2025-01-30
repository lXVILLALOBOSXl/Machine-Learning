package utils;
import java.io.Serializable;
import java.util.Random;

public class DataSet implements Serializable {

    public enum SplitType {
        FIRST, LAST, RANDOM
    }

    private double x[][];
    private double y[];

    public DataSet(double[][] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double[][]  getX() {
        return this.x;
    }

    public double[] getY() {
        return this.y;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < y.length; i++) {
            sb.append("| ").append(y[i]).append(" | ");
            for (int j = 0; j < x[i].length; j++) {
                sb.append(x[i][j]).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getN() {
        return this.y.length;
    }

    public int getNumberOfFeatures() {
        return x[0].length;
    }
    public static DataSet[] split(DataSet dataSet, double percentage, SplitType splitType) {
        int totalSize = dataSet.getN();
        int trainSize = (int) (totalSize * percentage);
        int testSize = totalSize - trainSize;

        double[][] xTrain = new double[trainSize][dataSet.getNumberOfFeatures()];
        double[] yTrain = new double[trainSize];

        double[][] xTest = new double[testSize][dataSet.getNumberOfFeatures()];
        double[] yTest = new double[testSize];

        switch (splitType) {
            case FIRST:
                System.arraycopy(dataSet.x, 0, xTrain, 0, trainSize);
                System.arraycopy(dataSet.y, 0, yTrain, 0, trainSize);

                System.arraycopy(dataSet.x, trainSize, xTest, 0, testSize);
                System.arraycopy(dataSet.y, trainSize, yTest, 0, testSize);
                break;

            case LAST:
                System.arraycopy(dataSet.x, totalSize - trainSize, xTrain, 0, trainSize);
                System.arraycopy(dataSet.y, totalSize - trainSize, yTrain, 0, trainSize);

                System.arraycopy(dataSet.x, 0, xTest, 0, testSize);
                System.arraycopy(dataSet.y, 0, yTest, 0, testSize);
                break;

            case RANDOM:
                int[] indices = new int[totalSize];
                for (int i = 0; i < totalSize; i++) {
                    indices[i] = i;
                }

                Random rand = new Random();
                for (int i = totalSize - 1; i > 0; i--) {
                    int j = rand.nextInt(i + 1);
                    int temp = indices[i];
                    indices[i] = indices[j];
                    indices[j] = temp;
                }

                for (int i = 0; i < trainSize; i++) {
                    xTrain[i] = dataSet.x[indices[i]];
                    yTrain[i] = dataSet.y[indices[i]];
                }
                for (int i = 0; i < testSize; i++) {
                    xTest[i] = dataSet.x[indices[trainSize + i]];
                    yTest[i] = dataSet.y[indices[trainSize + i]];
                }
                break;
        }

        return new DataSet[]{new DataSet(xTrain, yTrain), new DataSet(xTest, yTest)};
    }


}
