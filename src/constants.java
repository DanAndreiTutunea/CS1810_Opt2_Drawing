
public class constants {

    //misc constants

    public static final String DELIMETER = " "; //character used as delimeter between values in a string

    //assumptions

    public static final int MILISEC_PER_SEC = 1000; //milisecs in a sec
    public static final int RECTANGLE_NUM_OF_COMMAND_SIDES = 2; // number of sides needed in a rectangle command string
    public static final int TRIANGLE_NUM_OF_COMMAND_SIDES = 3;// number of sides needed in a triangle command string

    //size limits as requested

    public static final int RECTANGLE_MIN_SIDE_SIZE = 40;
    public static final int TRIANGLE_MIN_SIDE_SIZE = 25;
    public static final int RECTANGLE_MAX_SIDE_SIZE =100;
    public static final int TRIANGLE_MAX_SIDE_SIZE = 95;

    //validation errors

    public static final int VALID_SUCCESSFUL = 0;
    public static final int VALID_ERROR_WRONG_NO_SIDES = 1;
    public static final int VALID_ERROR_SIDE_SIZE = 2;
    public static final int VALID_ERROR_NOT_TRIANGLE = 3;
    public static final int VALID_ERROR_COMMAND_UNRECOGNISED = 4;

    //finch related constants
    
    public static final int FINCH_SPEED_TO_USE = 100; //min 0, max 250 - needs to be used as a percentage ( /100 )
    public static final int FINCH_FULL_TURN_DISTANCE = 55; //calculated circle length (2*pi*r), when Finch turns 360 deg 
	public static final int FINCH_FULL_TURN_ANGLE = 360; //360 deg in full circle
    //calibration related values
    public static final int FINCH_CALIBRATED_SPEED_100 = 11; //in cm/s
    public static final int FINCH_LHSWHEEL_CALIBRATION  = 112; //as percentage from generic speed (where 1 is 100%)
    public static final int FINCH_RHSWHEEL_CALIBRATION  = 100; //as percentage from generic speed (where 1 is 100%)
    public static final int FINCH_LHSWHEEL_TURN_CALIBRATION  = 108; //as percentage from generic speed (where 1 is 100%), for turns where one wheel is stationary
    public static final int FINCH_RHSWHEEL_TURN_CALIBRATION  = 100; //as percentage from generic speed (where 1 is 100%), for turns where one wheel is stationary
    

}
