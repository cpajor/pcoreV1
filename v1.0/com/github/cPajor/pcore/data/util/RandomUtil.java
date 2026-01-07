package com.github.cPajor.pcore.data.util;

import java.util.Random;

public class RandomUtil {

    public static int getRandInt( int min,  int max) throws IllegalArgumentException {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static Double getRandDouble( double min,  double max) throws IllegalArgumentException {
        return new Random().nextDouble() * (max - min) + min;
    }

    public static Float getRandFloat( float min,  float max) throws IllegalArgumentException {
        return new Random().nextFloat() * (max - min) + min;
    }
    
    public static boolean chance(int max) {
    	return new Random().nextInt(max - 1) == 0;
    }

}
