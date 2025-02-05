import utils.DataSet;
import utils.GPR;
import utils.RegressionModel;

public class Main {
    public static void main(String[] args) {
        DataSet dsOriginal = new DataSet(
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
        DataSet dataSet = new DataSet(
                new double[][]{
                        {1,108}, {1,115}, {1,106}, {1,97}, {1,95}, {1,91}, {1,97}, {1,83}, {1,83}, {1,78},
                        {1,54}, {1,67}, {1,56}, {1,53}, {1,61}, {1,115}, {1,81}, {1,78}, {1,30}, {1,45},
                        {1,99}, {1,32}, {1,25}, {1,28}, {1,90}, {1,89},
                },
                new double[]{
                        95, 96, 95, 97, 93, 94, 95, 93, 92, 86,
                        73, 80, 65, 69, 77, 96, 87, 89, 60, 63,
                        95, 61, 55, 56, 94, 93
                }
        );

        DataSet dataSet2 = new DataSet(
                new double[][]{
                        {1,108,Math.pow(108,2)}, {1,115,Math.pow(115,2)}, {1,106,Math.pow(106,2)}, {1,97,Math.pow(97,2)}, {1,95,Math.pow(95,2)}, {1,91,Math.pow(91,2)}, {1,97,Math.pow(97,2)}, {1,83,Math.pow(83,2)}, {1,83,Math.pow(83,2)}, {1,78,Math.pow(78,2)},
                        {1,54,Math.pow(54,2)}, {1,67,Math.pow(67,2)}, {1,56,Math.pow(56,2)}, {1,53,Math.pow(53,2)}, {1,61,Math.pow(61,2)}, {1,115,Math.pow(115,2)}, {1,81,Math.pow(81,2)}, {1,78,Math.pow(78,2)}, {1,30,Math.pow(30,2)}, {1,45,Math.pow(45,2)},
                        {1,99,Math.pow(99,2)}, {1,32,Math.pow(32,2)}, {1,25,Math.pow(25,2)}, {1,28,Math.pow(28,2)}, {1,90,Math.pow(90,2)}, {1,89,Math.pow(89,2)},
                },
                new double[]{
                        95, 96, 95, 97, 93, 94, 95, 93, 92, 86,
                        73, 80, 65, 69, 77, 96, 87, 89, 60, 63,
                        95, 61, 55, 56, 94, 93
                }
        );
        DataSet dataSet3 = new DataSet(
                new double[][]{
                        {1,108,Math.pow(108,2),Math.pow(108,3)}, {1,115,Math.pow(115,2),Math.pow(115,3)}, {1,106,Math.pow(106,2),Math.pow(106,3)}, {1,97,Math.pow(97,2),Math.pow(97,3)}, {1,95,Math.pow(95,2),Math.pow(95,3)}, {1,91,Math.pow(91,2),Math.pow(91,3)}, {1,97,Math.pow(97,2),Math.pow(97,3)}, {1,83,Math.pow(83,2),Math.pow(83,3)}, {1,83,Math.pow(83,2),Math.pow(83,3)}, {1,78,Math.pow(78,2),Math.pow(78,3)},
                        {1,54,Math.pow(54,2),Math.pow(54,3)}, {1,67,Math.pow(67,2),Math.pow(67,3)}, {1,56,Math.pow(56,2),Math.pow(56,3)}, {1,53,Math.pow(53,2),Math.pow(53,3)}, {1,61,Math.pow(61,2),Math.pow(61,3)}, {1,115,Math.pow(115,2),Math.pow(115,3)}, {1,81,Math.pow(81,2),Math.pow(81,3)}, {1,78,Math.pow(78,2),Math.pow(78,3)}, {1,30,Math.pow(30,2),Math.pow(30,3)}, {1,45,Math.pow(45,2),Math.pow(45,3)},
                        {1,99,Math.pow(99,2),Math.pow(99,3)}, {1,32,Math.pow(32,2),Math.pow(32,3)}, {1,25,Math.pow(25,2),Math.pow(25,3)}, {1,28,Math.pow(28,2),Math.pow(28,3)}, {1,90,Math.pow(90,2),Math.pow(90,3)}, {1,89,Math.pow(89,2),Math.pow(89,3)},
                },
                new double[]{
                        95, 96, 95, 97, 93, 94, 95, 93, 92, 86,
                        73, 80, 65, 69, 77, 96, 87, 89, 60, 63,
                        95, 61, 55, 56, 94, 93
                }
        );

        DataSet[] splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.FIRST);
        DataSet firstSetTrain = splitData[0];
        DataSet firstSetTest = splitData[1];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.LAST);
        DataSet lastSetTrain = splitData[0];
        DataSet lastSetTest = splitData[1];
        splitData = DataSet.split(dataSet, 0.7, DataSet.SplitType.RANDOM);
        DataSet randomSetTrain = splitData[0];
        DataSet randomSetTest = splitData[1];

