package hit;

/**
 * 205543317.
 */

public class Counter {

    private int counter; // A counter.

    /**
     * Add number to current count.
     *
     * @param number a number.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number a number.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return the value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}