/**
 * Created by Андрей on 01.03.2017.
 */
public interface ITree {
    int getLength();
    void cleanTree();
    boolean isEmpty();
    Node find(int key);
    void addNode(int key, double data);
    boolean removeNode(int key);
    //todo:iterator iterator();
    void throughTree(Node localRoot); // Lt -> t -> Rt  в рекурсивной форме
    //todo: удаление узла дерева с объеденением двух поддереьве
    void showTree();
    int getCounter();
}
