public class tree<k,v>{
	private class Node{
		k key;
		v value;
		Node left,right;
		public Node(k key,v value) {
			this.key=key;
			this.value=value;
			left=null;
			right=null;
		}
	}
	private int size=0;
	private Node root=null;
	private Node findNode(Object key) {
		Node thisNode=root;
		Comparable<k> cKey=(Comparable<k>) key;
		while(thisNode!=null) {
			int num=cKey.compareTo(thisNode.key);
			if(num<0) {
				thisNode=thisNode.left;
			}
			else if(num>0) {
				thisNode=thisNode.right;
			}
			else {
				return thisNode;
			}
		}
		return null;
	}
	public v find(Object key) {
		Node thisNode=findNode(key);
		if(thisNode==null) {
			return null;
		}
		else {
			return thisNode.value;
		}
	}
	private v helpAdd(k key,v val,Node node) {
		Comparable<k> cKey=(Comparable<k>) key;
		int num=cKey.compareTo(node.key);
		if(num<0) {
			if(node.left==null) {
				node.left=new Node(key,val);
				size++;
				return null;
			}
			helpAdd(key,val,node.left);
		}
		else if(num>0) {
			if(node.right==null) {
				node.right=new Node(key,val);
				size++;
				return null;
			}
			return helpAdd(key,val,node.right);
		}
		else if(num==0){
			v old=node.value;
			node.value=val;
			return old;
		}
		return null;
	}
	public v add(k key,v val) {
		if(root==null) {
			root=new Node(key,val);
			size++;
			return null;
		}
		return helpAdd(key,val,root);
	}
	public v remove(Object key) {
		Node child=findNode(key);
		if(child==null) {
			return null;
		}
		if(size==1) {
			root=null;
			size--;
			return child.value;
		}
		Node parent=findParent(key);
		if(child.left!=null&&child.right!=null) {
			Node find=find(child.right);
			Node helpFind=findParent(find.key);
			removeHelp(find,helpFind);
			child.key=find.key;
			v oldVal=child.value;
			child.value=find.value;
			return oldVal;
		}
		else {
			return removeHelp(child,parent);
		}
	}
	private Node findParent(Object key) {
		Comparable<k> cKey=(Comparable<k>) key;
		Node parent=root;
		Node children=root;
		while(children!=null) {
			int num=cKey.compareTo(children.key);
			if(num<0) {
				parent=children;
				children=children.left;
			}
			if(num>0) {
				parent=children;
				children=children.right;
			}
			if(num==0) {
				return parent;
			}
		}
		return null;
	}
	private v removeHelp(Node child,Node parent) {
	      if(child.left==null&&child.right==null) {
			if(parent.left==child) {
				parent.left=null;
			}
			if(parent.right==child) {
				parent.right=null;
			}
			size--;
			return child.value;
		}
		if(parent.left==null) {
			if(parent.left==child) {
				parent.left=child.right;
			}
			if(parent.right==child) {
				parent.right=child.right;
			}
			size--;
			return child.value;
		}
		if(child.right==null) {
			if(parent.left==child) {
				parent.left=child.left;
			}
			if(parent.right==child) {
				parent.right=child.left;
			}
			size--;
			return child.value;
		}
		return null;
	}
	private Node find(Node node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node;
	}
}