        GPR gpr = new GPR(dataSet);
        GPR gpr2 = new GPR(dataSet2);
        GPR gpr3 = new GPR(dataSet3);

        System.out.println(dsOriginal);

        System.out.println(gpr);
        System.out.println(gpr.getFitness(dataSet) + "\n");
        System.out.println(gpr2);
        System.out.println(gpr2.getFitness(dataSet2) + "\n");
        System.out.println(gpr3);
        System.out.println(gpr3.getFitness(dataSet3) + "\n");

        GPR gpr1F = new GPR(firstSetTrain);
        GPR gpr1L = new GPR(lastSetTrain);
        GPR gpr1R = new GPR(randomSetTrain);


        System.out.println(gpr1F);
        for(int i = 0; i < firstSetTest.getX().length; i++) {
            for (int j = 1; j < firstSetTest.getX()[i].length; j++) {
                System.out.println( firstSetTest.getY()[i] + " - " + gpr1F.predict(new double[][]{new double[]{firstSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1F.getFitness(firstSetTrain) + "\n");

        System.out.println(gpr1L);
        for (int i = 0; i < lastSetTest.getX().length; i++) {
            for (int j = 1 ; j < lastSetTest.getX()[i].length; j++) {
                System.out.println( lastSetTest.getY()[i] + " - " + gpr1L.predict(new double[][]{new double[]{lastSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1L.getFitness(lastSetTrain) + "\n");

        System.out.println(gpr1R);
        for (int i = 0; i < randomSetTest.getX().length; i++) {
            for (int j = 1; j < randomSetTest.getX()[i].length; j++) {
                System.out.println( randomSetTest.getY()[i] + " - " + gpr1R.predict(new double[][]{new double[]{randomSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1R.getFitness(randomSetTrain) + "\n");

        RegressionModel best = null;
        if(gpr1F.getFitness(firstSetTrain) > gpr1L.getFitness(lastSetTrain) && gpr1F.getFitness(firstSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1F;
        } else if(gpr1L.getFitness(lastSetTrain) > gpr1F.getFitness(firstSetTrain) && gpr1L.getFitness(lastSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1L;
        } else {
            best = gpr1R;
        }
        System.out.println("Best Lineal model: " + best + "\n");

        splitData = DataSet.split(dataSet2, 0.7, DataSet.SplitType.FIRST);
        firstSetTrain = splitData[0];
        firstSetTest = splitData[1];
        splitData = DataSet.split(dataSet2, 0.7, DataSet.SplitType.LAST);
        lastSetTrain = splitData[0];
        lastSetTest = splitData[1];
        splitData = DataSet.split(dataSet2, 0.7, DataSet.SplitType.RANDOM);
        randomSetTrain = splitData[0];
        randomSetTest = splitData[1];

        gpr1F = new GPR(firstSetTrain);
        gpr1L = new GPR(lastSetTrain);
        gpr1R = new GPR(randomSetTrain);

        System.out.println(gpr1F);
        for(int i = 0; i < firstSetTest.getX().length; i++) {
            for (int j = 1; j < firstSetTest.getX()[i].length; j++) {
                System.out.println( firstSetTest.getY()[i] + " - " + gpr1F.predict(new double[][]{new double[]{firstSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1F.getFitness(firstSetTrain) + "\n");

        System.out.println(gpr1L);
        for (int i = 0; i < lastSetTest.getX().length; i++) {
            for (int j = 1 ; j < lastSetTest.getX()[i].length; j++) {
                System.out.println( lastSetTest.getY()[i] + " - " + gpr1L.predict(new double[][]{new double[]{lastSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1L.getFitness(lastSetTrain) + "\n");

        System.out.println(gpr1R);
        for (int i = 0; i < randomSetTest.getX().length; i++) {
            for (int j = 1; j < randomSetTest.getX()[i].length; j++) {
                System.out.println( randomSetTest.getY()[i] + " - " + gpr1R.predict(new double[][]{new double[]{randomSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1R.getFitness(randomSetTrain) + "\n");

        best = null;

        if(gpr1F.getFitness(firstSetTrain) > gpr1L.getFitness(lastSetTrain) && gpr1F.getFitness(firstSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1F;
        } else if(gpr1L.getFitness(lastSetTrain) > gpr1F.getFitness(firstSetTrain) && gpr1L.getFitness(lastSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1L;
        } else {
            best = gpr1R;
        }

        System.out.println("Best Quadratic model: " + best + "\n");

        splitData = DataSet.split(dataSet3, 0.7, DataSet.SplitType.FIRST);
        firstSetTrain = splitData[0];
        firstSetTest = splitData[1];
        splitData = DataSet.split(dataSet3, 0.7, DataSet.SplitType.LAST);
        lastSetTrain = splitData[0];
        lastSetTest = splitData[1];
        splitData = DataSet.split(dataSet3, 0.7, DataSet.SplitType.RANDOM);
        randomSetTrain = splitData[0];
        randomSetTest = splitData[1];

        gpr1F = new GPR(firstSetTrain);
        gpr1L = new GPR(lastSetTrain);
        gpr1R = new GPR(randomSetTrain);

        System.out.println(gpr1F);
        for(int i = 0; i < firstSetTest.getX().length; i++) {
            for (int j = 1; j < firstSetTest.getX()[i].length; j++) {
                System.out.println( firstSetTest.getY()[i] + " - " + gpr1F.predict(new double[][]{new double[]{firstSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1F.getFitness(firstSetTrain) + "\n");

        System.out.println(gpr1L);
        for (int i = 0; i < lastSetTest.getX().length; i++) {
            for (int j = 1 ; j < lastSetTest.getX()[i].length; j++) {
                System.out.println( lastSetTest.getY()[i] + " - " + gpr1L.predict(new double[][]{new double[]{lastSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1L.getFitness(lastSetTrain) + "\n");

        System.out.println(gpr1R);
        for (int i = 0; i < randomSetTest.getX().length; i++) {
            for (int j = 1; j < randomSetTest.getX()[i].length; j++) {
                System.out.println( randomSetTest.getY()[i] + " - " + gpr1R.predict(new double[][]{new double[]{randomSetTest.getX()[i][j]}}));
            }
        }
        System.out.println(gpr1R.getFitness(randomSetTrain) + "\n");

        best = null;

        if(gpr1F.getFitness(firstSetTrain) > gpr1L.getFitness(lastSetTrain) && gpr1F.getFitness(firstSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1F;
        } else if(gpr1L.getFitness(lastSetTrain) > gpr1F.getFitness(firstSetTrain) && gpr1L.getFitness(lastSetTrain) > gpr1R.getFitness(randomSetTrain)) {
            best = gpr1L;
        } else {
            best = gpr1R;
        }

        System.out.println("Best Cubic model: " + best + "\n");

    }
}
