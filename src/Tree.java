import java.util.Stack;

/**
 * Created by Андрей on 01.03.2017.
 */
public class Tree implements ITree {

    private Node root;
    private int counter;
    private int length;

    Tree() {
        root = null;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void cleanTree() {
        root = null;
        System.gc();
        length = 0;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Node find(int key) {
        counter = 0;
        Node current = root;
        while (current.iData != key) {
            counter++;
            if (key < current.iData)
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null)
                return null;
        }
        return current;
    }

    @Override
    public void addNode(int key, double data) {
        length++;
        counter = 0;
        Node newNode = new Node();
        newNode.iData = key;
        newNode.dData = data;
        if (root == null)
            root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                counter++;
                parent = current;
                if (key < current.iData) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    if (current.iData != key) {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = newNode;
                            return;
                        }
                    }
                    else {
                        System.out.println("Node with same key (" + key + ") is already exists");
                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean removeNode(int key) {
        length--;
        counter = 0;
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.iData != key) {
            counter++;
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }

        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        } else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        else {
            Node successor = getSuccessor(current);

            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            successor.leftChild = current.leftChild;
        }
        return true;
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            counter++;
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    @Override
    public void throughTree(Node localRoot) {
        if (localRoot != null) {
            throughTree(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            throughTree(localRoot.rightChild);
        }
    }

    @Override
    public void showTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".....................................");
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.rightChild != null || temp.leftChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');

            }

            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        System.out.println(".....................................");
        counter = getLength();
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
