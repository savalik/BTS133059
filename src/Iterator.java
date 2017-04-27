import java.util.Stack;

public class Iterator<T> {

    private Tree<T>.Node<T> root = null;
    private Tree<T>.Node<T> current = null;
    private Stack<Tree<T>.Node<T>> tempStack = new Stack<>();
    private Stack<Tree<T>.Node<T>> nextNodes = new Stack<>();
    private Stack<Tree<T>.Node<T>> prevNodes = new Stack<>();

    Iterator(Tree<T>.Node<T> currentRoot) {
        root = currentRoot;
        // setToRoot(currentRoot); nullpointerexception because root = null by default
    }

    private void clean() {
        tempStack.clear();
        nextNodes.clear();
        prevNodes.clear();
    }

    public void setToRoot(Tree<T>.Node<T> currentRoot) {
        clean();
        root = currentRoot;
        current = currentRoot;
        if (root.getLeftChild() != null) {
            tempStack.clear();
            traverseSubtree(root.getLeftChild());
            prevNodes.addAll(tempStack);
        }
        if (root.getRightChild() != null) {
            tempStack.clear();
            traverseSubtree(root.getRightChild());
            while (!tempStack.empty()) {
                nextNodes.push(tempStack.pop());
            }
        }
    }

    public boolean isLast() {
        return nextNodes.empty();
    }

    public T getData() {
        return current.getdData();
    }

    public void setData(T data) {
        current.setdData(data);
    }

    public int getKey() {
        return current.getiData();
    }

    public void goToNext() {
        if (nextNodes.empty())
            System.out.println("It a last node!");
        else {
            prevNodes.push(current);
            current = nextNodes.pop();
        }
    }

    public void goToPrevious() {
        if (prevNodes.empty())
            System.out.println("It a first node!");
        else {
            nextNodes.push(current);
            current = prevNodes.pop();
        }
    }

    public void goToFirst() {
        while (!prevNodes.empty()) {
            nextNodes.push(current);
            current = prevNodes.pop();
        }
    }

    private void traverseSubtree(Tree<T>.Node<T> node) {
        if (node.getLeftChild() != null) {
            traverseSubtree(node.getLeftChild());
        }
        tempStack.push(node);
        if (node.getRightChild() != null) {
            traverseSubtree(node.getRightChild());
        }
    }
}
