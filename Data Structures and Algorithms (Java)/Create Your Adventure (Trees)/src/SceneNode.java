/**
 *  The main nodes for scenes
*/
public class SceneNode {
    private String title;
    private String sceneDescription;
    private int sceneID;
    private SceneNode left = null;
    private SceneNode middle = null;
    private SceneNode right = null;
    private SceneNode previous = null;
    private static int numScenes = 0;
    private int depth = 0;

    /**
     *  Default Constructor with default values
     */
    public SceneNode() {
        this.title = "Default Title";
        this.sceneDescription = "Default Description";
        this.sceneID = 0;
    }

    /**
     *  Default Constructor with custom values
     *
     * @param title
     *  Sets the scene title
     * @param sceneDescription
     *  Sets the scene description
     */
    public SceneNode(String title, String sceneDescription) {
        this.title = title;
        this.sceneDescription = sceneDescription;
        this.sceneID = ++numScenes;
    }

    // Methods
    /**
     * @param scene
     *  Adds a scene to a particular node: Left, Middle, Right
     * @throws FullSceneException
     *  Throws an exception if left, middle, and right nodes are not null
     */
    public void addScene(SceneNode scene) throws FullSceneException {
        if (this.left == null) {
            this.left = scene;
        } else if (this.middle == null) {
            this.middle = scene;
        } else if (this.right == null) {
            this.right = scene;
        } else {
            throw new FullSceneException();
        }
    }

    /**
     * @return
     *  Returns true if there are no more children of the node (The end of
     *  the path).
     */
    public boolean isEnding() {
        return (this.left == null && this.right == null && this.middle == null);
    }

    /**
     *  Prints out the scene in the game
     */
    public void displayScene() {
        // Display the current scene
        System.out.println(this.title);
        System.out.println(this.sceneDescription + "\n");
        char path = 'A';
        String outputFormat = "%s) %s";
        if (this.left != null) {
            System.out.format(outputFormat, path, this.left.getTitle() + "\n");
            path++;
        }
        if (this.middle != null) {
            System.out.format(outputFormat, path, this.middle.getTitle() + "\n");
            path++;
        }
        if (this.right != null) {
            System.out.format(outputFormat, path, this.right.getTitle() + "\n");
        }
        // Must be the end
        if (this.left == null && this.right == null && this.middle == null) {
            System.out.println("\nThe End.");
        }
    }

    /**
     * Prints out the scene in the Creation Mode
     */
    public void displayFullScene() {
        // Display "Leads To: _____"
        String outputFormat = "'%s' (#%d)";
        String output = "";
        if (this.left != null) {
            output += String.format(outputFormat, this.left.getTitle(),
                    this.left.getSceneID()) + ", ";
        }
        if (this.middle != null) {
            output += String.format(outputFormat, this.middle.getTitle(),
                    this.middle.getSceneID()) + ", ";
        }
        if (this.right != null) {
            output += String.format(outputFormat, this.right.getTitle(),
                    this.right.getSceneID());
        }
        if (this.left == null && this.middle == null && this.right == null) {
            output += "NONE";
        }

        // Final Output
        System.out.println("Scene ID #" + this.sceneID);
        System.out.println("Title: " + this.title);
        System.out.println("Scene: " + this.sceneDescription);
        if (output.lastIndexOf(", ") >= 0) {
            output = output.substring(0,output.lastIndexOf(", "));
        }
        System.out.print("Leads to: " + output + "\n");
    }

    /**
     * @return
     *  Returns a String representation of the Node class
     */
    @Override
    public String toString() {
        return this.title + String.format(" (#%s)", this.sceneID);
    }

    // Getters
    /**
     * @return
     *  Returns the total number of scenes in the tree
     */
    public static int getNumScenes() {
        return numScenes;
    }

    /**
     * @return
     *  Returns the current scene number
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * @return
     *  Returns the 1st path of the current scene
     */
    public SceneNode getLeft() {
        return left;
    }

    /**
     * @return
     *  Returns the 2nd path of the current scene
     */
    public SceneNode getMiddle() {
        return middle;
    }

    /**
     * @return
     *  Returns the 3rd path of the current scene
     */
    public SceneNode getRight() {
        return right;
    }

    /**
     * @return
     *  Returns the scene description
     */
    public String getSceneDescription() {
        return sceneDescription;
    }

    /**
     * @return
     *  Returns the scene title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return
     *  Returns the parent of the current scene
     */
    public SceneNode getPrevious() {
        return previous;
    }

    /**
     * @return
     *  Returns the depth of the node
     */
    public int getDepth() {
        return depth;
    }
// Setters
    /**
     * @param left
     *  Sets a (SceneNode) right child (the first path) of the current node
     */
    public void setLeft(SceneNode left) {
        this.left = left;
    }

    /**
     * @param middle
     *  Sets a (SceneNode) right child (the second path) of the current node
     */
    public void setMiddle(SceneNode middle) {
        this.middle = middle;
    }

    /**
     * @param numScenes
     *  Sets the total number of scenes
     */
    public static void setNumScenes(int numScenes) {
        SceneNode.numScenes = numScenes;
    }

    /**
     * @param right
     *  Sets a (SceneNode) right child (the third path) of the current node
     */
    public void setRight(SceneNode right) {
        this.right = right;
    }

    /**
     * @param sceneDescription
     *  Sets the (String) scene description
     */
    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    /**
     * @param sceneID
     *  Sets the (int) scene number
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * @param title
     *  Sets the (String) scene title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param previous
     *  Sets the parent of the current SceneNode
     */
    public void setPrevious(SceneNode previous) {
        this.previous = previous;
    }

    /**
     * @param depth
     *  Sets the depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
}

/**
 *  Exception class for checking if the left, middle, and right nodes are not
 *  null
 */
class FullSceneException extends Exception {
    public FullSceneException() {}
}