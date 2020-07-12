import java.util.EmptyStackException;
import java.util.Scanner;

/**
 *  Main class for Calculator
 */
public class Calculator {
    public static void main(String[] args) {
        String mainMenu = "Welcome to calculat0r.\n" +
                "\n" +
                "[A] Add new equation\n" +
                "[F] Change equation from history\n" +
                "[B] Print previous equation\n" +
                "[P] Print full history\n" +
                "[U] Undo\n" +
                "[R] Redo\n" +
                "[C] Clear history\n" +
                "[Q] Quit \n" +
                "\n" +
                "Select an option: ";
        String formatPrintMenu = "%-2s%-27s%-27s%-27s%-12s%-12s%s";
        String printMenu = String.format(formatPrintMenu, "#","Equation","Pre" +
                "-Fix","Post-Fix","Answer","Binary","Hexadecimal");
        Scanner stdin = new Scanner(System.in);
        System.out.print(mainMenu);
        String user = stdin.next();
        HistoryStack historyStack = new HistoryStack();
        EquationStack equationStack = new EquationStack();
        do {
            if (user.equalsIgnoreCase("a")) {
                System.out.print("Please enter an equation (in-fix notation): ");
                stdin.nextLine();
                String eq = stdin.nextLine();
                try {
                    Equation equation = new Equation(eq);
                    if (equation.isBalanced()) {
                        System.out.println("The equation is balanced and the " +
                                "answer is " + equation.getAnswer());

                    } else {
                        System.out.println("The equation is not balanced!");
                        equation.setAnswer("0.0000");
                        equation.setBinary("0");
                        equation.setHex("0");
                        equation.setPostfix("N/A");
                        equation.setPrefix("N/A");
                    }
                    historyStack.push(equation);
                } catch (EmptyStackException ex) {
                    System.out.println("The equation is not balanced!");
                    stdin.nextLine();
                }
            } else if (user.equalsIgnoreCase("f")) {
                // Do Something
            } else if (user.equalsIgnoreCase("b")) {
                try {
                    System.out.println(printMenu);
                    System.out.println(historyStack.getEquation(
                            historyStack.size()).toString());
                } catch (PositionOutOfRangeException e) {
                    System.out.println("Does not exist!");
                    stdin.nextLine();
                }
            } else if (user.equalsIgnoreCase("p")) {
                System.out.println(printMenu);
                System.out.println(historyStack.toString());
            } else if (user.equalsIgnoreCase("u")) {
                try {
                    historyStack.undo();
                } catch (NoLastUndoneEquationException e) {
                    System.out.println("There is nothing to undo.");
                    stdin.nextLine();
                }
            } else if (user.equalsIgnoreCase("r")) {
                try {
                    historyStack.redo();
                } catch (NoLastUndoneEquationException e) {
                    System.out.println("There is nothing to redo.");
                    stdin.nextLine();
                }
            } else if (user.equalsIgnoreCase("c")) {
                System.out.println("Memory Cleared!");
                historyStack = new HistoryStack();
            } else {
                System.out.println("Invalid input! Please put a proper input");
            }
            System.out.println();
            System.out.print(mainMenu);
            user = stdin.next();
        } while (!user.equalsIgnoreCase("q"));
        System.out.println("Program terminating successfully!");
        stdin.close();
    }
}
