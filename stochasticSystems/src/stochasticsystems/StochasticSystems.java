package stochasticsystems;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rm631
 */
public class StochasticSystems {
    
    private static int[][] s;
    //private static Random rng;
    
    /**
     * @param args the command line arguments
     * http://www.easysurf.cc/fract2.htm
     * https://www.draw.io/
     */
    public static void main(String[] args)
    {   
        Random rng = new Random();
        
        // long term desired occupational prob if prob to be in any cell is equal
        double occupationProb = 1.0 / 9.0; // if equal then it is always 1/9 
        
        int[][] turtleACoords = new int[][] { { 1, 1 } }; // x, y (i, j) coords
        int turtleA = 1; // state a
        
        int[][] turtleBCoords = new int[][] { { 1, 1 } };
        int turtleB = 1; // state b
        
        /*
         * grid with -1 acting as the 'ghost' states
         * -1, -1, -1, -1
         * -1, 7, 8, 9, -1
         * -1, 4, 5, 6, -1
         * -1, 1, 2, 3, -1
         * -1, -1, -1, -1
         */
        //int[][] 
        s = new int[][]{ // the states with the imaginary states
            { -1, -1, -1, -1 },
            { -1, 1, 2, 3, -1 },
            { -1, 4, 5, 6, -1 },
            { -1, 7, 8, 9, -1 },
            { -1, -1, -1, -1 }
        };
                
        //task1();
        //for(int i = 0; i < 10000; i++) {
        //    task2();
        //}
        //task2();
        //transitionProb();
        steadyState();
    }
    
