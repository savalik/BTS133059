import java.util.Stack;

public class Iterator<T> {

    private Tree<T>.Node<T> root = null;
    private Tree<T>.Node<T> current = null;
    private Stack<Tree<T>.Node<T>> temporary = new Stack<>();
    private Stack<Tree<T>.Node<T>> nextNodes = new Stack<>();
    private Stack<Tree<T>.Node<T>> prevNodes = new Stack<>();

    Iterator (Tree<T>.Node<T> currentRoot) {
        root = currentRoot;
        // setToRoot(currentRoot); nullpointerexception because root = null by default
    }

    /** Установка на корень дерева */
    public void setToRoot (Tree<T>.Node<T> currentRoot) {
        root = currentRoot;
        current = currentRoot;
        if (root.getLeftChild() != null) {
            temporary.clear();
            traverseSubtree(root.getLeftChild());
            prevNodes.addAll(temporary);
        }
        if (root.getRightChild() != null) {
            temporary.clear();
            traverseSubtree(root.getRightChild());
            while (!temporary.empty()) {
                nextNodes.push(temporary.pop());
            }
        }
    }

    /** Проверка конца дерева */
    public boolean isLast () {
        return nextNodes.empty();
    }

    /** Доступ к данным текущего элемента дерева */
    public T getData () {
        return current.getdData();
    }

    public void setData(T data){
        current.setdData(data);
    }

    public int getKey () {
        return current.getiData();
    }

    /** Переход к следующему по значению ключа элементу дерева */
    public void goToNext () {
        if(isLast())
            System.out.println("Last node!");
        else {
            prevNodes.push(current);
            current = nextNodes.pop();
        }
    }

    /** переход к предыдущему по значению ключа элементу дерева */
    public void goToPrevious () {
        if (current == root)
            System.out.println("Already at root!");
        else {
            nextNodes.push(current);
            current = prevNodes.pop();
        }
    }

    // рекурсивное заполнение стеков
    private void traverseSubtree (Tree<T>.Node<T> node) {
        if (node.getLeftChild() != null) {
            traverseSubtree(node.getLeftChild());
        }
        temporary.push(node);
        if (node.getLeftChild() != null) {
            traverseSubtree(node.getLeftChild());
        }
    }
}
