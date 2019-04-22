package datastructure;

/**
 * Created by sun on 2017/3/8.
 */

import sun.reflect.generics.tree.Tree;

/**
 * 自己用链表实现的二叉查找树
 */
class BinarySearchTree {
    /**
     * 树结点类
     */
    class TreeNode {
        int data;  //数据域
        TreeNode lchild;  //左孩子指针
        TreeNode rchild;  //右孩子指针

        public TreeNode() {

        }

        public TreeNode(int data, TreeNode lchild, TreeNode rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    private TreeNode root;  //根结点
    private TreeNode doubleListHead;  //双向链表头指针
    private TreeNode doubleListTail;  //双向链表尾指针

    public BinarySearchTree() {
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getDoubleListHead() {
        return doubleListHead;
    }

    /**
     * 插入结点
     *
     * @param data 数据域
     */
    public void insert(int data) {
        //如果根结点为空，直接创建树结点
        if (root == null) {
            root = new TreeNode(data, null, null);
            return;
        }
        TreeNode node = root;
        TreeNode parent = new TreeNode();
        while (node != null) {
            parent = node;
            if (data < node.data) {
                //如果data小于根结点的值，则在左子树中插入data
                node = node.lchild;
            } else if (data > node.data) {
                //如果data大于根结点的值，则在右子树中插入data
                node = node.rchild;
            } else {
                //如果data等于根结点的值，则直接返回
                return;
            }
        }

        //插入结点
        if (data < parent.data) {
            parent.lchild = new TreeNode(data, null, null);
        } else {
            parent.rchild = new TreeNode(data, null, null);

        }
    }

    /**
     * 删除结点
     *
     * @param data 数据域
     */
    public void delete(int data) {
        //先找到要删除的结点
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            if (data == node.data) {
                break;
            } else if (data < node.data) {
                parent = node;
                node = node.lchild;
            } else {
                parent = node;
                node = node.rchild;
            }
        }

        if (node == null) {
            System.out.println(data + "不存在");
            return;
        }

        if (node.lchild == null) {
            if (parent != null) {
                //被删除结点的子树取代被删除结点的位置,看被删除节点是父节点的左子树还是右子树
                if (node.data < parent.data) {
                    //被删除结点是父节点的左子树，被删除结点的子树成为被删除结点父节点的左子树
                    parent.lchild = node.rchild;
                } else {
                    //被删除结点是父节点的右子树，被删除结点的子树成为被删除结点的父节点的右子树
                    parent.rchild = node.rchild;
                }
            } else {
                //根结点
                root = root.rchild;
            }
        } else {
            TreeNode replaceNodeParent = node;
            TreeNode replaceNode = node.lchild;
            //找到被删除结点的前驱结点,即被删除结点的左子树中值最大的结点，即左子树中最右边的结点且没有右子树
            while (replaceNode.rchild != null) {
                replaceNodeParent = replaceNode;
                replaceNode = replaceNode.rchild;
            }
            //先删除前驱结点，即用前驱结点的子树取代前驱结点的位置，看前驱节点是父节点的左子树还是右子树
            if (replaceNode.data < replaceNodeParent.data) {
                replaceNodeParent.lchild = replaceNode.lchild;
            } else {
                replaceNodeParent.rchild = replaceNode.lchild;
            }
            //再用前驱结点的值取代被删除结点的值
            node.data = replaceNode.data;
        }
        System.out.println(data + "删除成功");
    }

    /**
     * 查找结点
     *
     * @param data 数据域
     * @return 结点
     */
    public TreeNode search(int data) {
        TreeNode node = root;
        while (node != null) {
            if (data == node.data) {
                break;
            } else if (data < node.data) {
                node = node.lchild;
            } else {
                node = node.rchild;
            }
        }

        if (node == null) {
            System.out.println(data + "不存在");
        }
        return node;
    }

    /**
     * 递归的方式中序遍历二叉树
     *
     * @param root 根结点
     */
    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.lchild);
            visit(root.data);
            inOrderTraverse(root.rchild);
        }
    }

    /**
     * 获取二叉查找树最大值
     *
     * @param root 根结点
     * @return 最大值
     */
    public int max(TreeNode root) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            parent = node;
            node = node.rchild;
        }
        return parent.data;
    }

    /**
     * 获取二叉查找树最小值
     *
     * @param root 根结点
     * @return 最小值
     */
    public int min(TreeNode root) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            parent = node;
            node = node.lchild;
        }
        return parent.data;
    }

    /**
     * 获取二叉树的第k个结点
     *
     * @param root 根结点
     * @param k    大小顺序为k
     * @return 第k个结点
     */
    public TreeNode getKthNode(TreeNode root, int k) {
        int[] index = {0};
        return KthNode(root, k, index);
    }

    /**
     * 获取二叉搜索树的第k个结点
     *
     * @param root  根结点
     * @param k     大小顺序为k
     * @param index 计数器，单个元素的基本类型数组作为参数，传递的是数组的引用，可以修改数组
     * @return 第k个结点
     */
    public TreeNode KthNode(TreeNode root, int k, int[] index) {
        if (root == null) {
            return null;
        }
        TreeNode left = KthNode(root.lchild, k, index);
        index[0]++;
        if (index[0] == k) {
            return root;
        }
        TreeNode right = KthNode(root.rchild, k, index);
        if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    /**
     * 验证某个整型数组是不是某二叉树的后序遍历序列
     *
     * @param sequence 整型数组
     * @return 如果是，返回true；否则返回false
     */
    public boolean verifyPostOrderSequenceOfBST(int[] sequence) {
        return verifySequence(sequence, 0, sequence.length - 1);
    }

    /**
     * 递归进行验证
     *
     * @param sequence 整型数组
     * @param start    开始下标
     * @param end      结束下标
     * @return 如果整型数组是某二叉树的后序遍历序列，返回true；否则返回false
     */
    public boolean verifySequence(int[] sequence, int start, int end) {
        if (sequence == null) {
            return false;
        }
        //如果子树只有一个结点，返回true
        if (start == end) {
            return true;
        }

        //左子树中的结点都小于根结点
        int i;
        for (i = start; i < end; i++) {
            if (sequence[i] > sequence[end]) {
                break;
            }
        }

        //右子树中的结点都大于根结点
        int j;
        for (j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) {
                return false;
            }
        }
        //判断左子树是不是二叉搜索树
        boolean left = true;
        if (i > 0) {
            //i=0表示左子树为空
            left = verifySequence(sequence, start, i - 1);
        }
        //判断右子树是不是二叉搜索树
        boolean right = true;
        if (i < end) {
            //i=end表示右子树为空
            right = verifySequence(sequence, i, end - 1);
        }
        return left && right;
    }

    /**
     * 将二叉查找树转换成排序双向链表
     *
     * @param node 根结点
     */
    public void convertToDoubleList(TreeNode node) {
        if (node == null) {
            return;
        }
        convertToDoubleList(node.lchild);
        if (doubleListTail == null) {
            //如果双向链表为空，让头指针指向node
            doubleListHead = node;
        } else {
            //如果双向链表不为空，则在双向链表中插入结点，即让尾指针的右孩子指针指向node，即后继结点
            doubleListTail.rchild = node;
        }
        //让后继结点的左孩子指针指向尾指针
        node.lchild = doubleListTail;
        //将尾指针移动到后继结点
        doubleListTail = node;
        convertToDoubleList(node.rchild);
    }

    /**
     * 判断二叉树是不是平衡二叉树
     *
     * @param node 根节点
     * @return 如果是平衡二叉树，返回true;否则返回false
     */
    public boolean isBalanced(TreeNode node) {
        int[] depth = {-1};
        return isBalancedCore(node, depth);
    }

    /**
     * 判断二叉树是不是平衡二叉树
     *
     * @param node  根节点
     * @param depth 树的深度
     * @return 如果是平衡二叉树，返回true；否则返回false
     */
    public boolean isBalancedCore(TreeNode node, int[] depth) {
        if (node == null) {
            depth[0] = 0;
            return true;
        }
        int[] depthLeft = {-1};
        int[] depthright = {-1};
        boolean left = isBalancedCore(node.lchild, depthLeft);
        boolean right = isBalancedCore(node.rchild, depthright);
        if (left == false || right == false || Math.abs(depthLeft[0] - depthright[0]) > 1) {
            return false;
        } else {
            //平衡二叉树成立的条件：左右子树高度之差的绝对值不超过1，且左右子树都是平衡二叉树
            depth[0] = Math.max(depthLeft[0], depthright[0]) + 1;
            return true;
        }
    }

    /**
     * 访问数据域
     *
     * @param data 数据域
     */
    public void visit(int data) {
        System.out.print(data + ", ");
    }

}

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        int[] nodes = {3, 2, 4, 1, 5};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        //创建二叉查找树
        for (int i : nodes) {
            binarySearchTree.insert(i);
        }
        binarySearchTree.inOrderTraverse(binarySearchTree.getRoot());
        System.out.println();
        binarySearchTree.search(6);
//        binarySearchTree.delete(2);
        binarySearchTree.inOrderTraverse(binarySearchTree.getRoot());
        System.out.println();
        System.out.println("最大值: " + binarySearchTree.max(binarySearchTree.getRoot()));
        System.out.println("最小值: " + binarySearchTree.min(binarySearchTree.getRoot()));
        System.out.println(binarySearchTree.getKthNode(binarySearchTree.getRoot(), 2).data);
        int[] sequence = {5, 7, 6, 9, 11, 10, 8};
        System.out.println("该数组是否是某二叉搜索树的后序遍历序列： " +
                binarySearchTree.verifyPostOrderSequenceOfBST(sequence));
//        binarySearchTree.convertToDoubleList(binarySearchTree.getRoot());
        BinarySearchTree.TreeNode head = binarySearchTree.getDoubleListHead();
        System.out.println("遍历排序双向链表： ");
        while (head != null) {
            System.out.print(head.data + ", ");
            head = head.rchild;
        }
        System.out.println("是否是平衡二叉树: " +
               binarySearchTree.isBalanced(binarySearchTree.getRoot()));
    }
}
