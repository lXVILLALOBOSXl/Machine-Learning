import utils.DataSet;
import utils.RegressionModel;
import utils.SLR;

public class Main {
    public static void main(String[] args) {
        DataSet dataSet = new DataSet(
                new double[][]{
                        {108}, {115}, {106}, {97}, {95}, {91}, {97}, {83}, {83}, {78},
                        {54}, {67}, {56}, {53}, {61}, {115}, {81}, {78}, {30}, {45},
                        {99}, {32}, {25}, {28}, {90}, {89}
                },
                new double[]{
                        95, 96, 95, 97, 93, 94, 95, 93, 92, 86,
                        73, 80, 65, 69, 77, 96, 87, 89, 60, 63,
                        95, 61, 55, 56, 94, 93
                }
        );

        DataSet[] splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.FIRST);
        DataSet firstSetTrain = splitData[0];
        DataSet firsSetTest = splitData[1];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.LAST);
        DataSet lastSetTrain = splitData[0];
        DataSet lastSetTest = splitData[1];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.RANDOM);
        DataSet randomSetTrain = splitData[0];
        DataSet randomSetTest = splitData[1];

        SLR slr = new SLR(dataSet);
        SLR slrF = new SLR(firstSetTrain);
        SLR slrL = new SLR(lastSetTrain);
        SLR slrR = new SLR(randomSetTrain);

        System.out.println(dataSet);

        System.out.println(slr);
        System.out.println(slr.getFitness(dataSet) + "\n");

        System.out.println(slrF);
        for (int i = 0; i < firsSetTest.getX().length; i++) {
            for (int j = 0; j < firsSetTest.getX()[i].length; j++) {
                System.out.println( firsSetTest.getY()[i] + " - " + slrF.predict(new double[][]{firsSetTest.getX()[i]}));
            }
        }
        System.out.println(slrF.getFitness(firstSetTrain) + "\n");

        System.out.println(slrL);
        for (int i = 0; i < lastSetTest.getX().length; i++) {
            for (int j = 0; j < lastSetTest.getX()[i].length; j++) {
                System.out.println( lastSetTest.getY()[i] + " - " + slrL.predict(new double[][]{lastSetTest.getX()[i]}));
            }
        }
        System.out.println(slrL.getFitness(lastSetTrain) + "\n");

        System.out.println(slrR);
        for (int i = 0; i < randomSetTest.getX().length; i++) {
            for (int j = 0; j < randomSetTest.getX()[i].length; j++) {
                System.out.println( randomSetTest.getY()[i] + " - " + slrR.predict(new double[][]{randomSetTest.getX()[i]}));
            }
        }
        System.out.println(slrR.getFitness(randomSetTrain) + "\n");

        RegressionModel best = null;

        if (slrF.getFitness(firstSetTrain) > slrL.getFitness(lastSetTrain) && slrF.getFitness(firstSetTrain) > slrR.getFitness(randomSetTrain)) {
            best = slrF;
        } else if (slrL.getFitness(lastSetTrain) > slrF.getFitness(firstSetTrain) && slrL.getFitness(lastSetTrain) > slrR.getFitness(randomSetTrain)) {
            best = slrL;
        } else {
            best = slrR;
        }

        System.out.println("Best model: " + best);


    }
}
