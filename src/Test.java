import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//todo: причесать

public class Test {
    static Tree myTree = new Tree<Integer>();
    static Tree myTreeWorst = new Tree<Integer>();
    static int treeSize = 10;  //tree size 10 / 100 / 1000 / 10000 / 100000
    static int rngKey, rngData, averageHops, flagCount, counter = 0, arraySize = 100*30, arrCounter = 0;
    static statistics[] stat = new statistics[arraySize];

    private static class statistics {
        public String _case;
        public int treeSize;
        public String action;
        public int amount;

        public void print() {
            System.out.println(_case + ", " + treeSize + ", " + action + ", " + amount);
        }

        public statistics(String _case, int treeSize, String action, int amount) {
            this.amount = amount;
            this.action = action;
            this.treeSize = treeSize;
            this._case = _case;
        }
    }

    public static void main(String[] args) {

        mid_level();
        hard_level();

        mid_level_stat();
        hard_level_stat();

        for (int i = 0; i != arrCounter; i++) {
            stat[i].print();
        }

        System.out.println("__");
    }

    private static void hard_level_stat() {

        for (int k = 1; k < (arraySize / 30 )+ 1; k++) {
            for (int i = 1; i < 6; i++) {

                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j++) {
                    rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                    rngData = ThreadLocalRandom.current().nextInt();
                    myTree.addNode(rngKey, rngData,true);
                }

                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                    rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                    if (myTree.find(rngKey) != null) flagCount++;
                    counter++;
                    averageHops += myTree.getCounter();
                }
                stat[arrCounter++] = new statistics("worst", (int)Math.pow(treeSize, i), "search", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;

                flagCount = myTree.getLength();
                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                    rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                    counter++;
                    averageHops += myTree.getCounter();
                }
                stat[arrCounter++] = new statistics("worst", (int)Math.pow(treeSize, i), "delete", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;

                myTree.iterator.setToRoot(myTree.getRoot());
                myTree.iterator.goToFirst();
                for (int j = 1; j < myTree.getLength() + 1; j++) {
                    myTreeWorst.addNode(myTree.iterator.getKey(), myTree.iterator.getData());
                    counter++;
                    averageHops += myTreeWorst.getCounter();
                    if (j < myTree.getLength()) myTree.iterator.goToNext();
                }
                stat[arrCounter++] = new statistics("worst", (int)Math.pow(treeSize, i), "add", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;

                myTree.cleanTree(true);
                myTreeWorst.cleanTree(true);
            }
        }
    }

    private static void hard_level() {
        System.out.println("..........................................");
        System.out.println("Worst case");

        for (int i = 1; i < 6; i++) {

            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j++) {
                boolean ready = false;
                while(!ready) {
                    rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                    rngData = ThreadLocalRandom.current().nextInt();
                    if (myTree.addNode(rngKey, rngData,true)) {
                        //flagCount++;
                        ready = true;
                    }
                }
            }
            System.out.println("Tree size :" + myTree.getLength());

            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                if (myTree.find(rngKey) != null) flagCount++;
                counter++;
                averageHops += myTree.getCounter();
            }
            System.out.println("Average actions with nodes on search: " + (averageHops / counter) + " ( " + flagCount + " - nodes found successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;

            flagCount = myTree.getLength();
            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                counter++;
                averageHops += myTree.getCounter();
            }
            System.out.println("Average actions with nodes on delete: " + (averageHops / counter) + " ( " + (flagCount - myTree.getLength()) + " - nodes remove successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;

            myTree.iterator.setToRoot(myTree.getRoot());
            myTree.iterator.goToFirst();
            for (int j = 1; j < myTree.getLength() + 1; j++) {
                myTreeWorst.addNode(myTree.iterator.getKey(), myTree.iterator.getData());
                counter++;
                averageHops += myTreeWorst.getCounter();
                if (j < myTree.getLength()) myTree.iterator.goToNext();
            }
            System.out.println("Average actions with nodes on add: " + (averageHops / counter) + " ( " + (myTreeWorst.getLength()) + " - nodes add successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;

            myTree.cleanTree();
            myTreeWorst.cleanTree();
            System.out.println();
        }

        System.out.println("..........................................");
    }

