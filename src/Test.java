import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    static Tree myTree = new Tree<Integer>();
    static int treeSize = 10;  //tree size 10 / 100 / 1000 / 10000 / 100000
    static int rngKey, rngData, averageHops, counter=0;
    public static void main(String[] args) {
        myTree.addNode(rngKey = ThreadLocalRandom.current().nextInt(0, 128), rngData = ThreadLocalRandom.current().nextInt(0, 128));

        for (int i = 1; i < 6; i++) {

            for (int j = 1; j < (Math.pow(treeSize,i)); j++) {
                rngKey = ThreadLocalRandom.current().nextInt(1,  Integer.MAX_VALUE);
                rngData = ThreadLocalRandom.current().nextInt();
                myTree.addNode(rngKey, rngData);
            }
            System.out.println("Tree size :" + myTree.getLength());


            //После тестирования на экран выводятся размер дерева и средняя трудоѐмкость
            //операций поиска, вставки и удаления
            for (int j = 1; j < (Math.pow(treeSize,i)); j=j+(int)Math.pow(3,i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1,  Integer.MAX_VALUE);
                myTree.find(rngKey);
                counter++;
                averageHops+=myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter=0;
            System.out.println("Average actions with nodes on search: " + averageHops);

            for (int j = 1; j < (Math.pow(treeSize,i)); j=j+(int)Math.pow(3,i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1,  Integer.MAX_VALUE);
                rngData = ThreadLocalRandom.current().nextInt();
                myTree.addNode(rngKey,rngData);
                counter++;
                averageHops+=myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter=0;
            System.out.println("Average actions with nodes on adding: " + averageHops);

            for (int j = 1; j < (Math.pow(treeSize,i)); j=j+(int)Math.pow(3,i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1,  Integer.MAX_VALUE);
                myTree.removeNode(rngKey);
                counter++;
                averageHops+=myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter=0;
            System.out.println("Average actions with nodes on delete: " + averageHops);

            myTree.cleanTree();
            System.out.println();
        }
    }
}
