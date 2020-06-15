/**
 * @name Tahmidul Alam
 * @ID 112784865
 * @email tahmidul.alam@stonybrook.edu
 * @course CSE 214
 * @recitation Recitation 10 (Daniel Calabria)
 */

/**
 *
 */
public class HistoryStack {
    private ExtendedEquationStack s;
    private Equation peek;
    private ExtendedEquationStack redoStack;
    private int size;

    /**
     *
     */
    public HistoryStack() {
        this.s = new ExtendedEquationStack();
        this.redoStack = new ExtendedEquationStack();
        this.size = 0;
    }

    /**
     * @return
     */
    public Equation peek() {
        return this.s.peek();
    }

    /**
     * @param newEquation
     */
    public void push(Equation newEquation) {
        this.s.push(newEquation);
        this.size++;
    }

    /**
     * @return
     */
    public Equation pop() {
        this.size--;
        return this.s.pop();
    }

    /**
     * @throws NoLastUndoneEquationException
     */
    public void redo() throws NoLastUndoneEquationException {
        if (redoStack.isEmpty())
            throw new NoLastUndoneEquationException();
        Equation eq = redoStack.pop();
        System.out.println("Redoing equation: " + this.s.push(eq));
        this.size++;
    }

    /**
     * @throws NoLastUndoneEquationException
     */
    public void undo() throws NoLastUndoneEquationException {
        if (s.isEmpty())
            throw new NoLastUndoneEquationException();
        Equation eq = this.s.pop();
        redoStack.push(eq);
        System.out.println("Undoing equation: " + eq.getEquation());
        this.size--;
    }

    /**
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * @param position
     * @return
     * @throws PositionOutOfRangeException
     */
    public Equation getEquation(int position)
            throws PositionOutOfRangeException {
        if (position == 1) {
            return (this.s.peek());
        } else if (position > size) throw new PositionOutOfRangeException();
        else {
            int count = 1;
            HistoryStack tmp = new HistoryStack();
            while (count != position-1) {
                if (count == position) {
                    Equation equation = this.s.peek();
                    while (!tmp.isEmpty()) {
                        this.s.push(tmp.pop());
                    }
                    return equation;
                }
                tmp.push(this.s.pop());
                count++;
            }
            while (!tmp.isEmpty()) {
                this.s.push(tmp.pop());
            }
        }
        throw new PositionOutOfRangeException();
    }

    /**
     * @return
     */
    private boolean isEmpty() {
        return this.s.isEmpty();
    }

    /**
     * @return
     */
    public String toString() {
        ExtendedEquationStack eqs = new ExtendedEquationStack();
        String format = "%-2s";
        String output = "";
        int count = size;
        while (!this.s.isEmpty() && count != 0) {
            Equation eq = this.s.pop();
            eqs.push(eq);
            output +=
                    String.format(format,count) + eq.toString() +
                            "\n";
            count--;
        }
        while (!eqs.isEmpty()) {
            this.s.push(eqs.pop());
        }
        return output;
    }

    /**
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * @return
     */
    public Equation getPeek() {
        return peek;
    }

    /**
     * @return
     */
    public ExtendedEquationStack getS() {
        return s;
    }

    /**
     * @param s
     */
    public void setS(ExtendedEquationStack s) {
        this.s = s;
    }

    /**
     * @param peek
     */
    public void setPeek(Equation peek) {
        this.peek = peek;
    }

    /**
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }
}

/**
 *
 */
class NoLastUndoneEquationException extends Exception {
    public NoLastUndoneEquationException() {}
}

/**
 *
 */
class PositionOutOfRangeException extends Exception {
    public PositionOutOfRangeException() {}
}