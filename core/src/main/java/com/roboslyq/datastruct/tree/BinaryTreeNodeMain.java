package com.roboslyq.datastruct.tree;

import com.roboslyq.datastruct.Source;
import com.roboslyq.datastruct.SourceImpl1;

public class BinaryTreeNodeMain {

    public static void main(String[] args) {

        BinaryTreeUtil binaryTreeUtil = new BinaryTreeUtil();
        BinaryTreeNode headNode = generate();
        binaryTreeUtil.preOrder(headNode);
    }

    /**
     *           1
     *        2    4
     *    3           5
     *
     * @return
     */
    public static BinaryTreeNode generate(){
        int i=0;
        BinaryTreeNode<Source> headNode = new BinaryTreeNode<>();
        Source source1 = new SourceImpl1();
        source1.setId(++i);
        headNode.setT(source1);

        BinaryTreeNode<Source> left1 = new BinaryTreeNode<>();
        Source source2 = new SourceImpl1();
        source2.setId(++i);
        left1.setT(source2);
        headNode.setLeftNode(left1);

        BinaryTreeNode<Source> left11 = new BinaryTreeNode<>();
        Source source21 = new SourceImpl1();
        source21.setId(++i);
        left11.setT(source21);
        left1.setLeftNode(left11);

        BinaryTreeNode<Source> rigth11 = new BinaryTreeNode<>();
        Source source22 = new SourceImpl1();
        source22.setId(++i);
        rigth11.setT(source22);
        left1.setRightNode(rigth11);

        BinaryTreeNode<Source> right1 = new BinaryTreeNode<>();
        Source source3 = new SourceImpl1();
        source3.setId(++i);
        right1.setT(source3);

        headNode.setRightNode(right1);
        return  headNode;
    }
}
