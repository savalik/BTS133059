import java.util.Stack;

public class Tree<T> implements ITree<T> {

    private Node<T> root;
    private int counter;
    private int length;

    public class Node<T> {
        private int iData;
        private T dData;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public int getiData() {
            counter++;
            return iData;
        }

        public void setiData(int iData) {
            counter++;
            this.iData = iData;
        }

        public T getdData() {
            counter++;
            return dData;
        }

        public void setdData(T dData) {
            counter++;
            counter++;
            this.dData = dData;
        }

        public Node<T> getLeftChild() {
            counter++;
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            counter++;
            this.leftChild = leftChild;
        }

        public Node<T> getRightChild() {
            counter++;
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            counter++;
            this.rightChild = rightChild;
        }

        public void displayNode() {
            System.out.print('{');
            System.out.print(iData);
            System.out.print(". ");
            System.out.print(dData);
            System.out.print('}');
        }
    }

    Tree() {
        root = null;
    }

    public Iterator<T> getIterator() {
        return new Iterator<T>(root);
    }

    Node<T> getRoot() {
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
        System.out.println("The tree has been clean");
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Node<T> find(int key) {
        counter = 0;
        Node<T> current = root;
        while (current.getiData() != key) {
            //counter++;
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
    public void addNode(int key, T data) {
        length++;
        counter = 0;
        Node<T> newNode = new Node<T>();
        newNode.setiData(key);
        newNode.setdData(data);
        if (root == null) {
            root = newNode;
            //counter++;
        } else {
            Node<T> current = root;
            Node<T> parent;
            while (true) {
                //counter++;
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
        if(root == null) {
            System.out.println("Tree is empty! Division by zero! Nothing to remove!");
            return false;
        }
        else {
            length--;
            counter = 0;
            Node<T> current = root;
            Node<T> parent = root;
            boolean isLeftChild = true;

            while (current.getiData() != key) {
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
                Node<T> successor = getSuccessor(current);

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
    }

    private Node<T> getSuccessor(Node<T> delNode) {  //поиск наследника для замещения удаленной ноды
        Node<T> successorParent = delNode;
        Node<T> successor = delNode;
        Node<T> current = delNode.getRightChild();
        while (current != null) {
            //counter++;
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
    public void throughTree(Node<T> localRoot) {
        if (localRoot == root) counter = 0;

        if (localRoot != null) {
            throughTree(localRoot.getLeftChild());
            System.out.print(localRoot.getiData() + " ");
            //counter++;
            if (getLength() == counter) System.out.println();
            throughTree(localRoot.getRightChild());
        }


    }

    @Override
    public void recursiveRemove(int key, Node<T> current, Node<T> parent, boolean isLeftChild) {
        if (parent == null)
            counter = 0;

        if (current != null) if (key < current.getiData())
            recursiveRemove(key, current.getLeftChild(), current, true);
        else if (key != current.getiData())
            recursiveRemove(key, current.getRightChild(), current, false);
        else {
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                if (current == root)
                    root = null;
                else if (isLeftChild) {
                    assert parent != null;
                    parent.setLeftChild(null);
                }
                else {
                    assert parent != null;
                    parent.setRightChild(null);
                }
            } else if (current.getRightChild() == null)
                if (current == root)
                    root = current.getLeftChild();
                else if (isLeftChild) {
                    assert parent != null;
                    parent.setLeftChild(current.getLeftChild());
                }
                else {
                    assert parent != null;
                    parent.setRightChild(current.getLeftChild());
                }

            else if (current.getLeftChild() == null)
                if (current == root)
                    root = current.getRightChild();
                else if (isLeftChild) {
                    assert parent != null;
                    parent.setLeftChild(current.getRightChild());
                }
                else {
                    assert parent != null;
                    parent.setRightChild(current.getRightChild());
                }

            else {
                Node<T> successor = getSuccessor(current);

                if (current == root)
                    root = successor;
                else if (isLeftChild) {
                    assert parent != null;
                    parent.setLeftChild(successor);
                }
                else {
                    assert parent != null;
                    parent.setRightChild(successor);
                }

                successor.setLeftChild(current.getLeftChild());
            }
        }
    }

    @Override
    public void showTree() {
        Stack<Node<T>> globalStack = new Stack<Node<T>>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("\n................................................................");
        while (!isRowEmpty) {
            Stack<Node<T>> localStack = new Stack<Node<T>>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                Node<T> temp = globalStack.pop();
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
        System.out.println("\n................................................................");
        counter = getLength();
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
