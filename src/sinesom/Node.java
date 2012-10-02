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

    public Node(int row, int column, double xValue) {

        this.row = row;
        this.column = column;
        this.dimension = 2;
        weights = new double[dimension];

        weights[0] = xValue;
        weights[1] = Utils.initialWeight();
    }

    public void updateValue(double[] dataItem, int distance) {
        double[] newWeights = new double[dimension];
        newWeights[0] = weights[0];
        newWeights[1] = (dataItem[1] - weights[1]) * Grid.getLearningRate()
                * Grid.getNodeRatio(distance);
        newWeights[1] += weights[1];
        weights = newWeights;
    }

    public double[] getWeights() {
        return weights;
    }

    public int getRow() {
        return row;
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
