import utils.DataSet;
import utils.SLR;

public class Main {
    public static void main(String[] args) {
        DataSet dataSet = new DataSet(
                new double[][]{{43.60},{50.44},{59.01},{66.30},{82.36},{92.15},{100.51},{110.06},{111.51}},
                new double[]{1261.08,1475.28,1657.52,2059.05,2303.76,2512.64,2751.46,2787.67,2939.13}
        );

        DataSet[] splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.FIRST);
        DataSet firstSet = splitData[0];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.LAST);
        DataSet lastSet = splitData[0];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.RANDOM);
        DataSet randomSet = splitData[0];

        SLR slr = new SLR(dataSet);
        SLR slrF = new SLR(firstSet);
        SLR slrL = new SLR(lastSet);
        SLR slrR = new SLR(randomSet);

        System.out.println(dataSet);

        System.out.println(slr);
        System.out.println(slr.calculateDeterminationCoefficient(dataSet, slr.getBeta()) + "\n");
        System.out.println(slrF);
        System.out.println(slrF.calculateDeterminationCoefficient(dataSet, slrF.getBeta()) + "\n");
        System.out.println(slrL);
        System.out.println(slrL.calculateDeterminationCoefficient(dataSet, slrL.getBeta()) + "\n");
        System.out.println(slrR);
        System.out.println(slrR.calculateDeterminationCoefficient(dataSet, slrR.getBeta()) + "\n");
    }
}
