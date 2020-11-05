import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class AVL {
    public static void main(String args[]) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("feast.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(k.nextToken());
        int list[] = new int[n];

        k = new StringTokenizer(f.readLine());

        node root =null;
        for( int i=0; i< n; i++){
            int key = Integer.parseInt(k.nextToken());
            root = insert(root, key);
        }

        inOrder(root);
    }
    public static void inOrder(node root){
        if(root!= null){
            inOrder(root.left);
            if(Math.abs(balance(root)) >1) {
                System.out.println("imbalance at " + root.key);
            }

            System.out.print(root.key + " has children: ");
            if(root.left!=null){
                System.out.print(root.left.key + " ");
            }
            if(root.right!=null){
                System.out.print(root.right.key + " ");
            }

            System.out.println();
            inOrder(root.right);
        }
    }

    public static int balance(node root){
        return height(root.left) - height(root.right);
    }

    public static node leftRotate(node root){
        node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    public static node rightRotate(node root){
        node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    public static node insert(node root, int key){
        if(root == null){
            return (new node(key));
        }

        if(key < root.key){
            root.left= insert(root.left, key);
        }
        else
            root.right = insert(root.right, key);

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = height(root.left) - height(root.right);

        // Check for imbalances
        // right right case
        if(balance < -1 && key > root.right.key){
            return leftRotate(root);
        }
        //right left case
        if(balance < -1 && key < root.right.key){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        //left left case
        if(balance >1 && key < root.left.key){
            return rightRotate(root);
        }
        // left right case
        if(balance >1 && key > root.left.key){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // no imbalance, so just return
        return root;
    }

    public static int height(node find){
        if(find==null){
            return 0;
        }
        return find.height;
    }

}
class node {
    int key;
    node left;
    node right;
    int height;

    public node(int key){
        this.key = key;
        this.height = 1;
        this.left =null;
        this.right=null;
    }
}