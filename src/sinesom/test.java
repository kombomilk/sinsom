/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinesom;

/**
 *
 * @author sergey
 */
public class test {
    
    public static int random(int limit) {
        return (int) Math.floor(Math.random() * limit);
    }
    
    public static double exp(double num) {
        return Math.exp(num);
    }
    
    public static void main(String[] args) {
        System.out.println(exp(4));
    }
    
}
