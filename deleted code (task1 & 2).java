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
                        turtleBCoords[0][0] = x+1;
                        turtleBCoords[0][1] = y;
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
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 1 : { // east
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y+1;
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
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 2 : { // south
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x-1;
                        turtleBCoords[0][1] = y;
                        
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
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 3 : { // west
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y-1;
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
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
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
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 1 : { // east
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y+1;
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 2 : { // south
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x-1;
                        turtleBCoords[0][1] = y;
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
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                    case 3 : { // west
                        // Move BCoords to proposed state b
                        int x = turtleACoords[0][0];
                        int y = turtleACoords[0][1];
                        turtleBCoords[0][0] = x;
                        turtleBCoords[0][1] = y-1;
                        // assign b the value of the position 'north' of a
                        turtleB = s[turtleBCoords[0][0]][turtleBCoords[0][1]];
                        
                        // find the occupationalProb of a & b
                        occupationProbA = occupationalProb(x);
                        if(turtleB != -1) { occupationProbB = occupationalProb(x); } else { occupationProbB = 0; }
                        
                        boolean move = false;
                        
                        double p = accProbTask2(occupationProbA, occupationProbB);
                        
                        if(r < p) {
                            move = true;
                        } else { 
                            move = false;
                        }
                        
                        if(move) { // then move a to b..
                            //turtleACoords = turtleBCoords;
                            turtleACoords[0][0] = turtleBCoords[0][0];
                            turtleACoords[0][1] = turtleBCoords[0][1];
                            turtleA = turtleB;
                        } // ..else a stays where it was ie. we do nothing
                        
                        break;
                    }
                }
            }
        }
    }