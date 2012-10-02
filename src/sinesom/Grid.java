/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 *
 * @author sergey
 */
public class Grid {

    private int rows;
    private int columns;
    private Node[][] map;
    private double start;
    private double step;

    public Grid(int rows, int columns, 
            double start, double step) {
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.step = step;
        
        map = new Node[rows][columns];
        
        int index = 0;
        for (double i = start; index < columns; i += step) {
            map[0][index] = new Node(0, index++, i);
        }
    }
    
    public double getEnd() {
        return start + step*columns;
    }
    
    public double getStart() {
        return start;
    }
    
    public double getStep() {
        return step;
    }

    /**
     * Processes data item in a certain iteration
     *
     * @param dataItem
     */
    public void processData(double[] dataItem) {
        Node bmu = findBestMatchingUnit(dataItem);
        updateBMU(bmu, dataItem);
        updateNeighbours(bmu, dataItem);
    }

    /**
     * Update value of best matching unit
     *
     * @param bmu
     */
    public void updateBMU(Node bmu, double[] dataItem) {
        bmu.updateValue(dataItem, 0);
    }

    /**
     * Update value of neighbours of bmu
     *
     * @param bmu
     */
    public void updateNeighbours(Node bmu, double[] dataItem) {
        for (int i = (int) Math.max(0, bmu.getColumn() - getCurrentRadius());
                i < Math.min(columns, bmu.getColumn() + getCurrentRadius()); i++) {
            map[0][i].updateValue(dataItem, bmu.getColumn() - i);
        }
    }

    public Node findBestMatchingUnit(double[] dataVector) {
        Node bmu = map[0][0];
        double bmuDistance = bmu.getDistance(dataVector);
        // specific for this case
        for (int j = 1; j < columns; j++) {
            double currentDistance = map[0][j].getDistance(dataVector);
            if (currentDistance < bmuDistance) {
                bmu = map[0][j];
                bmuDistance = currentDistance;
            }
        }
        return bmu;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += map[i][j].toString() + "\n";
            }
        }
        return result;
    }

    public static double getLearningRate() {
        if (SineSOM.iteration < 400) {
            return 1;
        } else {
            return 0.02;
        }
//        return SineSOM.START_LEARNING_RATE
//                * Math.exp(-SineSOM.iteration / SineSOM.getInputDimension());
    }

    public static double getNodeRatio(int distance) {
        return Math.exp(-Utils.square(distance)
                / (2 * Utils.getExponent(SineSOM.iteration, SineSOM.getInputDimension())));
    }

    private double getCurrentRadius() {
        return Math.max(rows, columns) / 2 * Utils.getExponent(
                SineSOM.iteration, SineSOM.getInputDimension());
    }
}
