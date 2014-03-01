package enums;

/**
 * Common abstract class for enumerate data.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public abstract class Enumeratable {
    private int value;
    private String name;

    public Enumeratable(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Get the integer value of the enumerate type.
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Get string representation of the enumerate type.
     * @return String
     */
    public String getName() {
        return name;
    }
}
