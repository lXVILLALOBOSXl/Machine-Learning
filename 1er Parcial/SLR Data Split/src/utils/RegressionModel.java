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

    public static double calculateDeterminationCoefficient(DataSet dataSet, double[] betas) {
        double[][] xValues = dataSet.getX();
        double[] yValues = dataSet.getY();
        double sumOfSquaredErrors = 0.0;
        double sumOfSquaredTotal = 0.0;
        double yMean = calculateMean(yValues);

        for (int i = 0; i < yValues.length; i++) {
            double predicted = betas[0];
            for (int j = 0; j < xValues[i].length; j++) {
                predicted += betas[j + 1] * xValues[i][j];
            }
            double error = yValues[i] - predicted;
            sumOfSquaredErrors += error * error;
            double totalError = yValues[i] - yMean;
            sumOfSquaredTotal += totalError * totalError;
        }

        return 1 - (sumOfSquaredErrors / sumOfSquaredTotal);
    }




}
