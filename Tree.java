package avlTree;
import avlTree.Node;

public class Tree {
	public Node root;
	public Tree() {
		this.root=null;
	}
	public Node search(int s) {
		Node current=this.root;
		while(current!=null) {
			if (s>current.data) {
				current=current.right;
			}
			else if (s<current.data) {
				current=current.left;
			}
			else if(s==current.data) {
				return current;
			}
		}
		return null;
	}
	public void add(int s) {
		Node newNode =new  Node (s);
		Node current=this.root;
		Node parent=current;
		if (root==null) {
			root=newNode;
			System.out.println(s + " has been added.");
			return;
		}
		while(current!=null) {
			parent=current;
			if (newNode.data>current.data) {
				current=current.right;
			}
			else if (newNode.data<current.data) {
				current=current.left;
			}
			else if(newNode.data==current.data) {
				System.out.println(s + " was already added.");
				return;
			}
		}
		if (newNode.data>parent.data) {
			parent.right=newNode;
			this.makeBalanced(root);
			System.out.println(s + " has been added.");
			return;
		}
		else {
			parent.left=newNode;
			this.makeBalanced(root);
			System.out.println(s + " has been added.");
			return;
		}
	}
	public void printThis() {
		this.printTree(this.root);
	}
	public void printTree(Node node) {
		if (node!=null) {
			this.printTree(node.left);
			System.out.println(node.data);
			this.printTree(node.right);
		}
	}
	public int getHeight(Node node) {
		if (node==null) {
			return -1;
		}
		else {
			return node.calculateHeight();
		}
	}
	public void remove(int s) {
		if (this.search(s)!=null) {
		System.out.println(s + " exists in the tree.");
		Node current=root;
		Node parent=null;
		boolean isALeftChild=false;
		while (current.data!=s) {
			parent=current;
			if (current.data>s) {
				current=current.left;
			}
			else {
				current=current.right;
			}
		}
		if (parent==null) {
			if (current.left==null && current.right==null) {
				root=null;
				System.out.println(s + " was the only element and has been deleted.");
				return;
			}
			else if (current.left==null) {
				root=current.right;
				this.makeBalanced(root);
				System.out.println(s + " has been deleted.");
				return;
			}
			else if (current.right==null) {
				root=current.left;
				this.makeBalanced(root);
				System.out.println(s + " has been deleted.");
				return;
			}
			else {
				this.deleteRoot(current);
				this.makeBalanced(root);
				System.out.println(s + " has been deleted.");
				return;
			}
		}
		if (parent.left==current) {
			isALeftChild=true;
		}
		if (current.left==null && current.right==null) {
			if (isALeftChild) {
				parent.left=null;
			}
			else {
				parent.right=null;
			}
			this.makeBalanced(root);
			System.out.println(s + " has been deleted.");
			return;
		}
		else if (current.left==null) {
			if (isALeftChild) {
				parent.left=current.right;
			}
			else {
				parent.right=current.right;
			}
			this.makeBalanced(root);
			System.out.println(s + " has been deleted.");
			return;
		}
		else if (current.right==null) {
			if (isALeftChild) {
				parent.left=current.left;
			}
			else {
				parent.right=current.left;
			}
			this.makeBalanced(root);
			System.out.println(s + " has been deleted.");
			return;
		}
		else {
			this.replaceAndDelete(current, parent,isALeftChild);
			this.makeBalanced(root);
			System.out.println(s + " has been deleted.");
			return;
		}
	}
		else {
			System.out.println(s + " does not existin tree.");
		}
	}
	public void replaceAndDelete(Node node,Node parent,boolean isALeftChild) {
		Node current=node.right;
		Node parent2=node;
		while(current.left!=null) {
			parent2=current;
			current=current.left;
		}
		if (isALeftChild) {
			parent.left=current;
		}
		else {
			parent.right=current;
		}
		if(parent2.left==current) {
			parent2.left=current.right;
			current.left=node.left;
			current.right=node.right;
		}
		else {
			current.left=parent2.left;
		}
	}
	public void deleteRoot(Node node) {
		Node parent2=node;
		Node current=node.right;
		while(current.left!=null) {
			parent2=current;
			current=current.left;
		}
		if(parent2.right==current) {
			current.left=node.left;
			root=current;
		}
		else {
			parent2.left=current.right;
			current.left=node.left;
			current.right=parent2;
			root=current;
			
		}
	}
	public Node makeBalanced(Node node) {
		if (node.left!=null) {
			node.left=this.makeBalanced(node.left);
		}
		if (node.right!=null) {
			node.right=this.makeBalanced(node.right);
		}
		if (balanceFactor(node)<-1) {
			if (balanceFactor(node.left)<0) {
				node=this.rightRotation(node);
			}
			else {
				node.left=leftRotation(node.left);
				node=rightRotation(node);
			}
		}
		if (balanceFactor(node)>1) {
			if (balanceFactor(node.right)>0) {
				node=this.leftRotation(node);
			}
			else {
				node.right=rightRotation(node.right);
				node=leftRotation(node);
			}
		}
		return node;
		
	}
	public int balanceFactor(Node node) {
		return this.getHeight(node.right)-this.getHeight(node.left);
	}
	public Node rightRotation(Node node) {
		Node temp=node.left;
		node.left=temp.right;
		temp.right=node;
		return temp;
		
	}
	public Node leftRotation(Node node) {
		Node temp=node.right;
		node.right=temp.left;
		temp.left=node;
		return temp;
	}


}
