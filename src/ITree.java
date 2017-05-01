public interface ITree<T> {
    int getLength();
    void cleanTree();
    boolean isEmpty();
    Tree<T>.Node<T> find(int key);
    boolean addNode(int key, T data);
    boolean removeNode(int key);
    Iterator<T> getIterator();
    void throughTree(Tree<T>.Node<T> localRoot); // Lt -> t -> Rt  в рекурсивной форме
    void recursiveRemove(int key, Tree<T>.Node<T> currentNode, Tree<T>.Node<T> parentNode, boolean isLeftChild); //удаление узла дерева с объеденением двух поддереьве в рекурсивной форме
    void showTree();
    int getCounter();
}
