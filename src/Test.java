import java.util.concurrent.ThreadLocalRandom;

public class Test {
    static Tree myTree = new Tree<Integer>();
    static Tree myTreeWorst = new Tree<Integer>();
    static int treeSize = 10;  //tree size 10 / 100 / 1000 / 10000 / 100000
    static int rngKey, rngData, averageHops, counter = 0;

    public static void main(String[] args) {

        mid_level();
        hard_level();

    }


    private static void hard_level() {
        System.out.println("..........................................");
        System.out.println("Worst case");

        for (int i = 1; i < 6; i++) {

            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j++) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                rngData = ThreadLocalRandom.current().nextInt();
                myTree.addNode(rngKey, rngData);
            }
            System.out.println("Tree size :" + myTree.getLength());

            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                rngData = ThreadLocalRandom.current().nextInt();
                myTree.addNode(rngKey, rngData);
                counter++;
                averageHops += myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter = 0;
            System.out.println("Average actions with nodes on adding: " + averageHops);

            for (int j = 1; j < (Math.pow(treeSize, i)); j = j + (int) Math.pow(3, i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                myTree.removeNode(rngKey);
                counter++;
                averageHops += myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter = 0;
            System.out.println("Average actions with nodes on delete: " + averageHops);

            myTree.iterator.setToRoot(myTree.getRoot());

            for (int j = 1; j  < myTree.getLength() +1; j++){
               // myTreeWorst.addNode();
            }

            myTree.cleanTree();
            System.out.println();
        }

        System.out.println("..........................................");
    }

    private static void mid_level() {

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                myTree.find(rngKey);
                counter++;
                averageHops += myTree.getCounter();
            }
            averageHops = averageHops / counter;
            counter = 0;
            System.out.println("Average actions with nodes on search: " + averageHops);
        }
    }
}