    /**
     * calculates the acceptance probability
     * @param a pi(a)
     * @param b pi(b)
     * @return whether or not to move to state b
     */
    private static boolean accProb(double a, double b) {
        System.out.println("a : " + a + " | b : " + b);
        double p = 0; // p is our probability of acceptance
        p = Math.min(1.0, (b/a));
        double pTest = (b/a);
        System.out.println("p is: " + p);
        System.out.println("pTest is: " + p);
        if(p >= 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Moves the turtle through the grid where the steady state probability 
     * to be in any square is equal
     */
    private static void task1() {
        
        Random rng = new Random();
        
        // long term desired occupational prob if prob to be in any cell is equal
        double occupationProb = 1.0 / 9.0; // if equal then it is always 1/9 
        
        int[][] turtleACoords = new int[][] { { 1, 1 } }; // x, y (i, j) coords
        int turtleA = 1; // state a
        
        int[][] turtleBCoords = new int[][] { { 1, 1 } };
        int turtleB = 1; // state b
        
        for(int t = 0; t > -1; t++) { 
            //System.out.println("State a is now: " + turtleA);
            if(t == 0) {
                // at t=0 the turtle is at state 1
            } else {
                int rndInt = rng.nextInt(4); // pseudorandom but 1/4 chance for 0,1,2,3
                //System.out.println("rndInt is: " + rndInt);
                switch(rndInt) {
                    case 0 : { // north
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        //System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x+1;
                        turtleBCoords[0][1] = y;
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        boolean move = false;
                        if(turtleB == -1) {
                            // we never want to go into a ghost state so pi(b) will be 0
                            move = accProb(occupationProb, 0.0);
                        } else {
                            // else the states share the 1/9 chance to be occupied
                            move = accProb(occupationProb, occupationProb);
                        }
                        
                        if(move) { // then move a to b..
                            //System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 1 : { // east
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        //System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y+1;
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        boolean move = false;
                        if(turtleB == -1) {
                            // we never want to go into a ghost state so pi(b) will be 0
                            move = accProb(occupationProb, 0.0);
                        } else {
                            // else the states share the 1/9 chance to be occupied
                            move = accProb(occupationProb, occupationProb);
                        }
                        
                        if(move) { // then move a to b..
                            //System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 2 : { // south
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        //System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x-1;
                        turtleBCoords[0][1] = y;
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        boolean move = false;
                        if(turtleB == -1) {
                            // we never want to go into a ghost state so pi(b) will be 0
                            move = accProb(occupationProb, 0.0);
                        } else {
                            // else the states share the 1/9 chance to be occupied
                            move = accProb(occupationProb, occupationProb);
                        }
                        
                        if(move) { // then move a to b..
                            //System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 3 : { // west
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        //System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y-1;
                        //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        boolean move = false;
                        if(turtleB == -1) {
                            // we never want to go into a ghost state so pi(b) will be 0
                            move = accProb(occupationProb, 0.0);
                        } else {
                            // else the states share the 1/9 chance to be occupied
                            move = accProb(occupationProb, occupationProb);
                        }
                        
                        if(move) { // then move a to b..
                            //System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            //System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                }
                System.out.println();
            }
        }
    }
    
    /**
     * calculates the acceptance probability
     * @param a pi(a)
     * @param b pi(b)
     * @return the probability of acceptance
     */
    private static double accProbTask2(double a, double b) {
        double p = 0; // p is our probability of acceptance
        p = Math.min(1.0, (b/a));
        return p;
    }
    
    /**
     * Moves the turtle through the grid where:
     * { 1, 2, 3 } has a probability of 1/6
     * { 4, 5, 6 } has a probability of 2/6
     * { 7, 8, 9 } has a probability of 3/6
     */
    private static void task2() {
        Random rng = new Random();
        
        double occupationProbA;
        double occupationProbB;
        
        int[][] turtleACoords = new int[][] { { 1, 1 } }; // x, y (i, j) coords
        int turtleA = 1; // state a
        
        int[][] turtleBCoords = new int[][] { { 1, 1 } };
        int turtleB = 1; // state b
        
        for(int t = 0; t < 4; t++) { // Change t for number of time steps, > -1 for infinite
            double r = rng.nextDouble();
            //if(r == 0) { r = 1; } 
            
            if(t == 0) {
                // at t = 0 we are at the 'random' state a
            } else {
                int rndInt = rng.nextInt(4); // pseudorandom but 1/4 chance for 0,1,2,3
                System.out.println("rndInt is: " + rndInt);
                switch(rndInt) {
                    case 0 : { // north
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x+1;
                        turtleBCoords[0][1] = y;
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x+1); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        System.out.println("r: " + r + " | p : " + p);
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 1 : { // east
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y+1;
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        System.out.println("r: " + r + " | p : " + p);
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 2 : { // south
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x-1;
                        turtleBCoords[0][1] = y;
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x-1); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        System.out.println("r: " + r + " | p : " + p);
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 3 : { // west
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        System.out.println("x : " + x + " | y : " + y);
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y-1;
                        System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        System.out.println("r: " + r + " | p : " + p);
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            System.out.println("a has changed!");
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                            System.out.println("turtleACoords: " + turtleACoords[0][0] + " " + turtleACoords[0][1]);
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                }
                System.out.println("At t=3");
            }
        }
    }
    
    /**
     * probabilities based on task 2
     * @param x the x coord of a or b
     * @return the occupational probability of the given row
     */
    private static double occupationalProb(int x) {
        switch(x) {
            case 0 : // row 0 is a ghost state row
                return 0;
            case 1 :
                return (1.0 / 6.0);
                //return (1.0 / 9.0);
            case 2 :
                return (2.0 / 6.0);
                //return (1.0 / 9.0);
            case 3 : 
                return (3.0 / 6.0);
                //return (1.0 / 9.0);
            case 4 : // row 4 is a ghost state row
                return 0;
            default : // if somehow a number out of this range is passed, then we definitely
                      // don't want to go there
                return 0;
        }
    }
    
    /**
     * move through the grid in a linear fashion..
     * calculate the acceptance probability for each pair 
     * calculate the probability of choice, in this case the fixed value 1/4
     * multiply these two values
     * check if it adds to 1, if not, then add a self transition
     */
    private static void transitionProb() {
        Random rng = new Random();
        
        double occupationProbA;
        double occupationProbB;
        
        int[][] turtleACoords = new int[][] { { 1, 1 } }; // x, y (i, j) coords
        int turtleA = 1; // state a
        
        int[][] turtleBCoords = new int[][] { { 1, 1 } };
        int turtleB = 1; // state b
        
        for(int i = 0; i < s.length; i++) {
            for(int j = 0; j < s[i].length; j++) {
                turtleACoords[0][0] = i;
                turtleACoords[0][1] = j;
                if(s[i][j] != -1) { // ignore the ghost states
                    double[] accProb = new double[4]; 
                    double choiceProb = 1.0 / 4.0;
                    double[] transProb = new double[4]; // 1/4 * accProb
                    
                    // find the transition probabilities of NESW (0-3)
                    for(int k = 0; k < 4; k++) {
                        switch(k) {
                            case 0 : {
                                int x = turtleACoords[0][0];
                                int y = turtleACoords[0][1];
                                
                                turtleBCoords[0][0] = x+1;
                                turtleBCoords[0][1] = y;
                                turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                                
                                if(turtleB == -1) {
                                    // we never want to go into a ghost state so pi(b) will be 0
                                    accProb[k] = accProbTask2(occupationalProb(x), 0.0);
                                } else {
                                    accProb[k] = accProbTask2(occupationalProb(x), occupationalProb((x+1)));
                                }
                                
                                transProb[k] = (accProb[k] * choiceProb);
                                break;
                            }
                            case 1 : {
                                int x = turtleACoords[0][0];
                                int y = turtleACoords[0][1];
                                
                                turtleBCoords[0][0] = x;
                                turtleBCoords[0][1] = y+1;
                                turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                                
                                //accProb[k] = ;
                                if(turtleB == -1) {
                                    // we never want to go into a ghost state so pi(b) will be 0
                                    accProb[k] = accProbTask2(occupationalProb(x), 0.0);
                                } else {
                                    // else the states share the 1/9 chance to be occupied
                                    accProb[k] = accProbTask2(occupationalProb(x), occupationalProb(x));
                                }
                                
                                transProb[k] = (accProb[k] * choiceProb);
                                break;
                            }
                            case 2 : {
                                int x = turtleACoords[0][0];
                                int y = turtleACoords[0][1];
                                //System.out.println("x : " + x + " | y : " + y);
                                turtleBCoords[0][0] = x-1;
                                turtleBCoords[0][1] = y;
                                turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                                //System.out.println(turtleB);
                                
                                //accProb[k] = ;
                                if(turtleB == -1) {
                                    // we never want to go into a ghost state so pi(b) will be 0
                                    accProb[k] = accProbTask2(occupationalProb(x), 0.0);
                                } else {
                                    accProb[k] = accProbTask2(occupationalProb(x), occupationalProb((x-1)));
                                }
                                
                                transProb[k] = (accProb[k] * choiceProb);
                                break;
                            }
                            case 3 : {
                                int x = turtleACoords[0][0];
                                int y = turtleACoords[0][1];
                                //System.out.println("x : " + x + " | y : " + y);
                                turtleBCoords[0][0] = x;
                                turtleBCoords[0][1] = y-1;
                                turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                                //System.out.println(turtleB);
                                
                                //accProb[k] = ;
                                if(turtleB == -1) {
                                    // we never want to go into a ghost state so pi(b) will be 0
                                    accProb[k] = accProbTask2(occupationalProb(x), 0.0);
                                } else {
                                    // else the states share the 1/9 chance to be occupied
                                    accProb[k] = accProbTask2(occupationalProb(x), occupationalProb(x));
                                }
                                
                                transProb[k] = (accProb[k] * choiceProb);
                                break;
                            }
                        }
                    }
                    
                    double sum = 0;
                    for(int k = 0; k < transProb.length; k++) {
                        //System.out.println("transProb " + k + ": " + transProb[k]);
                        sum += transProb[k];
                    }
                    
                    double selfTrans = (1 - sum);
                    
                    if(selfTrans != 0) {
                        System.out.println("P(" + s[i][j] + "-> " + s[i][j] + ") = " + selfTrans);
                    }
                    
                    //boolean selfTransFlag = false;
                    //if(selfTrans == 0) { // no self transition
                    for(int k = 0; k < transProb.length; k++) {
                        switch(k) {
                            case 0 :
                                if(transProb[k] == 0) {
                                    /*if(!selfTransFlag) {
                                        selfTransFlag = true;
                                        System.out.println("P(" + s[i][j] + "-> " + s[i][j]
                                        + ") = " + selfTrans);
                                    } */
                                } else {
                                    System.out.println("P(" + s[i][j] + "-> " + s[i+1][j]
                                        + ") = " + transProb[k]);
                                }
                                break;
                            case 1 :
                                if(transProb[k] == 0) {
                                    /*if(!selfTransFlag) {
                                        selfTransFlag = true;
                                        System.out.println("P(" + s[i][j] + "-> " + s[i][j]
                                        + ") = " + selfTrans);
                                    } */
                                } else {
                                    System.out.println("P(" + s[i][j] + "-> " + s[i][j+1]
                                        + ") = " + transProb[k]);
                                }
                                break;
                            case 2 : 
                                if(transProb[k] == 0) {
                                    /*if(!selfTransFlag) {
                                        selfTransFlag = true;
                                        System.out.println("P(" + s[i][j] + "-> " + s[i][j]
                                        + ") = " + selfTrans);
                                    } */
                                } else {
                                    System.out.println("P(" + s[i][j] + "-> " + s[i-1][j]
                                        + ") = " + transProb[k]);
                                }
                                break;
                            case 3 :
                                if(transProb[k] == 0) {
                                    /*if(!selfTransFlag) {
                                        selfTransFlag = true;
                                        System.out.println("P(" + s[i][j] + "-> " + s[i][j]
                                        + ") = " + selfTrans);
                                    } */
                                } else {
                                    System.out.println("P(" + s[i][j] + "-> " + s[i][j-1]
                                        + ") = " + transProb[k]);
                                }
                                break;
                        }
                    }
                    
                }
            }
        }
    }
    
    private static void steadyState() {
        // since the grid contains ghost states we need to go through and ignore them
        // or we could just set it to 9, since we know the length..
        int countLength = 0;
        for(int i = 0; i < s.length; i++) {
            for(int j = 0; j < s[i].length; j++) {
                if(s[i][j] != -1) {
                    countLength++;
                }
            }
        }
        
        // initalise count[] as N+! and initalise all elements to 0
        double[] count = new double[countLength+1];
        for(int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        
        Random rng = new Random();
        
        int[][] turtleACoords = new int[][] { { 1, 1 } }; // x, y (i, j) coords
        int turtleA = 1; // state a
        
        int[][] turtleBCoords = new int[][] { { 1, 1 } };
        int turtleB = 1; // state b
        
        for(int n = 0; n < 10000; n++) { // number of repetitions
            for(int m = 0; m < 4; m++) { // number of time steps
                int k = 0; // number of transitions (including self)
                
                double[] accProb = new double[4]; 
                double choiceProb = 1.0 / 4.0;
                double[] transProb = new double[4];
                
                // find the transition probabilities of NESW (0-3)
                for(int i = 0; i < 4; i++) {
                    switch(i) {
                        case 0 : {
                            int x = turtleACoords[0][0];
                            int y = turtleACoords[0][1];
                            
                            turtleBCoords[0][0] = x+1;
                            turtleBCoords[0][1] = y;
                            turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];

                            if(turtleB == -1) {
                                accProb[i] = accProbTask2(occupationalProb(x), 0.0);
                            } else {
                                accProb[i] = accProbTask2(occupationalProb(x), occupationalProb((x+1)));
                            }

                            transProb[i] = (accProb[i] * choiceProb);
                            break;
                        }
                        case 1 : {
                            int x = turtleACoords[0][0];
                            int y = turtleACoords[0][1];

                            turtleBCoords[0][0] = x;
                            turtleBCoords[0][1] = y+1;
                            turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                            
                            if(turtleB == -1) {
                                accProb[i] = accProbTask2(occupationalProb(x), 0.0);
                            } else {
                                accProb[i] = accProbTask2(occupationalProb(x), occupationalProb(x));
                            }

                            transProb[i] = (accProb[i] * choiceProb);
                            break;
                        }
                        case 2 : {
                            int x = turtleACoords[0][0];
                            int y = turtleACoords[0][1];
                            
                            turtleBCoords[0][0] = x-1;
                            turtleBCoords[0][1] = y;
                            turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                            
                            if(turtleB == -1) {
                                accProb[i] = accProbTask2(occupationalProb(x), 0.0);
                            } else {
                                accProb[i] = accProbTask2(occupationalProb(x), occupationalProb((x-1)));
                            }

                            transProb[i] = (accProb[i] * choiceProb);
                            break;
                        }
                        case 3 : {
                            int x = turtleACoords[0][0];
                            int y = turtleACoords[0][1];
                            
                            turtleBCoords[0][0] = x;
                            turtleBCoords[0][1] = y-1;
                            turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                            
                            if(turtleB == -1) {
                                // we never want to go into a ghost state so pi(b) will be 0
                                accProb[i] = accProbTask2(occupationalProb(x), 0.0);
                            } else {
                                accProb[i] = accProbTask2(occupationalProb(x), occupationalProb(x));
                            }

                            transProb[i] = (accProb[i] * choiceProb);
                            break;
                        }
                    }
                }
                
                double sum = 0;
                for(int i = 0; i < transProb.length; i++) {
                    sum += transProb[k];
                }
                
                /**
                 * The next section sets k = number of transitions (including self)
                 */
                
                double selfTrans = (1 - sum);
                
                if(selfTrans != 0) { // if there is a self transition...
                    k++;
                    //System.out.println("P(" + s[i][j] + "-> " + s[i][j] + ") = " + selfTrans);
                }
                
                for(int i = 0; i < transProb.length; i++) {
                    switch(i) {
                        case 0 :
                            if(transProb[i] == 0) {
                            } else {
                                k++;
                            }
                            break;
                        case 1 :
                            if(transProb[i] == 0) {
                            } else {
                                k++;
                            }
                            break;
                        case 2 : 
                            if(transProb[i] == 0) {
                            } else {
                                k++;
                            }
                            break;
                        case 3 :
                            if(transProb[i] == 0) {
                            } else {
                                k++;
                            }
                            break;
                    }
                }
                
                // initialse t and put the transition value into it
                double[] t = new double[k+1];
                t[0] = 0;
                t[1] = selfTrans;
                
                for(int i = 2; i < t.length; i++) {
                    System.out.println("i: " + i + "transProb: " + transProb[i]);
                    
                    if(transProb[i] != 0) {
                        System.out.println("WAAAA " + transProb[i]);
                        System.out.println(i);
                        t[i] = transProb[i];
                        System.out.println(t[i]);
                    }
                    
                }
                
                Arrays.sort(t); // sort t in ascending order
                
                double r = rng.nextDouble(); // r is between 0-1
                double next = 0; // where we want to go next
                
                double tSum = 0;
                for(int i = 0; i < t.length; i++) {
                    if((tSum < r) && (r <= (tSum + t[i]))) {
                        next = t[i];
                        System.out.println("2) t[" + i +"]" + ": " + t[i]);
                        i = t.length; // end the loop after this iteration..
                    } else {
                        System.out.println("1) t[" + i +"]" + ": " + t[i]);
                        tSum += t[i];
                        System.out.println("tSum: " + tSum);
                    }
                }
                
                // find next in the transProb array
                // so that we know what the next position actually is
                for(int i = 0; i < transProb.length; i++) {
                    System.out.println("next: " + next + " | transProb" + transProb[i]);
                    if(next == transProb[i]) {
                        System.out.println("next: " + next);
                        System.out.println("transProb[" + i + "]: " + transProb[i]);
                        //System.out.println("next has matched transprob!");
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        System.out.println("x: " + x + " | y: " + y);
                        switch(i) {
                            case 0 :
                                turtleACoords[0][0] = x+1;
                                turtleACoords[0][1] = y;
                                turtleA = s[turtleACoords[0][0]][turtleACoords[0][1]];
                                System.out.println("case0, turtleA: " + turtleA);

                                break;
                            case 1 :
                                turtleACoords[0][0] = x;
                                turtleACoords[0][1] = y+1;
                                turtleA = s[turtleACoords[0][0]][turtleACoords[0][1]];
                                System.out.println("case1, turtleA: " + turtleA);

                                break;
                            case 2 :
                                turtleACoords[0][0] = x-1;
                                turtleACoords[0][1] = y;
                                turtleA = s[turtleACoords[0][0]][turtleACoords[0][1]];
                                System.out.println("case2, turtleA: " + turtleA);

                                break;
                            case 3 : 
                                turtleACoords[0][0] = x;
                                turtleACoords[0][1] = y-1;
                                turtleA = s[turtleACoords[0][0]][turtleACoords[0][1]];
                                System.out.println("case3, turtleA: " + turtleA);
                                break;
                        }
                    }
                }
                System.out.println("turtleA: " + turtleA);
                count[turtleA] += 1;
            }
        }
        int n = 10000;
        //for(int i = 0; i < count.length; i++) {
        //    System.out.println(i);
        //    System.out.println(count[i]);
        //}
        System.out.println();
        System.out.println("Estimated prob of 1: " + (count[1]/n));
        System.out.println("Estimated prob of 3: " + (count[3]/n));
        System.out.println("Estimated prob of 9: " + (count[9]/n));
    }
    
}
