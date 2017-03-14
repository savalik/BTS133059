public interface ITree {
    int getLength();
    void cleanTree();
    boolean isEmpty();
    Tree.Node find(int key);
    void addNode(int key, double data);
    boolean removeNode(int key);
    Iterator getIterator();
    void throughTree(Tree.Node localRoot); // Lt -> t -> Rt  в рекурсивной форме
    void recursiveRemove(int key, Tree.Node currentNode, Tree.Node parentNode, boolean isLeftChild); //todo: удаление узла дерева с объеденением двух поддереьве в рекурсивной форме
    void showTree();
    int getCounter();
}
