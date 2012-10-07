/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 * Supplementary functions
 * @author sergey
 */
public class Utils {
    
    /**
     * Returns the initial weight of a node
     * In this case just a small number from 0 to 1
     * @return 
     */
    public static double initialWeight() {
        return Math.random() * 2 - 1;
    }
    
    /**
     * Almost Euclidean distance - except that it's squared
     * @return 
     */
    public static double getDistance(double[] vec1, double[] vec2) {
        double distance = 0;
        
        if (vec1.length == vec2.length) {
            for (int i=0; i<vec1.length; i++) {
                distance += square(vec1[i] - vec2[i]);
            }
        } else {
            distance = -1;
        }
        return distance;
    }
    
    public static double getExponent(int iteration, int totalIterations) {
        return Math.exp(- (double) iteration / totalIterations);
    }
    
    // Mathematical functions
    
    /**
     * Squares the number
     * @return 
     */
    public static double square(double d) {
        return d * d;
    }
    
    public static int random(int limit) {
        return (int) Math.floor(Math.random() * limit);
    }
    
    public static String format(double number) {
        return String.format("%.2f", number);
    }
}
