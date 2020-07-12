import java.util.Scanner;

/**
 *  Main class for designing an adventure game and playing it
 */
public class AdventureDesigner {
    static SceneTree sceneTree = new SceneTree();
    /**
     * @param args
     *  Main function with possible CLI
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String menu = "\nA) Add Scene\n" +
                "R) Remove Scene\n" +
                "S) Show Current Scene\n" +
                "P) Print Adventure Tree\n" +
                "B) Go Back A Scene\n" +
                "F) Go Forward A Scene\n" +
                "G) Play Game\n" +
                "N) Print Path To Cursor\n" +
                "M) Move scene\n" +
                "Q) Quit\n";
        System.out.println("Creating a story...");

        // Setup Root
        System.out.print("\nPlease enter a title: ");
        String title = stdin.nextLine();
        System.out.print("Please enter a scene: ");
        String desc = stdin.nextLine();
        try {
            sceneTree.addNewNode(title, desc);
            System.out.println("Root Successfully Added!");
        } catch (FullSceneException e) {
            System.out.println("Sorry, the root is already defined!");
        } catch (Exception e) {
            System.out.println("Partial Functionality?");
        }

        while (true) {
            System.out.println(menu);
            System.out.print("\nPlease Enter a Selection: ");
            try {
                char selection = stdin.next().charAt(0);
                stdin.nextLine();
                switch (Character.toLowerCase(selection)) {
                    case 'a': // Add Scene (Prompts the user)
                        System.out.print("\nPlease enter a title: ");
                        title = stdin.nextLine();
                        System.out.print("Please enter a scene: ");
                        desc = stdin.nextLine();
                        try {
                            sceneTree.addNewNode(title, desc);
                            System.out.println("Scene Successfully Added!");
                        } catch (FullSceneException e) {
                            System.out.println("\nSorry, scenes are full!\n");
                        }
                        break;
                    case 'r': // Remove Scene
                        System.out.print("\nPlease enter an option: ");
                        try {
                            sceneTree.removeScene(stdin.next().charAt(0));
                            System.out.println("Successfully Removed!");
                        } catch (NoSuchNodeException e) {
                            System.out.println("Node does not exist!");
                            stdin.nextLine();
                        }
                        break;
                    case 's': // Show Current Scene
                        sceneTree.getCursor().displayFullScene();
                        break;
                    case 'p': // Print Adventure Tree
                        System.out.println("\n" + sceneTree.toString());
                        break;
                    case 'b': // Go back a scene
                        try {
                            sceneTree.moveCursorBackwards();
                            System.out.println("\nSuccessfully moved back to " +
                                    sceneTree.getCursor().getTitle());
                        } catch (NoSuchNodeException e) {
                            System.out.println("Sorry, you are at the root");
                            stdin.nextLine();
                        }
                        break;
                    case 'f': // Go forward a scene
                        System.out.print("\nWhich option do you wish to go to: ");
                        try {
                            sceneTree.moveCursorForward(stdin.next().charAt(0));
                            System.out.println("Successfully moved to " +
                                    sceneTree.getCursor().getTitle());
                        } catch (NoSuchNodeException e) {
                            System.out.println("That option does not exist.");
                            stdin.nextLine();
                        }
                        break;
                    case 'g':
                        playGame();
                        break;
                    case 'n': // Print path to cursor
                        System.out.println("\n" + sceneTree.getPathFromRoot());
                        break;
                    case 'm': // Move Scene
                        System.out.print("\nMove current scene to: ");
                        int number = stdin.nextInt();
                        try {
                            sceneTree.moveScene(number);
                            System.out.println("Successfully Moved!");
                        } catch (NoSuchNodeException e) {
                            System.out.println("Node not Found!");
                            stdin.nextLine();
                        } catch (FullSceneException e) {
                            System.out.println("The scenes in that ID are full!");
                            stdin.nextLine();
                        }
                        break;
                    case 'q':
                        System.out.println("\nProgram terminating normally...");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("\nInvalid selection.");
                }
            } catch (Exception e) {
                System.out.println("Partial Functionality?");
                stdin.nextLine();
            }
        }
    }

    /**
     *  Attempts to play the game created by the user
     */
    public static void playGame() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("\nNow beginning game...");
        SceneNode tempCursor = sceneTree.getRoot();
        while (tempCursor != null) {
            System.out.println("\n" + tempCursor.getTitle());
            System.out.println(tempCursor.getSceneDescription());
            if (tempCursor.isEnding()) {
                break;
            }
            char c = 'A';
            if (tempCursor.getLeft() != null) {
                System.out.println(c + ") " + tempCursor.getLeft().getTitle());
                c++;
            }
            if (tempCursor.getMiddle() != null) {
                System.out.println(c + ") " + tempCursor.getMiddle().getTitle());
                c++;
            }
            if (tempCursor.getRight() != null) {
                System.out.println(c + ") " + tempCursor.getRight().getTitle());
            }

            System.out.print("\nPlease enter an option: ");
            char option = stdin.next().charAt(0);
            switch (Character.toLowerCase(option)) {
                case 'a':
                    if (tempCursor.getLeft() != null) {
                        tempCursor = tempCursor.getLeft();
                    }
                    break;
                case 'b':
                    if (tempCursor.getMiddle() != null) {
                        tempCursor = tempCursor.getMiddle();
                    }
                    break;
                case 'c':
                    if (tempCursor.getRight() != null) {
                        tempCursor = tempCursor.getRight();
                    }
                    break;
                default:
                    System.out.println("\nIncorrect Option. Please Try Again!");
            }
        }
        System.out.println("\nThe End.");
        System.out.println("\nReturning back to creation mode...\n");
    }
}