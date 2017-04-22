import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {


    static Tree myTree;
    static int TreeSize = 0, errorCode;
    static char TreeType;
    static boolean iteratorFlag = false;


    public static void main(String[] args) {
        errorCode = preparation();
        if (errorCode > 0)
            System.out.println("Error # " + errorCode);
        switch (TreeType) {
            case 'i':
                myTree = new Tree<Integer>();
                break;
            case 'd':
                myTree = new Tree<Double>();
                break;
            case 's':
                myTree = new Tree<String>();
                break;
            default:
                System.out.println("data type not selected");
        }
        getMenu();
    }

    private static int preparation() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (true) {
            System.out.println("Enter size (number of elements, 2 - 20) of the tree");
            if (sc.hasNextInt()) TreeSize = sc.nextInt();
            else sc.nextLine();
            if (TreeSize < 21 && TreeSize > 1)
                break;
        }

        while (flag) {
            System.out.println("Enter data type (i - integer, d - double, s- string) of the tree");
            switch (getCommand()) {
                case 'i':
                    TreeType = 'i';
                    flag = false;
                    break;
                case 'd':
                    TreeType = 'd';
                    flag = false;
                    break;
                case 's':
                    TreeType = 's';
                    flag = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("Thanks. Size of the tree - " + TreeSize + ". data type - " + TreeType);
        return 0;
    }


    private static void getMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter a character corresponding to one of the following commands:");
        System.out.println("a - get size of the tree");
        System.out.println("b - clean the tree");
        System.out.println("c - checking the tree for emptiness");
        System.out.println("d - delete element with given key");
        System.out.println("e - add element with given key");
        System.out.println("f - find element with given key");
        if (myTree.getRoot() == null) System.out.println("g - Fill the tree with random values");
        System.out.println("h - tree traversal  Lt -> t -> Rt  in recursive form");
        System.out.println("i - removing a node (based on the method of combining two subtrees (recursive))");
        System.out.println("j - show the tree");
        System.out.println("k - number hops of last operation");
        System.out.println("y - enter to iterator menu");
        System.out.println("x - exit");
        System.out.println("Enter command: ");

        switch (getCommand()) {
            case 'a':
                System.out.println("Size of the tree = " + myTree.getLength());
                getMenu();
                break;
            case 'b':
                myTree.cleanTree();
                getMenu();
                break;
            case 'c':
                if (myTree.isEmpty())
                    System.out.println("the tree is empty.");
                else
                    System.out.println("the tree is not empty.");
                getMenu();
                break;
            case 'd':
                int remKey = 0;
                while (true) {
                    System.out.println("Enter key of node that should be removed: (enter 'x' twice for escape)");
                    if (sc.hasNextInt()) {
                        remKey = sc.nextInt();
                        if (myTree.removeNode(remKey)) {
                            System.out.println("node with key " + remKey + " was successful delete");
                            break;
                        } else
                            System.out.println("same error during removing node with key " + remKey + ".");

                    } else {
                        if(getCommand() == 'x') break;
                        sc.nextLine();
                    }
                }
                getMenu();
                break;
            case 'e':
                int addKey = 0;
                while (true) {
                    System.out.println("Enter key of node that should be added: ");
                    if (sc.hasNextInt()) {
                        addKey = sc.nextInt();
                        switch (TreeType) {
                            case 'i':
                                int addInt;
                                while (true) {
                                    System.out.println("Enter integer data of node that should be added: ");
                                    if (sc.hasNextInt()) {
                                        addInt = sc.nextInt();
                                        myTree.addNode(addKey, addInt);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                            case 'd':
                                double addDouble;
                                while (true) {
                                    System.out.println("Enter double data of node that should be added: ");
                                    if (sc.hasNextDouble()) {
                                        addDouble = sc.nextDouble();
                                        myTree.addNode(addKey, addDouble);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                            case 's':
                                String addStr;
                                while (true) {
                                    System.out.println("Enter string data of node that should be added: ");
                                    if (sc.hasNext()) {
                                        addStr = sc.next().intern();
                                        myTree.addNode(addKey, addStr);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                        }
                        break;
                    } else sc.nextLine();
                }
                getMenu();
                break;
            case 'f':
                Tree.Node findNode;
                while (true) {
                    System.out.println("Enter key of searching node: ");
                    if (sc.hasNextInt()) {
                        findNode = myTree.find(sc.nextInt());
                        if (findNode != null) {
                            System.out.println("Data of the desired node: ");
                            findNode.displayNode();
                        } else
                            System.out.println("Node with such key was not found");
                        break;
                    } else sc.nextLine();
                }
                getMenu();
                break;
            case 'g':
                int rngKey;
                System.out.println("filling the tree...");
                for (int i = 1; i < TreeSize + 1; i++) {
                    rngKey = ThreadLocalRandom.current().nextInt(0, 128);
                    switch (TreeType) {
                        case 'i':
                            int randomInt = ThreadLocalRandom.current().nextInt();
                            myTree.addNode(rngKey, randomInt);
                            break;
                        case 'd':
                            double randomDouble = ThreadLocalRandom.current().nextDouble();
                            myTree.addNode(rngKey, randomDouble);
                            break;
                        case 's':
                            Random rng = new Random();
                            String randomStr = generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 8);
                            myTree.addNode(rngKey, randomStr);
                            break;
                    }
                }
                System.out.println("Done!");
                getMenu();
                break;
            case 'h':
                myTree.throughTree(myTree.getRoot());
                getMenu();
                break;
            case 'i':
                int remrKey = 0;
                while (true) {
                    System.out.println("Enter key of node that should be removed: ");
                    if (sc.hasNextInt()) {
                        remrKey = sc.nextInt();
                        myTree.recursiveRemove(remrKey, myTree.getRoot(), null, false);
                        break;
                    } else sc.nextLine();
                }
                getMenu();
                break;
            case 'j':
                myTree.showTree();
                getMenu();
                break;
            case 'k':
                System.out.println(myTree.getCounter() + " hops in last operations");
                getMenu();
                break;
            case 'y':
                getIteratorMenu();
                break;
            case 'x':
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private static char getCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext())
            return sc.next().charAt(0);
        else
            return 0;
    }

    private static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    private static void getIteratorMenu() {
        Scanner sc = new Scanner(System.in);

        if (!myTree.isEmpty()) {
            if (!iteratorFlag) {
                myTree.iterator.setToRoot(myTree.getRoot());
                iteratorFlag = true;
            }

            System.out.println("\nEnter a character corresponding to one of the following commands:");
            System.out.println("a - set iterator on root of tree");
            System.out.println("b - check end of tree");
            System.out.println("c - access current node data");
            System.out.println("d - hop to next node");
            System.out.println("e - hop to previos node");
            System.out.println("y - back to general menu");
            System.out.println("x - exit");

            switch (getCommand()) {
                case 'a':
                    myTree.iterator.setToRoot(myTree.getRoot());
                    getIteratorMenu();
                    break;
                case 'b':
                    if (myTree.iterator.isLast())
                        System.out.println("Current node is last");
                    else
                        System.out.println("Current node is not last");
                    getIteratorMenu();
                    break;
                case 'c':
                    myTree.iterator.getData();
                    System.out.println("Do you want change node data? (y - for change)");
                    if (getCommand() == 'y') {
                        switch (TreeType) {
                            case 'i':
                                int addInt;
                                while (true) {
                                    System.out.println("Enter integer data of node that should be added: ");
                                    if (sc.hasNextInt()) {
                                        addInt = sc.nextInt();
                                        myTree.iterator.setData(addInt);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                            case 'd':
                                double addDouble;
                                while (true) {
                                    System.out.println("Enter double data of node that should be added: ");
                                    if (sc.hasNextDouble()) {
                                        addDouble = sc.nextDouble();
                                        myTree.iterator.setData(addDouble);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                            case 's':
                                String addStr;
                                while (true) {
                                    System.out.println("Enter string data of node that should be added: ");
                                    if (sc.hasNext()) {
                                        addStr = sc.next().intern();
                                        myTree.iterator.setData(addStr);
                                        break;
                                    } else sc.nextLine();
                                }
                                break;
                        }
                    }
                    getIteratorMenu();
                    break;
                case 'd':
                    myTree.iterator.goToNext();
                    getIteratorMenu();
                    break;
                case 'e':
                    myTree.iterator.goToPrevious();
                    getIteratorMenu();
                    break;
                case 'y':
                    getMenu();
                    break;
                case 'x':
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("!!! Tree is empty. You cant use iterator with empty tree !!!");
            getMenu();
        }
    }
}