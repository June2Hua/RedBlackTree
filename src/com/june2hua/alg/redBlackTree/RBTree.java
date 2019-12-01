package com.june2hua.alg.redBlackTree;

class RBTree {
	private Node root;//根节点
	public RBTree(){
		root=null;
	}
	//左旋
	public void leftRotate(Node root){//root左旋对象的根
		Node rootRightChild=root.right;//保存变量右孩子
		//修改root
		root.right=rootRightChild.left;
		if(rootRightChild.left!=null)
			rootRightChild.left.parent=root;
		//修改rootRightChild的父亲节点
		rootRightChild.parent=root.parent;
		//若root为RBTree的root，则修改RBTree的root
		if(rootRightChild.parent==null)
			this.root=rootRightChild;
		//否则修改root的parent对应的孩子
		else{
			if(root.parent.left==root)
				root.parent.left=rootRightChild;
			else
				root.parent.right=rootRightChild;
		}
		//设置rootRightChild的左孩子
		rootRightChild.left=root;
		root.parent=rootRightChild;
	}
	//右旋
	public void rightRotate(Node root){//root为右旋对象的根
		Node rootLeftChild=root.left;//保存变量左孩子
		//修改root
		root.left=rootLeftChild.right;
		if(rootLeftChild.right!=null)
			rootLeftChild.right.parent=root;
		//修改rootLeftChild的父亲节点
		rootLeftChild.parent=root.parent;
		//若root为RBTree的root，则修改RBTree的root
		if(rootLeftChild.parent==null)
			this.root=rootLeftChild;
		//否则修改root的parent对应的孩子
		else{
			if(root.parent.left==root)
				root.parent.left=rootLeftChild;
			else
				root.parent.right=rootLeftChild;
		}
		//设置rootLeftChild的右孩子
		rootLeftChild.right=root;
		root.parent=rootLeftChild;
	}
	
	
}
