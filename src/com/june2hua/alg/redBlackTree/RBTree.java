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
	//插入一个节点node，插入默认为红色
	public void insert(Node node){
		//错误
		if(node==null)
			return ;
		//如果为第一次插入
		if(root==null){
			root=node;
			node.parent=null;
			return ;
		}
		//如果非第一插入
		Node tmp=root;
		Node parent;
		do{
			parent=tmp;//保存父亲节点
			if(node.data<tmp.data)
				tmp=tmp.left;
			else
				tmp=tmp.right;
		}while(tmp!=null);
		tmp=node;
		node.parent=parent;
		ajustment(node);
	}
	//重新调整为红黑树
	public void ajustment(Node node){
		Node gparent;//父节点的父节点
		//如果父节点为红色，则需要进行调整
		while(node.parent!=null&&node.parent.color==Node.RED){
			gparent=node.parent.parent;//可能为空		*******
			//判断插入的节点是在父节点的父节点的左边还是右边
			if(node.parent==gparent.left){//左边
				//如果叔叔节点为红色，则直接变色
				if(gparent.right.color==Node.RED){
					node.parent.color=Node.BLACK;//当前节点的前一代设为黑色
					gparent.right.color=Node.BLACK;//当前节点的前一代设为黑色
					gparent.color=Node.RED;//当前节点的前前一代设为红色
				}
				//如果叔叔节点为黑色，且当前为左子树，右旋
				else if(gparent.right.color==Node.BLACK&&node==node.parent.left){
					//变色
					node.parent.color=Node.BLACK;
					gparent.color=Node.RED;
					//右旋
					rightRotate(gparent);
				}
				//如果叔叔节点为黑色，且当前为右子树,左旋
				else if(gparent.right.color==Node.BLACK&&node==node.parent.right){
					leftRotate(node.parent);
				}
			}else{//右边
				//如果叔叔节点为红色，则直接变色
				if(gparent.left.color==Node.RED){
					node.parent.color=Node.BLACK;//当前节点的前一代设为黑色
					gparent.left.color=Node.BLACK;//当前节点的前一代设为黑色
					gparent.color=Node.RED;//当前节点的前前一代设为红色
				}
				//如果叔叔节点为黑色，且当前为左子树，右旋
				else if(gparent.left.color==Node.BLACK&&node==node.parent.left){
					//变色
					node.parent.color=Node.BLACK;
					gparent.color=Node.RED;
					//右旋
					rightRotate(gparent);
				}
				//如果叔叔节点为黑色，且当前为右子树,左旋
				else if(gparent.left.color==Node.BLACK&&node==node.parent.right){
					leftRotate(node.parent);
				}
			}
		}
	}
	//前序遍历
	public void preOrder(Node node){
		//递归结束条件
		if(node==null)
			return ;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	//中序遍历
	public void inOrder(Node node){
		//递归结束条件
		if(node==null)
			return ;
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}
	//后序遍历
	public void postOrder(Node node){
		//递归结束条件
		if(node==null)
			return ;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}
	//删除一个节点
	public void remove(Node node){
		//如果删除的为叶子节点，直接删除
		if(node.left==null&&node.right==null){
			if(node.parent.left==node)
				node.parent.left=null;
			else
				node.parent.right=null;
			node=null;//释放node空间
			return;
		}
		//如果删除的节点有左右孩子
		if(node.left!=null&&node.right!=null){
			//找到后继结点，右子树的最左下角节点
			Node replaceNode=node.right;
			while(replaceNode.left!=null)
				replaceNode=replaceNode.left;
			//如果删除根节点
			if(node==root){
				root=replaceNode;
			}else{//非根节点
				//更换node的父节点的孩子节点
				if(node.parent.left==node)
					node.parent.left=replaceNode;
				else
					node.parent.right=replaceNode;
			}
			//保存变量
			Node replaceChild=replaceNode.right;
			Node replaceParent=replaceNode.parent;
			boolean replaceColor=replaceNode.color;
			//被删除的节点为后继结点的父节点
			if(replaceParent.parent==node){
				replaceParent=replaceNode;
			}else{
				//代替节点的孩子上移
				if(replaceChild!=null)
					replaceChild.parent=replaceParent;
				replaceParent.left=replaceChild;
				
				//修改代替节点
				replaceNode.right=node.right;
				node.right.parent=replaceNode;
			}
			replaceNode.parent=node.parent;
			replaceNode.color=node.color;
			replaceNode.left=node.left;
			node.left.parent=replaceNode;
			if(replaceColor==Node.BLACK){
				//进行红黑树修正
				ajustmentForRemove(replaceChild,replaceParent);
			}
			return ;
		}
		//删除节点只有一个子树情况
		Node nodeChild;
		if(node.left==null)
			nodeChild=node.right;
		else
			nodeChild=node.left;
		//保存变量
		Node nodeParent=node.parent;
		boolean color=node.color;
		//修改
		nodeChild.parent=nodeParent;
		if(node==root){//若删除的为根节点
			root=node;
		}else{
			if(nodeParent.left==node){
				nodeParent.left=nodeChild;
			}else{
				nodeParent.right=nodeChild;
			}
		}
		if(color==Node.BLACK){
			//进行红黑树修正
			ajustmentForRemove(nodeChild,nodeParent);
		}
		node=null;//释放空间
	}
	//重新调整为红黑树
	public void ajustmentForRemove(Node node,Node parent){
		Node tmp;
		while((node!=root)&&(node==null||node.color==node.BLACK)){
			//左孩子
			if(parent.left==node){
				tmp=parent.right;//node的兄弟节点
				if(tmp.color==Node.RED){
					//修改颜色
					tmp.color=Node.BLACK;
					parent.color=Node.RED;
					//左旋
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
			}else{//右孩子
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























