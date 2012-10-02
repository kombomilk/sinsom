/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 *
 * @author sergey
 */
public class SineSOM {

    static final double START_LEARNING_RATE = 10;
    static int iteration = 1;
    static double[][] inputData;
    static int rows = 800;
    static int start = -4;
    static double step = 0.01;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid grid = new Grid(1, rows, start, step);
        learn(grid, 1);
        for (iteration = 1; iteration <= inputData.length; iteration++) {
            double[] dataVector = inputData[iteration - 1];
            grid.processData(dataVector);
        }
        //System.out.println(grid.toString());
    }
    
    public static int getInputDimension() {
        return inputData.length;
    }
    
    public static void learn(Grid grid, int fraction) {
        int inputDimension = (int) Math.floor((grid.getEnd() - grid.getStart()) / 
                (grid.getStep() * fraction));
        inputData = new double[inputDimension][2];
        int index = 0;
        for (double i=grid.getStart(); index < inputDimension; 
                i+=grid.getStep() * fraction) {
            inputData[index++] = new double[] {i, Math.sin(i/2)};
//            System.out.println(Utils.format(i) + " " + Utils.format(Math.sin(i/2)));
        }
    }
}