
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

    //Return true iff 2 consecutive points are a distance gt length1 apart.
    public static boolean LIC0() {
    	for(int i = 0; i < X.length-1; i++){
    		double x1 = X[i];
    		double x2 = X[i+1];
    		double y1 = Y[i];
    		double y2 = Y[i+1];
    		double dx = x1-x2;
    		double dy = y1-y2;
    		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    		if(distance > PARAMETERS.length1){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    private static boolean LIC1() {
        return false;
    }
    private static boolean LIC2() {
        return false;
    }
    private static boolean LIC3() {
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
