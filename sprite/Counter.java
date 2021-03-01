package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
public class Counter {
    private int count;
    /**
     * constructors for the Counter class.
     * @param initialValue - the initial value for the counter
     **/
    public Counter(int initialValue) {
        this.count = initialValue;
    }

    /***
     * add number to current count.
     * @param number - the number to current count
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /***
     * subtract number from current count.
     * @param number - number from current count
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /***
     * get current count.
     * @return the value of the counter
     */
    public int getValue() {
        return this.count;
    }
}
