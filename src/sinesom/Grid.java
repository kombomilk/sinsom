/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 * Grid
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
    
    /**
     * Returns the value of the last node
     * @return 
     */
    public double getEnd() {
        // this works only for one row
        return start + step*columns;
    }
    
    /**
     * Returns the value of the first node
     * @return 
     */
    public double getStart() {
        return start;
    }
    
    /**
     * Returns the step between two neighbor nodes
     * @return 
     */
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
        if (bmu == null) return;
        updateNeighbours(bmu, dataItem);
    }

    /**
     * Update value of neighbours of bmu
     *
     * @param bmu
     */
    public void updateNeighbours(Node bmu, double[] dataItem) {
        double radius = getCurrentRadius();
        for (int i = (int) Math.max(0, bmu.getColumn() - radius);
                i < Math.min(columns, bmu.getColumn() + radius); i++) {
            map[0][i].updateValue(dataItem, bmu.getColumn() - i, radius);
        }
    }

    public Node findBestMatchingUnit(double[] dataVector) {
        Node bmu = null;
        // specific for this case
        for (int j = 0; j < columns; j++) {
            if (bmu == null) bmu = map[0][j];
            double bmuDistance = bmu.getDistance(dataVector);
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
        if (SineSOM.iteration < 800) {
            return 1;
        } else {
            return 0.02;
        }
//        return 1;
//        return SineSOM.START_LEARNING_RATE
//                * Math.exp(-SineSOM.iteration / SineSOM.getInputDimension());
    }

    public static double getNodeRatio(int distance, double radius) {
        double sigma = radius;
        return Math.exp(-Utils.square(distance) / Utils.square(sigma));
//        return 1;
    }

    private double getCurrentRadius() {
        return Math.max(rows, columns) / 2 * Utils.getExponent(
                SineSOM.iteration, SineSOM.getInputDimension());
//        return 2;
    }
}
