package avlTree;

public class Node {
	public int data;
	public Node left;
	public Node right;
	public int height;
	public Node(int data) {
		this.data=data;
		this.left=null;
		this.right=null;
		this.height=-1;
	}
	public int calculateHeight() {
		int hL=-1;
		int hR=-1;
		if (this.left!=null) {
			hL=left.calculateHeight();
		}
		if (this.right!=null) {
			hR=right.calculateHeight();
		}
		return max(hR,hL)+1;
	}
	public int max(int a,int b) {
		if (a>=b) {
			return a;
		}
		return b;
	}

}
