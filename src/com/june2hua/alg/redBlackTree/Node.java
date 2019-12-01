package com.june2hua.alg.redBlackTree;

class Node {
	public static final boolean RED=false;//红节点标记为false
	public static final boolean BLACK=true;//黑节点标记为true
	public int data;//节点值
	public Node parent;//父节点
	public Node left;//左节点
	public Node right;//右节点
	public boolean color;//节点颜色
	//带颜色的构造函数
	public Node(int data,Node parent,Node left,Node right,boolean color){
		this.data=data;
		this.parent=parent;
		this.left=left;
		this.right=right;
		this.color=color;
	}
	//不带颜色的构造函数，默认为red
	public Node(int data,Node parent,Node left,Node right){
		this.data=data;
		this.parent=parent;
		this.left=left;
		this.right=right;
		this.color=RED;
	}
}