    private static void mid_level_stat() {

        for (int k = 1; k < (arraySize / 30 )+ 1; k++) {
            for (int i = 1; i < 6; i++) {
                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j++) {
                    boolean ready = false;
                    while(!ready) {
                        rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                        rngData = ThreadLocalRandom.current().nextInt();
                        if (myTree.addNode(rngKey, rngData,true)) {
                            flagCount++;
                            ready = true;
                        }
                    }
                    counter++;
                    averageHops += myTree.getCounter();
                }
                stat[arrCounter++] = new statistics("mid", (int)Math.pow(treeSize, i), "add", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;

                myTree.iterator.setToRoot(myTree.getRoot());
                myTree.iterator.goToFirst();
                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                    if (myTree.find(myTree.iterator.getKey()) != null) flagCount++;
                    for (int y = 1; y < Math.pow(3, i); y++) myTree.iterator.goToNext();
                    counter++;
                    averageHops += myTree.getCounter();
                }
                stat[arrCounter++] = new statistics("mid", (int)Math.pow(treeSize, i), "search", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;

                myTree.iterator.setToRoot(myTree.getRoot());
                myTree.iterator.goToFirst();
                for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                    if (myTree.removeNode(myTree.iterator.getKey())) flagCount++;
                    for (int y = 1; y < Math.pow(3, i); y++) myTree.iterator.goToNext();
                    counter++;
                    averageHops += myTree.getCounter();
                }
                stat[arrCounter++] = new statistics("mid", (int)Math.pow(treeSize, i), "delete", (averageHops / counter));
                counter = 0;
                flagCount = 0;
                averageHops = 0;
                myTree.cleanTree(true);
            }
        }

    }

    private static void mid_level() {

        System.out.println("..........................................");
        System.out.println("Middle case");

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j++) {
                boolean ready = false;
                while(!ready) {
                    rngKey = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                    rngData = ThreadLocalRandom.current().nextInt();
                    if (myTree.addNode(rngKey, rngData)) {
                        flagCount++;
                        ready = true;
                    }
                }
                counter++;
                averageHops += myTree.getCounter();
            }
            System.out.println("Tree size :" + myTree.getLength());
            System.out.println("Average actions with nodes on add: " + (averageHops / counter) + " ( " + flagCount + " - nodes add successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;

            myTree.iterator.setToRoot(myTree.getRoot());
            myTree.iterator.goToFirst();
            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                if (myTree.find(myTree.iterator.getKey()) != null) flagCount++;
                for (int y = 1; y < Math.pow(3, i); y++) myTree.iterator.goToNext();
                counter++;
                averageHops += myTree.getCounter();
            }
            System.out.println("Average actions with nodes on search: " + (averageHops / counter) + " ( " + flagCount + " - nodes found successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;

            myTree.iterator.setToRoot(myTree.getRoot());
            myTree.iterator.goToFirst();
            for (int j = 1; j < (Math.pow(treeSize, i)) + 1; j = j + (int) Math.pow(3, i)) {
                if (myTree.removeNode(myTree.iterator.getKey())) flagCount++;
                for (int y = 1; y < Math.pow(3, i); y++) myTree.iterator.goToNext();
                counter++;
                averageHops += myTree.getCounter();
            }
            System.out.println("Average actions with nodes on delete: " + (averageHops / counter) + " ( " + flagCount + " - nodes remove successfully. " + counter + " attempts  )");
            counter = 0;
            flagCount = 0;
            averageHops = 0;


            myTree.cleanTree();
            System.out.println();
        }

        System.out.println("..........................................");
    }
}