// 315318766 Omer Bar

package game;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class Counter {

    private int counter;

    /**
     * constructor.
     * @param counter - int.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * add number to current count.
     * @param number - int.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number - int
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    // get current count.

    /**
     * Getter for the counter value.
     * @return - int
     */
    public int getValue() {
        return this.counter;
    }

    @Override
    public String toString() {
        return "Score: " + this.getValue();
    }
}