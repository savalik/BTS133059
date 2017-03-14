public interface ITree {
    int getLength();
    void cleanTree();
    boolean isEmpty();
    Node find(int key);
    void addNode(int key, double data);
    boolean removeNode(int key);
    Iterator getIterator();
    void throughTree(Node localRoot); // Lt -> t -> Rt  в рекурсивной форме
    void recursiveRemove(int key, Node currentNode, Node parentNode, boolean isLeftChild);
    void showTree();
    int getCounter();
}
