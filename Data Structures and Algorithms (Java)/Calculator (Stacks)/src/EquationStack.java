/**
 *  EquationStack class
 */
public class EquationStack {
    private ExtendedStringStack s;

    /**
     *  Default Constructor
     */
    public EquationStack() {
        this.s = new ExtendedStringStack();
    }

    /**
     * @param s
     */
    public void push(String s) {
        this.s.push(s);
    }

    /**
     * @return
     */
    public String pop() {
        return this.s.pop();
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return this.s.isEmpty();
    }

    /**
     * @return
     */
    public int size() {
        return this.s.size();
    }

    /**
     * @return
     */
    public String peek() {
        return this.s.peek();
    }
}