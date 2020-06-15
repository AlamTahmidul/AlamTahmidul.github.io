/**
 * @name Tahmidul Alam
 * @ID 112784865
 * @email tahmidul.alam@stonybrook.edu
 * @course CSE 214
 * @recitation Recitation 10 (Daniel Calabria)
 *
 *  The tree for SceneNode
 */
public class SceneTree {
    private SceneNode root;
    private SceneNode cursor;
    private String preOrderString;

    /**
     *  Default Constructor
     */
    public SceneTree() {
        this.root = null;
        this.cursor = null;
    }

    // Methods
    /**
     * @throws NoSuchNodeException
     *  Throw an exception if there is no parent of the node
     */
    public void moveCursorBackwards() throws NoSuchNodeException {
        if (this.cursor.getPrevious() == null) {
            throw new NoSuchNodeException();
        }
        this.cursor = this.cursor.getPrevious();
    }

    /**
     * @param option
     *  Moves cursor to the next available slot: left, middle, or right
     * @throws NoSuchNodeException
     *  Otherwise, no child node exists
     */
    public void moveCursorForward(char option) throws NoSuchNodeException {
        switch (Character.toLowerCase(option)) {
            case 'a':
                if (this.cursor.getLeft() != null)
                    this.cursor = this.cursor.getLeft();
                else throw new NoSuchNodeException();
                break;
            case 'b':
                if (this.cursor.getMiddle() != null)
                    this.cursor = this.cursor.getMiddle();
                else throw new NoSuchNodeException();
                break;
            case 'c':
                if (this.cursor.getRight() != null)
                    this.cursor = this.cursor.getRight();
                else throw new NoSuchNodeException();
                break;
            default:
                throw new NoSuchNodeException();
        }
    }

    /**
     * @param title
     *  The title of the new scene TBA
     * @param sceneDescription
     *  The description of the new scene TBA
     * @throws FullSceneException
     *  If all 3 nodes (left, middle, right) are full, throw an exception
     */
    public void addNewNode(String title, String sceneDescription)
            throws FullSceneException {
        SceneNode sceneNode = new SceneNode(title, sceneDescription);
        if (this.root == null) {
            sceneNode.setDepth(0);
            this.root = sceneNode;
            this.cursor = this.root;
            return;
        }
        if (this.cursor.getLeft() == null) {
            this.cursor.setLeft(sceneNode);
            this.cursor.getLeft().setPrevious(this.cursor);
//            this.cursor = this.cursor.getLeft();
            this.cursor.getLeft().setDepth(this.cursor.getDepth() + 1);
        } else if (this.cursor.getMiddle() == null) {
            this.cursor.setMiddle(sceneNode);
            this.cursor.getMiddle().setPrevious(this.cursor);
//            this.cursor = this.cursor.getMiddle();
            this.cursor.getMiddle().setDepth(this.cursor.getDepth() + 1);
        } else if (this.cursor.getRight() == null) {
            this.cursor.setRight(sceneNode);
            this.cursor.getRight().setPrevious(this.cursor);
//            this.cursor = this.cursor.getRight();
            this.cursor.getRight().setDepth(this.cursor.getDepth() + 1);
        } else {
            throw new FullSceneException();
        }
    }

    /**
     * @param option
     *  The node to remove (left, middle, or right)
     * @throws NoSuchNodeException
     *  If there is no SceneNode in left, middle, or right
     */
    public void removeScene(char option) throws NoSuchNodeException {
        switch (Character.toLowerCase(option)) {
            case 'a':
                if (this.cursor.getLeft() != null) {
//                    this.cursor.getLeft().setPrevious(null);
                    this.cursor.setLeft(null);
                } else throw new NoSuchNodeException();
                break;
            case 'b':
                if (this.cursor.getMiddle() != null) {
//                    this.cursor.getMiddle().setPrevious(null);
                    this.cursor.setMiddle(null);
                } else throw new NoSuchNodeException();
                break;
            case 'c':
                if (this.cursor.getRight() != null) {
//                    this.cursor.getRight().setPrevious(null);
                    this.cursor.setRight(null);
                } else throw new NoSuchNodeException();
                break;
            default:
                throw new NoSuchNodeException();
        }
    }

