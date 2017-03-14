import java.util.Stack;

public class Tree implements ITree {

    private Node root;
    private int counter;
    private int length;

    Tree() {
        root = null;
    }

    public Iterator getIterator() {
        return new Iterator(root);
    }

    public Node getRoot() {
        return root;
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
        while (current.getiData() != key) {
            counter++;
            if (key < current.getiData())
                current = current.getLeftChild();
            else
                current = current.getRightChild();
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
        newNode.setiData(key);
        newNode.setdData(data);
        if (root == null) {
            root = newNode;
            counter++;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                counter++;
                parent = current;
                if (key < current.getiData()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                } else {
                    if (current.getiData() != key) {
                        current = current.getRightChild();
                        if (current == null) {
                            parent.setRightChild(newNode);
                            return;
                        }
                    } else {
                        System.out.println("Node with same key (" + key + ") is already exists");
                        length--;
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

        while (current.getiData() != key) {
            counter++;
            parent = current;
            if (key < current.getiData()) {
                isLeftChild = true;
                current = current.getLeftChild();
            } else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            if (current == null)
                return false;
        }

        if (current.getLeftChild() == null && current.getRightChild() == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.setLeftChild(null);
            else
                parent.setRightChild(null);
        } else if (current.getRightChild() == null)
            if (current == root)
                root = current.getLeftChild();
            else if (isLeftChild)
                parent.setLeftChild(current.getLeftChild());
            else
                parent.setRightChild(current.getLeftChild());

        else if (current.getLeftChild() == null)
            if (current == root)
                root = current.getRightChild();
            else if (isLeftChild)
                parent.setLeftChild(current.getRightChild());
            else
                parent.setRightChild(current.getRightChild());

        else {
            Node successor = getSuccessor(current);

            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.setLeftChild(successor);
            else
                parent.setRightChild(successor);

            successor.setLeftChild(current.getLeftChild());
        }
        return true;
    }

    private Node getSuccessor(Node delNode) {  //поиск наследника для замещения удаленной ноды
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.getRightChild();
        while (current != null) {
            counter++;
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != delNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(delNode.getRightChild());
        }
        return successor;
    }

    @Override
    public void throughTree(Node localRoot) {
        if (localRoot == root) counter = 0;

        if (localRoot != null) {
            throughTree(localRoot.getLeftChild());
            System.out.print(localRoot.getiData() + " ");
            counter++;
            if (getLength() == counter) System.out.println();
            throughTree(localRoot.getRightChild());
        }


    }

    @Override
    public void recursiveRemove(int key, Node current, Node parent, boolean isLeftChild) {
        if (parent == null)
            counter = 0;
        else
            counter++;

        if (current != null) if (key < current.getiData())
            recursiveRemove(key, current.getLeftChild(), current, true);
        else if (key != current.getiData())
            recursiveRemove(key, current.getRightChild(), current, false);
        else {
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                if (current == root)
                    root = null;
                else if (isLeftChild)
                    parent.setLeftChild(null);
                else
                    parent.setRightChild(null);
            } else if (current.getRightChild() == null)
                if (current == root)
                    root = current.getLeftChild();
                else if (isLeftChild)
                    parent.setLeftChild(current.getLeftChild());
                else
                    parent.setRightChild(current.getLeftChild());

            else if (current.getLeftChild() == null)
                if (current == root)
                    root = current.getRightChild();
                else if (isLeftChild)
                    parent.setLeftChild(current.getRightChild());
                else
                    parent.setRightChild(current.getRightChild());

            else {
                Node successor = getSuccessor(current);

                if (current == root)
                    root = successor;
                else if (isLeftChild)
                    parent.setLeftChild(successor);
                else
                    parent.setRightChild(successor);

                successor.setLeftChild(current.getLeftChild());
            }
        }
    }

    @Override
    public void showTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".....................................");
        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getiData());
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());

                    if (temp.getRightChild() != null || temp.getLeftChild() != null)
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
