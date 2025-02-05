package utils;

import static utils.Statics.calculateMean;

public abstract class RegressionModel {
    protected double[] beta;

    protected Double determinationCoefficient;


    public RegressionModel(double[] beta) {
        this.beta = beta;
    }

    @Override
    public String toString() {
        StringBuilder equation = new StringBuilder("Y = ");
        for (int i = 0; i < beta.length; i++) {
            if (i == 0) {
                equation.append(beta[i]);
            } else {
                equation.append(" + ").append(beta[i]).append("X").append(i);
            }
        }
        equation.append(" + e");
        return equation.toString();
    }

    public Double getFitness(DataSet dataSet){
        if (this.determinationCoefficient == null) {
            this.determinationCoefficient = calculateDeterminationCoefficient(dataSet, this.beta);
        }
        return this.determinationCoefficient;
    }

    public double predict(double[][] x) {
        double prediction = beta[0];
        for (int i = 0; i < x[0].length; i++) {
            prediction += beta[i + 1] * x[0][i];
        }
        return prediction;
    }

//    public static double calculateDeterminationCoefficient(DataSet dataSet, double[] betas) {
//        double[][] xValues = dataSet.getX();
//        double[] yValues = dataSet.getY();
//        double sumOfSquaredErrors = 0.0;
//        double sumOfSquaredTotal = 0.0;
//        double yMean = calculateMean(yValues);
//
//        for (int i = 0; i < yValues.length; i++) {
//            double predicted = betas[0];
//            for (int j = 0; j < xValues[i].length; j++) {
//                predicted += betas[j + 1] * xValues[i][j];
//            }
//            double error = yValues[i] - predicted;
//            sumOfSquaredErrors += error * error;
//            double totalError = yValues[i] - yMean;
//            sumOfSquaredTotal += totalError * totalError;
//        }
//
//        return 1 - (sumOfSquaredErrors / sumOfSquaredTotal);
//    }

    public static double calculateDeterminationCoefficient(DataSet dataSet, double[] betas) {
        double[][] xValues = dataSet.getX();
        double[] yValues = dataSet.getY();
        double sumOfSquaredErrors = 0.0;
        double sumOfSquaredTotal = 0.0;
        double yMean = calculateMean(yValues);

        // Remove the first column (identity column of ones)
        int newFeatureCount = xValues[0].length - 1; // New number of features
        double[][] adjustedXValues = new double[xValues.length][newFeatureCount];

        for (int i = 0; i < xValues.length; i++) {
            System.arraycopy(xValues[i], 1, adjustedXValues[i], 0, newFeatureCount);
        }

        // Adjust betas array to exclude β₀ (intercept)
        double intercept = betas[0];  // Save the intercept separately
        double[] adjustedBetas = new double[betas.length - 1];
        System.arraycopy(betas, 1, adjustedBetas, 0, adjustedBetas.length);

        if (adjustedBetas.length != adjustedXValues[0].length) {
            throw new IllegalArgumentException("Mismatch: betas length should be " + adjustedXValues[0].length + " but found " + adjustedBetas.length);
        }

        // Compute R² (coefficient of determination)
        for (int i = 0; i < yValues.length; i++) {
            double predicted = intercept; // Start with the intercept
            for (int j = 0; j < adjustedXValues[i].length; j++) {
                predicted += adjustedBetas[j] * adjustedXValues[i][j];
            }
            double error = yValues[i] - predicted;
            sumOfSquaredErrors += error * error;
            double totalError = yValues[i] - yMean;
            sumOfSquaredTotal += totalError * totalError;
        }

        return sumOfSquaredTotal == 0 ? 0 : 1 - (sumOfSquaredErrors / sumOfSquaredTotal);
    }




}
