package datastructure;

/**
 * Created by sun on 2017/3/3.
 */

import java.io.IOException;
import java.util.*;
import java.util.LinkedList;

/**
 * 自己用链表实现的二叉树
 *
 * @param <E>
 */
class BinaryTree<E> {
    /**
     * 树结点类
     *
     * @param <E>
     */
    static class TreeNode<E> {
        E data;  //数据域
        TreeNode lchild;  //左孩子指针
        TreeNode rchild;  //右孩子指针

        public TreeNode() {
        }

        public TreeNode(E data, TreeNode lchild, TreeNode rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    private TreeNode root;  //根结点

    public BinaryTree() {
        root = new TreeNode();
    }

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 递归方式先序创建二叉树
     *
     * @param root 根结点
     */
    public void preOrderCreateRecursive(TreeNode root) {
        char ch = '\0';
        try {
            ch = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ch == '0') {
            root = null;    //为何没有执行
        } else {
            root.data = ch;
            root.lchild = new TreeNode();
            root.rchild = new TreeNode();
            preOrderCreateRecursive(root.lchild);
            preOrderCreateRecursive(root.rchild);
        }
    }

    /**
     * 递归的方式先序遍历二叉树
     *
     * @param root 根结点
     */
    public void preOrderTraverseRecursive(TreeNode root) {
        if (root != null) {
            visit((E) root.data);
            preOrderTraverseRecursive(root.lchild);
            preOrderTraverseRecursive(root.rchild);
        }
    }

    /**
     * 递归的方式中序遍历二叉树
     *
     * @param root 根结点
     */
    public void inOrderTraverseRecursive(TreeNode root) {
        if (root != null) {
            inOrderTraverseRecursive(root.lchild);
            visit((E) root.data);
            inOrderTraverseRecursive(root.rchild);
        }
    }

    /**
     * 递归的方式后序遍历二叉树
     *
     * @param root 根结点
     */
    public void postOrderTraverseRecursive(TreeNode root) {
        if (root != null) {
            postOrderTraverseRecursive(root.lchild);
            postOrderTraverseRecursive(root.rchild);
            visit((E) root.data);
        }
    }

    /**
     * 非递归的方式先序遍历二叉树
     * 原理：利用一个栈，先序遍历即根先遍历，根先入栈，再检查栈非空，移出栈顶元素，打印栈顶元素的值，
     * 因为栈后进先出，先将栈顶元素的右孩子入栈，再将左孩子入栈，即可实现先序遍历
     *
     * @param root 根结点
     */
    public void preOrderTraverseNonRecursive(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                visit((E) node.data);
                if (node.rchild != null) {
                    stack.push(node.rchild);
                }
                if (node.lchild != null) {
                    stack.push(node.lchild);
                }
            }
        }
    }

