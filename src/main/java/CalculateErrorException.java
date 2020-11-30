public class CalculateErrorException extends Exception {
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    CalculateErrorException() {
        super(CALCULATE_ERROR_MESSAGE);
    }
}
