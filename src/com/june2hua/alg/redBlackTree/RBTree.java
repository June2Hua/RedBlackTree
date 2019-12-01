package com.june2hua.alg.redBlackTree;

class RBTree {
	private Node root;//���ڵ�
	public RBTree(){
		root=null;
	}
	//����
	public void leftRotate(Node root){//root��������ĸ�
		Node rootRightChild=root.right;//��������Һ���
		//�޸�root
		root.right=rootRightChild.left;
		if(rootRightChild.left!=null)
			rootRightChild.left.parent=root;
		//�޸�rootRightChild�ĸ��׽ڵ�
		rootRightChild.parent=root.parent;
		//��rootΪRBTree��root�����޸�RBTree��root
		if(rootRightChild.parent==null)
			this.root=rootRightChild;
		//�����޸�root��parent��Ӧ�ĺ���
		else{
			if(root.parent.left==root)
				root.parent.left=rootRightChild;
			else
				root.parent.right=rootRightChild;
		}
		//����rootRightChild������
		rootRightChild.left=root;
		root.parent=rootRightChild;
	}
	//����
	public void rightRotate(Node root){//rootΪ��������ĸ�
		Node rootLeftChild=root.left;//�����������
		//�޸�root
		root.left=rootLeftChild.right;
		if(rootLeftChild.right!=null)
			rootLeftChild.right.parent=root;
		//�޸�rootLeftChild�ĸ��׽ڵ�
		rootLeftChild.parent=root.parent;
		//��rootΪRBTree��root�����޸�RBTree��root
		if(rootLeftChild.parent==null)
			this.root=rootLeftChild;
		//�����޸�root��parent��Ӧ�ĺ���
		else{
			if(root.parent.left==root)
				root.parent.left=rootLeftChild;
			else
				root.parent.right=rootLeftChild;
		}
		//����rootLeftChild���Һ���
		rootLeftChild.right=root;
		root.parent=rootLeftChild;
	}
	
	
}
