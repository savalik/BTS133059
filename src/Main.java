import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Tree myTree;
    static int TreeSize = 0, errorCode;
    static char TreeType;


    public static void main(String[] args) {
        errorCode = preparation();
        if (errorCode > 0)
            System.out.println("Error # " + errorCode);
        switch (TreeType){
            case 'i' : myTree = new Tree<Integer>();
                break;
            case 'd' : myTree = new Tree<Double>();
                break;
            case 's' : myTree = new Tree<String>();
                break;
            default: System.out.println("data type not selected");
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
        System.out.println("i - removing a node based on the method of combining two subtrees (recursive)");
        System.out.println("j - show the tree");
        System.out.println("k - number hops of last operation");
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
                    System.out.println("Enter key of node that should be removed: ");
                    if (sc.hasNextInt()) {
                        remKey = sc.nextInt();
                        if (myTree.removeNode(remKey))
                            System.out.println("node with key " + remKey + " was successful delete");
                        else
                            System.out.println("same error during removing node with key " + remKey + ".");
                        break;
                    } else sc.nextLine();
                }
                break;
          /*  case 'e':
                int addKey = 0;
                while (true) {
                    System.out.println("Enter key of node that should be added: ");
                    if (sc.hasNextInt()) {
                        addKey = sc.nextInt();
                        myTree.addNode();
                        break;
                    } else sc.nextLine();
                }

                break; */
            case 'f':
                TreeType = 's';

                break;
            case 'g':
                TreeType = 'i';

                break;
            case 'h':
                TreeType = 'd';

                break;
            case 'i':
                TreeType = 's';

                break;
            case 'j':
                TreeType = 'i';

                break;
            case 'k':
                TreeType = 'd';

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
        if (sc.hasNext())  // возвращает истинну если с потока ввода не пустой
            return sc.next().charAt(0);
        else
            return 0;
    }
}
