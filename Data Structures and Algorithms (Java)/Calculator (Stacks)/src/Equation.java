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
public class Equation {
    private String equation;
    private String prefix;
    private String postfix;
    private String answer;
    private String binary;
    private String hex;

    /**
     *
     */
    public Equation() {
        this.equation = "";
        this.prefix = "";
        this.postfix = "";
        this.answer = "";
        this.binary = "";
        this.hex = "";
    }

    /**
     * @param equation
     */
    public Equation(String equation) {
        this.equation = equation;
        this.prefix = "";
        this.postfix = getPostfix();
        this.answer = "";
        this.binary = "";
        this.hex = "";
    }

    /**
     * @return
     */
    public boolean isBalanced() {
        EquationStack equationStack = new EquationStack();
        String tmp = equation.replaceAll(" ", "");
        for (int i = 0; i<tmp.length(); i++) {
            if (tmp.charAt(i) == '(') {
                equationStack.push(Character.toString(tmp.charAt(i)));
            } else if (tmp.charAt(i) == ')') {
                equationStack.pop();
            }
        }
        return equationStack.isEmpty();
    }

    /**
     * @param number
     * @return
     */
    public String decToBin(int number) {
        String bin = "";
        while (number > 0) {
            int n = number % 2;
            bin = n + bin;
            number /= 2;
        }
        this.binary = bin;
        return bin;
    }

    /**
     * @param number
     * @return
     */
    public String decToHex(int number) {
        String hexString = "";
        while (number > 0) {
            int n = number % 16;
            hexString = (n>=9 ? (char) ('A' + (n-10)): n) + hexString;
            number /= 16;
        }
        this.hex = hexString;
        return hexString;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        double num = Double.parseDouble(this.answer);
        int numInt = (int) num;
        this.binary = decToBin(numInt);
        this.hex = decToHex(numInt);
        getPrefix();
        String format = "%-27s%-27s%-27s%-12.3f%-12s%s";
        return String.format(format,equation,prefix,postfix,
                Double.parseDouble(answer),binary,hex);
    }

    /**
     *  Used some code from Recitation 3 Spring 2020 Stacks
     * @return String of answer
     */
    public String getAnswer() { // Postfix to Answer
        EquationStack equationStack = new EquationStack();
        for (int i=0; i<postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                String number = "";
                while (Character.isDigit(c)) {
                    number += c;
                    i++;
                    c = postfix.charAt(i);
                }
                i--;
                equationStack.push(number);
            } else {
                double operand2 = Double.parseDouble(equationStack.pop());
                double operand1 = Double.parseDouble(equationStack.pop());
                if (c == '+') {
                    String d = "" + (operand1 + operand2);
                    equationStack.push(d);
                } else if (c == '-') {
                    String d = "" + (operand1 - operand2);
                    equationStack.push(d);
                } else if (c == '*') {
                    String d = "" + (operand1 * operand2);
                    equationStack.push(d);
                } else if (c == '/') {
                    String d = "" + (operand1 / operand2);
                    equationStack.push(d);
                } else if (c == '%') {
                    String d = "" + ((int) operand1 % (int) operand2);
                    equationStack.push(d);
                } else if (c == '^') {
                    String d = "" + (Math.pow(operand1,operand2));
                    equationStack.push(d);
                }
            }
        }
        this.answer = equationStack.pop();
        return this.answer;
    }

    /**
     * @return
     */
    public String getBinary() {
        return binary;
    }

    /**
     * @return
     */
    public String getEquation() {
        return equation;
    }

    /**
     * @return
     */
    public String getHex() {
        return hex;
    }

    /**
     * @param c
     * @return
     */
    public int opPrec(char c) {
        if (c == '^') return 4;
        else if (c == '%') return 3;
        else if (c == '*' || c == '/') return 2;
        else if (c == '+' || c == '-') return 1;
        return -1;
    }

    /**
     * @return
     */
    public String getPostfix() {
        String result = "";
        EquationStack equationStack = new EquationStack();
        for (int i=0; i<equation.length(); i++) {
            char c = equation.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                result += c;
            } else if (c == '(') {
                equationStack.push("(");
            } else if (c == ')') {
                while (!equationStack.isEmpty() &&
                        equationStack.peek().charAt(0) != '(') {
                    result += " " + equationStack.pop();
                }
                if (!equationStack.isEmpty() &&
                        equationStack.peek().charAt(0) == '(') {
                    equationStack.pop();
                }
            } else { // Must be an operator
                while (!equationStack.isEmpty() &&
                    opPrec(equationStack.peek().charAt(0)) >= opPrec(c)) {
                    result += " " + equationStack.pop();
                }
                result += " ";
                equationStack.push(Character.toString(c)); // Swap operators
            }
        }
        while (!equationStack.isEmpty()) { // Remaining Operators Besides ()
            result += " " + equationStack.pop();
        }
        this.postfix = result;
        this.answer = getAnswer();
        return postfix;
    }

    /**
     * @param a
     * @return
     */
    public String getPostfix(String a) {
        String result = "";
        EquationStack equationStack = new EquationStack();
        for (int i=0; i<equation.length(); i++) {
            char c = equation.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                result += c;
            } else if (c == '(') {
                equationStack.push("(");
            } else if (c == ')') {
                while (!equationStack.isEmpty() &&
                        equationStack.peek().charAt(0) != '(') {
                    result += " " + equationStack.pop();
                }
                if (!equationStack.isEmpty() &&
                        equationStack.peek().charAt(0) == '(') {
                    equationStack.pop();
                }
            } else { // Must be an operator
                while (!equationStack.isEmpty() &&
                        opPrec(equationStack.peek().charAt(0)) >= opPrec(c)) {
                    result += " " + equationStack.pop();
                }
                result += " ";
                equationStack.push(Character.toString(c)); // Swap operators
            }
        }
        while (!equationStack.isEmpty()) { // Remaining Operators Besides ()
            result += " " + equationStack.pop();
        }
        return result;
    }

    /**
     * @param s
     * @return
     */
    public String reverse(String s) {
        String out = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')')
                out += "(";
            else if (s.charAt(i) == '(')
                out += ")";
            else
                out += s.charAt(i);
        }
        return out;
    }

    /**
     * @return
     */
    public String getPrefix() {
        String rev = reverse(postfix);
        String prefixRev = getPostfix(rev);
        this.prefix = reverse(prefixRev);
        return this.prefix;
    }

    /**
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @param binary
     */
    public void setBinary(String binary) {
        this.binary = binary;
    }

    /**
     * @param equation
     */
    public void setEquation(String equation) {
        this.equation = equation;
    }

    /**
     * @param hex
     */
    public void setHex(String hex) {
        this.hex = hex;
    }

    /**
     * @param postfix
     */
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    /**
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
