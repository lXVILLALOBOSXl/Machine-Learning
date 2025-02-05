package utils;

public class GPR extends RegressionModel{

    private DataSet dataSet;

    public GPR(double[] beta) {
        super(beta);
    }

    public GPR(DataSet dataSet) {
        super(null);
        this.dataSet = dataSet;
        this.train();
    }


    public void train() {
        this.beta = LinearAlgebra.matrixVectorMultiplication(LinearAlgebra.inverseMatrix(LinearAlgebra.matrixMultiplication((LinearAlgebra.transposeMatrix(dataSet.getX())),dataSet.getX())),
                LinearAlgebra.matrixVectorMultiplication((LinearAlgebra.transposeMatrix(dataSet.getX())),dataSet.getY()));
    }
}
