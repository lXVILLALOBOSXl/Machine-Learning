package utils;

public class SLR extends RegressionModel {

    private DataSet dataSet;

    public SLR(double[] beta) {
        super(beta);
    }

    public SLR(DataSet dataSet) {
        super(null);
        this.dataSet = dataSet;
        this.train();
    }

    public void train() {
        double b1 = calculateSlope();
        double b0 = calculateIntersect(b1);
        this.beta = new double[]{b0, b1};
    }

    private double calculateIntersect(double b1) { // b0
        double[] x = new double[dataSet.getN()];
        int index = 0;
        for (double[] value : dataSet.getX()) {
            x[index++] = value[0];
        }
        return (DiscreetMath.sum(dataSet.getY()) - (b1 * DiscreetMath.sum(x))) / dataSet.getN();
    }

    private double calculateSlope() { // b1
        double[] x = new double[dataSet.getN()];
        int index = 0;
        for (double[] value : dataSet.getX()) {
            x[index++] = value[0];
        }
        return ((dataSet.getN() * DiscreetMath.sumMultiplication(x, dataSet.getY())) - (DiscreetMath.sum(x) * DiscreetMath.sum(dataSet.getY()))) / ((dataSet.getN() * DiscreetMath.sumPow(x, 2)) - (Math.pow(DiscreetMath.sum(x), 2)));
    }

    public double[] getBeta() {
        return this.beta;
    }



}
