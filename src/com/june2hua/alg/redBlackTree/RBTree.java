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
	//ɾ��һ���ڵ�
	public void remove(Node node){
		//���ɾ����ΪҶ�ӽڵ㣬ֱ��ɾ��
		if(node.left==null&&node.right==null){
			if(node.parent.left==node)
				node.parent.left=null;
			else
				node.parent.right=null;
			node=null;//�ͷ�node�ռ�
			return;
		}
		//���ɾ���Ľڵ������Һ���
		if(node.left!=null&&node.right!=null){
			//�ҵ���̽�㣬�������������½ǽڵ�
			Node replaceNode=node.right;
			while(replaceNode.left!=null)
				replaceNode=replaceNode.left;
			//���ɾ�����ڵ�
			if(node==root){
				root=replaceNode;
			}else{//�Ǹ��ڵ�
				//����node�ĸ��ڵ�ĺ��ӽڵ�
				if(node.parent.left==node)
					node.parent.left=replaceNode;
				else
					node.parent.right=replaceNode;
			}
			//�������
			Node replaceChild=replaceNode.right;
			Node replaceParent=replaceNode.parent;
			boolean replaceColor=replaceNode.color;
			//��ɾ���Ľڵ�Ϊ��̽��ĸ��ڵ�
			if(replaceParent.parent==node){
				replaceParent=replaceNode;
			}else{
				//����ڵ�ĺ�������
				if(replaceChild!=null)
					replaceChild.parent=replaceParent;
				replaceParent.left=replaceChild;
				
				//�޸Ĵ���ڵ�
				replaceNode.right=node.right;
				node.right.parent=replaceNode;
			}
			replaceNode.parent=node.parent;
			replaceNode.color=node.color;
			replaceNode.left=node.left;
			node.left.parent=replaceNode;
			if(replaceColor==Node.BLACK){
				//���к��������
				ajustmentForRemove(replaceChild,replaceParent);
			}
			return ;
		}
		//ɾ���ڵ�ֻ��һ���������
		Node nodeChild;
		if(node.left==null)
			nodeChild=node.right;
		else
			nodeChild=node.left;
		//�������
		Node nodeParent=node.parent;
		boolean color=node.color;
		//�޸�
		nodeChild.parent=nodeParent;
		if(node==root){//��ɾ����Ϊ���ڵ�
			root=node;
		}else{
			if(nodeParent.left==node){
				nodeParent.left=nodeChild;
			}else{
				nodeParent.right=nodeChild;
			}
		}
		if(color==Node.BLACK){
			//���к��������
			ajustmentForRemove(nodeChild,nodeParent);
		}
		node=null;//�ͷſռ�
	}
	//���µ���Ϊ�����
	public void ajustmentForRemove(Node node,Node parent){
		Node tmp;
		while((node!=root)&&(node==null||node.color==node.BLACK)){
			//����
			if(parent.left==node){
				tmp=parent.right;//node���ֵܽڵ�
				if(tmp.color==Node.RED){
					//�޸���ɫ
					tmp.color=Node.BLACK;
					parent.color=Node.RED;
					//����
					leftRotate(parent);
					tmp=parent.right;
				}
				if((tmp.left==null||tmp.left.color==Node.BLACK)&&(tmp.right==null||tmp.right.color==Node.BLACK)){
					tmp.color=Node.RED;
					node=parent;
					parent=node.parent;
				}else{
					if(tmp.right==null||tmp.right.color==Node.BLACK){
						tmp.left.color=Node.BLACK;
						tmp.color=Node.RED;
						rightRotate(tmp);
						tmp=parent.right;
					}
					tmp.color=parent.color;
					parent.color=Node.BLACK;
					tmp.right.color=Node.BLACK;
					leftRotate(parent);
					node=root;
					break;
				}
			}else{//�Һ���
				tmp=parent.left;
				if(tmp.color==Node.RED){
					tmp.color=Node.BLACK;
					parent.color=Node.RED;
					rightRotate(parent);
					tmp=parent.left;
				}
				if((tmp.left==null||tmp.left.color==Node.BLACK)&&(tmp.right==null||tmp.right.color==Node.BLACK)){
					tmp.color=Node.RED;
					node=parent;
					parent=node.parent;
				}else{
					if(tmp.left==null||tmp.left.color==Node.BLACK){
						tmp.right.color=Node.BLACK;
						tmp.color=Node.RED;
						leftRotate(tmp);
						tmp=parent.left;
					}
					tmp.color=parent.color;
					parent.color=Node.BLACK;
					tmp.left.color=Node.BLACK;
					rightRotate(parent);
					node=root;
					break;
				}
			}
		}
		if(node!=null)
			node.color=Node.BLACK;
	}
}