    /**
     * 非递归的方式中序遍历二叉树
     *
     * @param root 根结点
     */
    public void inOrderTraverseNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            //先将根结点入栈，再将左孩子入栈
            while (node != null) {
                stack.push(node);
                node = node.lchild;
            }
            //将栈顶元素出栈，再将右孩子入栈
            if (stack.size() > 0) {
                node = stack.pop();
                visit((E) node.data);
                node = node.rchild;
            }
        }
    }

    /**
     * 非递归方式后序遍历二叉树
     *
     * @param root 根结点
     */
    public void postOrderTraverseNonRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        TreeNode rNode = null;
        while (current != null || stack.size() > 0) {
            //先将根结点入栈，再处理左子树
            while (current != null) {
                stack.push(current);
                current = current.lchild;
            }
            current = stack.pop();
            //如果当前结点右孩子为空或者上一个访问的结点是右孩子,则访问当前结点
            while (current != null && (current.rchild == null || current.rchild == rNode)) {
                visit((E) current.data);
                rNode = current;
                if (stack.empty()) {
                    return;
                }
                current = stack.pop();
            }
            //继续处理右子树
            stack.push(current);
            current = current.rchild;
        }
    }

    /**
     * 层次遍历二叉树
     * 使用队列
     *
     * @param root 根结点
     */
    public void leverTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode node = root;
        queue.offer(node);
        while (queue.size() > 0) {
            node = queue.poll();
            visit((E) node.data);
            if (node.lchild != null) {
                queue.offer(node.lchild);
            }
            if (node.rchild != null) {
                queue.offer(node.rchild);
            }
        }
    }

    /**
     * 获取二叉树深度
     *
     * @param root 根结点
     * @return 深度
     */
    public int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.lchild);
        int right = getDepth(root.rchild);
        int depth = Math.max(left, right) + 1;
        return depth;
    }

    /**
     * 根据结点的数据域查找出所在的树结点
     *
     * @param root 根结点
     * @param e    数据
     * @return 数据所在的树结点
     */
    public TreeNode findNode(TreeNode root, E e) {
        TreeNode left = null;
        TreeNode right = null;
        if (root != null) {
            if (e.equals(root.data)) {
                return root;
            } else {
                left = findNode(root.lchild, e);
                right = findNode(root.rchild, e);
                if (left != null) {
                    return left;
                } else if (right != null) {
                    return right;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * 将一棵二叉树转换为它的镜像
     *
     * @param root 根结点
     */
    public void mirror(TreeNode root) {
        TreeNode node = null;
        if (root != null) {
            //将当前根结点左右孩子互换
            node = root.rchild;
            root.rchild = root.lchild;
            root.lchild = node;
            //处理原来的右子树
            mirror(root.lchild);
            //处理原来的左子树
            mirror(root.rchild);
        }
    }

    /**
     * 判断一棵树是否是对称的
     *
     * @param root 根结点
     * @return 如果这棵树是对称的，返回true;否则返回false
     */
    public boolean judgeSymmetric(TreeNode root) {
        return isSymmetric(root.lchild, root.rchild);
    }

    /**
     * 用递归的方式判断一棵树是否是对称的
     *
     * @param left  左孩子
     * @param right 右孩子
     * @return 如果左右子树是对称的，返回true;否则返回false
     */
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        //如果左右子树都为空，返回true
        if (left == null && right == null) {
            return true;
        }
        //如果两棵子树中只有一棵为空，则返回false
        boolean oneEmpty = (left == null && right != null) || (left != null && right == null);
        if (oneEmpty) {
            return false;
        }
        //如果两棵子树数据域相等，则继续判断
        if (left.data.equals(right.data)) {
            boolean value1 = isSymmetric(left.lchild, right.rchild);
            boolean value2 = isSymmetric(left.rchild, right.lchild);
            return value1 && value2;
        } else {
            //如果两棵子树数据域不等，则返回false
            return false;
        }

    }

    /**
     * 按行打印二叉树
     *
     * @param root 根结点
     */
    public void printByLine(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = root;  //当前结点
        TreeNode last = root;  //指向每层最后一个结点
        TreeNode tmp = null;  //临时指针，指向当前状态下最后一个结点
        queue.offer(current);
        while (queue.size() > 0) {
            current = queue.poll();
            visit((E) current.data);
            if (current.lchild != null) {
                queue.offer(current.lchild);
                tmp = current.lchild;
            }
            if (current.rchild != null) {
                queue.offer(current.rchild);
                tmp = current.rchild;
            }
            if (current == last) {
                System.out.println();
                last = tmp;
            }
        }
    }

    /**
     * 获取二叉树中结点的个数
     *
     * @param root 根结点
     * @return 结点的个数
     */
    public int getNodeNumber(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getNodeNumber(root.lchild);
        int right = getNodeNumber(root.rchild);
        return left + right + 1;
    }

    /**
     * 根据先序遍历和中序遍历构造二叉树
     *
     * @param preOrder 先序遍历序列
     * @param inOrder  中序遍历序列
     */
    public void buildBinaryTree(char[] preOrder, char[] inOrder) {
        if (preOrder.length != inOrder.length) {
            root = null;
        }
        root = buildBinaryTreeRecursive(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);
    }

    /**
     * 构造二叉树
     *
     * @param preOrder      先序遍历序列
     * @param preOrderStart 先序开始下标
     * @param preOrderEnd   先序结束下标
     * @param inOder        中序遍历序列
     * @param inOrderStart  中序开始下标
     * @param inOrderEnd    中序结束下标
     * @return 根结点
     */
    public TreeNode buildBinaryTreeRecursive(char[] preOrder, int preOrderStart, int preOrderEnd,
                                             char[] inOder, int inOrderStart, int inOrderEnd) {
        if (preOrderStart > preOrderEnd) {
            return null;
        }

        //如果左子树或者右子树只有一个结点，则直接生成左子树或者右子树
        if (preOrderStart == preOrderEnd) {
            return new TreeNode(preOrder[preOrderStart], null, null);
        }

        TreeNode root = new TreeNode(preOrder[preOrderStart], null, null);
        int position = findPositon(inOder, inOrderStart, inOrderEnd, preOrder[preOrderStart]);
        //构建左子树
        //在先序遍历序列中指定开始下标和结束下标，在中序遍历序列中指定开始下标和结束下标
        root.lchild = buildBinaryTreeRecursive(preOrder, preOrderStart + 1,
                preOrderStart + position - inOrderStart, inOder, inOrderStart,
                position - 1);
        //构建右子树
        //在先序遍历序列中指定开始下标和结束下标，在中序遍历序列中指定开始下标和结束下标
        root.rchild = buildBinaryTreeRecursive(preOrder,
                preOrderStart + position - inOrderStart + 1, preOrderEnd,
                inOder, position + 1, inOrderEnd);
        return root;
    }

    /**
     * 在中序遍历序列中找到根结点的位置
     *
     * @param inOrder 中序遍历序列
     * @param start   开始下标
     * @param end     结束下标
     * @param e       根结点的数据域
     * @return 根结点在中序遍历序列中的位置
     */
    public int findPositon(char[] inOrder, int start, int end, char e) {
        for (int i = start; i <= end; i++) {
            if (e == inOrder[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 访问树结点的数据域
     *
     * @param e 数据域
     */
    public void visit(E e) {
        if (e == null)
            return;
        System.out.print(e);
    }

    /**
     * 获取树中两个节点的最近公共祖先
     * @param root  根节点
     * @param n1
     * @param n2
     * @return  最近公共祖先节点
     */
    public TreeNode getLeastCommonAncestor(TreeNode root, char n1, char n2){
        if (root == null){
            return null;
        }

        //如果n1和n2中的任一个跟root匹配，则root就是最近公共祖先
        if (root.data.equals(n1) || root.data.equals(n2)){
            return root;
        }

        TreeNode left = getLeastCommonAncestor(root.lchild, n1, n2);
        TreeNode right = getLeastCommonAncestor(root.rchild, n1, n2);
        //一个节点在左子树中，一个节点在右子树中，则root就是最近公共祖先
        if (left != null && right != null){
            return root;
        }
        //两个节点都在一个子树上，则返回那个子树
        return (left != null) ? left : right;
    }



}

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Character> binaryTree = new BinaryTree<Character>();
        System.out.println("请输入结点：");
        /*binaryTree.getRoot().data = 'A';
        binaryTree.getRoot().lchild = new BinaryTree.TreeNode('B', null, null);
        binaryTree.getRoot().rchild = new BinaryTree.TreeNode('E', null, null);
        binaryTree.getRoot().lchild.lchild = new BinaryTree.TreeNode('C', null, null);
        binaryTree.getRoot().lchild.rchild = new BinaryTree.TreeNode('D', null, null);
        binaryTree.getRoot().rchild.rchild = new BinaryTree.TreeNode('F', null, null);*/
//        binaryTree.preOrderCreateRecursive(binaryTree.getRoot());
        binaryTree.buildBinaryTree("ABCDEF".toCharArray(), "CBDAEF".toCharArray());
        System.out.println("打印二叉树：");
//        binaryTree.preOrderTraverseRecursive(binaryTree.getRoot());
//        binaryTree.inOrderTraverseRecursive(binaryTree.getRoot());
//        binaryTree.postOrderTraverseRecursive(binaryTree.getRoot());
//        binaryTree.preOrderTraverseNonRecursive(binaryTree.getRoot());
//        binaryTree.inOrderTraverseNonRecursive(binaryTree.getRoot());
//        binaryTree.postOrderTraverseNonRecursive(binaryTree.getRoot());
//        binaryTree.leverTraverse(binaryTree.getRoot());
        System.out.println();
        System.out.println("二叉树深度: " + binaryTree.getDepth(binaryTree.getRoot()));
//        binaryTree.leverTraverse(binaryTree.findNode(binaryTree.getRoot(), 'B'));
//        binaryTree.mirror(binaryTree.getRoot());
//        binaryTree.leverTraverse(binaryTree.getRoot());
        System.out.println("二叉树是否对称: " + binaryTree.judgeSymmetric(binaryTree.getRoot()));
        binaryTree.printByLine(binaryTree.getRoot());
        System.out.println("二叉树中结点的个数: " + binaryTree.getNodeNumber(binaryTree.getRoot()));
        System.out.println("最近公共祖先：" + binaryTree.getLeastCommonAncestor(binaryTree.getRoot(),
                'C', 'D').data);
    }
}
