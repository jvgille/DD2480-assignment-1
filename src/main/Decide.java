package decide;

public class Decide {
    public static int NUM_POINTS;
    public static double[] X;
    public static double[] Y;

    public static Parameters PARAMETERS;

    public static Connector[][] LCM;
    public static boolean[] PUV;

    public static boolean decide() {
        return false;
    }

    private static boolean LIC0() {
        return false;
    }
    private static boolean LIC1() {
        return false;
    }
    private static boolean LIC2() {
        return false;
    }
    
    public static boolean LIC3() {
    	assert(PARAMETERS.area1 >= 0);
    	if(NUM_POINTS <= 2) {
    		return false;
    	}
    	for(int i=0; i < NUM_POINTS-2; ++i) {
    		double area = Math.abs(((X[i]*(Y[i+1]-Y[i+2])+X[i+1]*(Y[i+2]-Y[i])+X[i+2]*(Y[i]-Y[i+1]))/2.0));
    		if(area>PARAMETERS.area1) {
    			return true;
    		}
    	}
        return false;
    }
    
    private static boolean LIC4() {
        return false;
    }
    private static boolean LIC5() {
        return false;
    }
    private static boolean LIC6() {
        return false;
    }
    private static boolean LIC7() {
        return false;
    }
    private static boolean LIC8() {
        return false;
    }
    private static boolean LIC9() {
        return false;
    }
    private static boolean LIC10() {
        return false;
    }
    private static boolean LIC11() {
        return false;
    }
    private static boolean LIC12() {
        return false;
    }
    private static boolean LIC13() {
        return false;
    }
    private static boolean LIC14() {
        return false;
    }
}
