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
	//����һ���ڵ�node������Ĭ��Ϊ��ɫ
	public void insert(Node node){
		//����
		if(node==null)
			return ;
		//���Ϊ��һ�β���
		if(root==null){
			root=node;
			node.parent=null;
			return ;
		}
		//����ǵ�һ����
		Node tmp=root;
		Node parent;
		do{
			parent=tmp;//���游�׽ڵ�
			if(node.data<tmp.data)
				tmp=tmp.left;
			else
				tmp=tmp.right;
		}while(tmp!=null);
		tmp=node;
		node.parent=parent;
		ajustment(node);
	}
	//���µ���Ϊ�����
	public void ajustment(Node node){
		Node gparent;//���ڵ�ĸ��ڵ�
		//������ڵ�Ϊ��ɫ������Ҫ���е���
		while(node.parent!=null&&node.parent.color==Node.RED){
			gparent=node.parent.parent;//����Ϊ��		*******
			//�жϲ���Ľڵ����ڸ��ڵ�ĸ��ڵ����߻����ұ�
			if(node.parent==gparent.left){//���
				//�������ڵ�Ϊ��ɫ����ֱ�ӱ�ɫ
				if(gparent.right.color==Node.RED){
					node.parent.color=Node.BLACK;//��ǰ�ڵ��ǰһ����Ϊ��ɫ
					gparent.right.color=Node.BLACK;//��ǰ�ڵ��ǰһ����Ϊ��ɫ
					gparent.color=Node.RED;//��ǰ�ڵ��ǰǰһ����Ϊ��ɫ
				}
				//�������ڵ�Ϊ��ɫ���ҵ�ǰΪ������������
				else if(gparent.right.color==Node.BLACK&&node==node.parent.left){
					//��ɫ
					node.parent.color=Node.BLACK;
					gparent.color=Node.RED;
					//����
					rightRotate(gparent);
				}
				//�������ڵ�Ϊ��ɫ���ҵ�ǰΪ������,����
				else if(gparent.right.color==Node.BLACK&&node==node.parent.right){
					leftRotate(node.parent);
				}
			}else{//�ұ�
				//�������ڵ�Ϊ��ɫ����ֱ�ӱ�ɫ
				if(gparent.left.color==Node.RED){
					node.parent.color=Node.BLACK;//��ǰ�ڵ��ǰһ����Ϊ��ɫ
					gparent.left.color=Node.BLACK;//��ǰ�ڵ��ǰһ����Ϊ��ɫ
					gparent.color=Node.RED;//��ǰ�ڵ��ǰǰһ����Ϊ��ɫ
				}
				//�������ڵ�Ϊ��ɫ���ҵ�ǰΪ������������
				else if(gparent.left.color==Node.BLACK&&node==node.parent.left){
					//��ɫ
					node.parent.color=Node.BLACK;
					gparent.color=Node.RED;
					//����
					rightRotate(gparent);
				}
				//�������ڵ�Ϊ��ɫ���ҵ�ǰΪ������,����
				else if(gparent.left.color==Node.BLACK&&node==node.parent.right){
					leftRotate(node.parent);
				}
			}
		}
	}
	//ǰ�����
	public void preOrder(Node node){
		//�ݹ��������
		if(node==null)
			return ;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	//�������
	public void inOrder(Node node){
		//�ݹ��������
		if(node==null)
			return ;
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}
	//�������
	public void postOrder(Node node){
		//�ݹ��������
		if(node==null)
			return ;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}
}














