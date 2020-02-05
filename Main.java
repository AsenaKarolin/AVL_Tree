package avlTree;
import avlTree.Tree;

public class Main {

	public static void main(String[] args) {
		Tree myTree = new Tree();
		myTree.add(5);
		myTree.add(7);
		myTree.add(3);
		myTree.add(2);
		myTree.add(4);
		myTree.add(9);
		myTree.add(12);
		myTree.add(1);
		myTree.add(3);
		myTree.add(8);
		myTree.add(10);
		myTree.add(11);
		myTree.printThis();
		myTree.remove(9);
		myTree.remove(5);
		myTree.remove(12);

		myTree.printThis();
	}

}
