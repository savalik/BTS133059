import java.util.Stack;

public class Iterator {

    private Tree.Node root = null;
    private Tree.Node current = null;
    private Stack<Tree.Node> temporary = new Stack<>();
    private Stack<Tree.Node> nextNodes = new Stack<>();
    private Stack<Tree.Node> prevNodes = new Stack<>();

    Iterator (Tree.Node currentRoot) {
        root = currentRoot;
        setToRoot();
        System.out.println(prevNodes + " " + nextNodes);
    }

    /** Установка на корень дерева */
    public void setToRoot () {
        current = root;
        if (root.leftChild != null) {
            temporary.clear();
            traverseSubtree(root.leftChild);
            prevNodes.addAll(temporary);
        }
        if (root.rightChild != null) {
            temporary.clear();
            traverseSubtree(root.rightChild);
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
    public double getData () {
        System.out.println(current.iData + ": " + current.dData);
        return current.dData;
    }

    /** Переход к следующему по значению ключа элементу дерева */
    public void goToNext () {
        prevNodes.push(current);
        current = nextNodes.pop();
    }

    /** переход к предыдущему по значению ключа элементу дерева */
    public void goToPrevious () {
        nextNodes.push(current);
        current = prevNodes.pop();
    }

    // рекурсивное заполнение стеков
    private void traverseSubtree (Tree.Node node) {
        if (node.leftChild != null) {
            traverseSubtree(node.leftChild);
        }
        temporary.push(node);
        if (node.rightChild != null) {
            traverseSubtree(node.rightChild);
        }
    }
}