    /**
     * @param sceneIDToMoveTo
     *  The sceneID to move to
     * @throws NoSuchNodeException
     *  The user input exceeds the total number of scenes
     * @throws FullSceneException
     *  The scene can't be added
     */
    public void moveScene(int sceneIDToMoveTo)
            throws NoSuchNodeException, FullSceneException {
        if (sceneIDToMoveTo > SceneNode.getNumScenes())
            throw new NoSuchNodeException();
        preOrderMove(this.root, sceneIDToMoveTo);
    }

    /**
     * @param node
     *  The node to move
     * @param sceneIDToMoveTo
     *  The new parent of the node to be moved
     * @throws FullSceneException
     *  The parent has all children
     */
    private void preOrderMove(SceneNode node, int sceneIDToMoveTo)
            throws FullSceneException {
        if (node == null) return;
        if (node.getSceneID() == sceneIDToMoveTo) {
            SceneNode parent = this.cursor.getPrevious();
            if (node.getLeft() == null) {
                this.cursor.setPrevious(node);
                node.setLeft(this.cursor);
                this.cursor.setLeft(null);
                parent.setLeft(null);
            } else if (node.getMiddle() == null) {
                this.cursor.setPrevious(node);
                node.setMiddle(this.cursor);
                this.cursor.setMiddle(null);
                parent.setMiddle(null);
            } else if (node.getRight() == null) {
                this.cursor.setPrevious(node);
                node.setRight(this.cursor);
                this.cursor.setRight(null);
                parent.setRight(null);
            } else {
                throw new FullSceneException();
            }
            this.cursor.setDepth(this.cursor.getPrevious().getDepth() + 1);
        } else {
            preOrderMove(node.getLeft(), sceneIDToMoveTo);
            preOrderMove(node.getMiddle(), sceneIDToMoveTo);
            preOrderMove(node.getRight(), sceneIDToMoveTo);
        }
    }

    /**
     * @return
     *  String of the path to the current cursor
     */
    public String getPathFromRoot() {
        StringBuilder s = new StringBuilder();
        SceneNode tempCursor = this.cursor;
        while (tempCursor != null) {
            s.insert(0, ", " + tempCursor.getTitle());
            tempCursor = tempCursor.getPrevious();
        }
        if (s.toString().contains(", "))
            s = new StringBuilder(s.substring(s.indexOf(", ") + 2));
        return s.toString();
    }

    /**
     *  Puts the preOrder String of the SceneTree (titles of nodes) from the
     *  root
     */
    private void preOrder(SceneNode sceneNode, String preOrderString, char c) {
        if (sceneNode == null) {
            return;
        }
        if (this.cursor.getSceneID() == sceneNode.getSceneID()) {
            if (sceneNode.getSceneID() == this.root.getSceneID()) {
                this.preOrderString += String.format("%c) ", c) + sceneNode.toString() + " " +
                        "(*)\n";
            } else {
                this.preOrderString += "  ".repeat(sceneNode.getDepth())
                        + String.format("%c) ", c) + sceneNode.toString() +
                        " (*)\n";
            }
        } else {
            this.preOrderString += "  ".repeat(sceneNode.getDepth())
                    + String.format("%c) ", c) + sceneNode.toString() +
                    "\n";
        }
        preOrder(sceneNode.getLeft(), this.preOrderString,
                'A');
        preOrder(sceneNode.getMiddle(), this.preOrderString,
                'B');
        preOrder(sceneNode.getRight(), this.preOrderString,'C');
    }

    /**
     * @return
     *  Uses pre-order to set up the string
     */
    @Override
    public String toString() {
        this.preOrderString = "";
        preOrder(this.root, "", 'A');
        return preOrderString.substring(preOrderString.indexOf("A) ")+3);
    }

    /**
     * @return
     *  Returns the cursor Node
     */
    public SceneNode getCursor() {
        return cursor;
    }

    /**
     * @return
     *  Returns the root node
     */
    public SceneNode getRoot() {
        return root;
    }

    /**
     * @param cursor
     *  Sets the cursor ndoe
     */
    public void setCursor(SceneNode cursor) {
        this.cursor = cursor;
    }

    /**
     * @param root
     *  Sets the root node
     */
    public void setRoot(SceneNode root) {
        this.root = root;
    }
}

/**
 *  Exception if there is no parent/child of a node
 */
class NoSuchNodeException extends Exception {
    public NoSuchNodeException() {}
}