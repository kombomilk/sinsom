/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 *
 * @author sergey
 */
public class Node {

    private int row;
    private int column;
    private double[] weights;
    private int dimension;
    private boolean updated;

    public Node(int row, int column, double xValue) {

        this.row = row;
        this.column = column;
        this.dimension = 2;
        updated = false;
        weights = new double[dimension];

        weights[0] = xValue;
        weights[1] = Utils.initialWeight();
//        weights[1] = 0;
    }

    public void updateValue(double[] dataItem, int distance, double radius) {
        double[] newWeights = new double[dimension];
        double ratio = Grid.getNodeRatio(distance, radius);
        double learningRate = Grid.getLearningRate();
        
        newWeights[0] = (dataItem[0] - weights[0]) * learningRate * ratio;        
        newWeights[0] += weights[0];
        
        newWeights[1] = (dataItem[1] - weights[1]) * learningRate * ratio;
        newWeights[1] += weights[1];
        
        weights = newWeights;
    }

    public double[] getWeights() {
        return weights;
    }

    public int getRow() {
        return row;
    }
    
    public boolean isUpdated() {
        return updated;
    }

    public int getColumn() {
        return column;
    }

    public int getDimension() {
        return dimension;
    }

    public double getDistance(double[] vec) {
        return Utils.getDistance(weights, vec);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < dimension; i++) {
            result += Utils.format(weights[i]) + " ";
        }
        return result;
    }
}
