package com.june2hua.alg.redBlackTree;

class Node {
	public static final boolean RED=false;//��ڵ���Ϊfalse
	public static final boolean BLACK=true;//�ڽڵ���Ϊtrue
	public int data;//�ڵ�ֵ
	public Node parent;//���ڵ�
	public Node left;//��ڵ�
	public Node right;//�ҽڵ�
	public boolean color;//�ڵ���ɫ
	//����ɫ�Ĺ��캯��
	public Node(int data,Node parent,Node left,Node right,boolean color){
		this.data=data;
		this.parent=parent;
		this.left=left;
		this.right=right;
		this.color=color;
	}
	//������ɫ�Ĺ��캯����Ĭ��Ϊred
	public Node(int data,Node parent,Node left,Node right){
		this.data=data;
		this.parent=parent;
		this.left=left;
		this.right=right;
		this.color=RED;
	}
}








