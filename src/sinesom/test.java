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
    
    public static void main(String[] args) {
        System.out.println(random(2));
    }
    
}
