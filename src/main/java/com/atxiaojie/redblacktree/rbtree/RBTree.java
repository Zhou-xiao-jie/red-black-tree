package com.atxiaojie.redblacktree.rbtree;

/**
 * @ClassName: RBTree
 * @Description: 手写红黑树
 * ①创建RBTree,定义颜色
 * ②创建RBNode
 * ③辅助方法定义：parentOf(node),isRed(node),isBlack(node),setRed(node),setBlack(node),inOrderPrint()
 * ④左旋方法定义：leftRotate(node)
 * ⑤右旋方法定义：rightRotate(node)
 * ⑥公开插入接口方法定义：insert(K key, V value)
 * ⑦内部插入接口方法定义：insert(RBNode, node)
 * ⑧修正插入导致红黑树失衡的方法定义：insertFIxUp(RBNode node)
 * ⑨测试红黑树正确性
 *
 * @author: zhouxiaojie
 * @date: 2021/11/10 17:59
 * @Version: V1.0.0
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    //树根的引用
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    /**
     * @MethodsName: parentOf
     * @Description 获取当前节点的父节点
     * @Author zhouxiaojie
     * @Date 18:15 2021/11/10
     * @Param [node]
     * @return com.atxiaojie.redblacktree.rbtree.RBTree.RBNode
     **/
    private RBNode parentOf(RBNode node){
        if(node != null){
            return node.parent;
        }
        return null;
    }

    /**
     * @MethodsName: isRed
     * @Description 判断节点是否为红色
     * @Author zhouxiaojie
     * @Date 18:19 2021/11/10
     * @Param [node]
     * @return boolean
     **/
    private boolean isRed(RBNode node){
        if(node != null){
            return node.color == RED;
        }
        return false;
    }

    /**
     * @MethodsName: isBlack
     * @Description 判断节点是否为黑色
     * @Author zhouxiaojie
     * @Date 18:19 2021/11/10
     * @Param [node]
     * @return boolean
     **/
    private boolean isBlack(RBNode node){
        if(node != null){
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * @MethodsName: setRed
     * @Description 设置节点为红色
     * @Author zhouxiaojie
     * @Date 18:23 2021/11/10
     * @Param [node]
     * @return void
     **/
    private void setRed(RBNode node){
        if(node != null){
            node.color = RED;
        }
    }

    /**
     * @MethodsName: setBlack
     * @Description 设置节点为黑色
     * @Author zhouxiaojie
     * @Date 18:23 2021/11/10
     * @Param [node]
     * @return void
     **/
    private void setBlack(RBNode node){
        if(node != null){
            node.color = BLACK;
        }
    }

    /**
     * @MethodsName: inOrderPrint
     * @Description 二叉树中序打印
     * @Author zhouxiaojie
     * @Date 18:41 2021/11/10
     * @Param []
     * @return void
     **/
    public void inOrderPrint(){
        inOrderPrint(this.root);
    }

    /**
     * @MethodsName: inOrderPrint
     * @Description 递归打印
     * @Author zhouxiaojie
     * @Date 18:42 2021/11/10
     * @Param [node]
     * @return void
     **/
    private void inOrderPrint(RBNode node){
        if(node != null){
            inOrderPrint(node.left);
            System.out.println("key:" + node.key + ",value:" + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * @MethodsName: leftRotate
     * @Description 左旋方法
     * 左旋示意图：左旋x节点
     *     P                        P
     *     |                        |
     *     x                        y
     *   /  \       ----->         / \
     *  lx   y                    x   ry
     *      / \                  / \
     *     ly  ry               lx  ly
     *
     * 1.将y的左子节点ly的父节点更新为x,并将x的右子节点指向y的左子节点（ly）
     * 2.当x的父节点（不为空时），更新y的父节点为x的父节点，并将x的父节点指定 子树（当前x的子树位置） 指定为y
     * 3.将x的父节点更新为y,将y的左子节点更新为x
     *
     * @Author zhouxiaojie
     * @Date 18:43 2021/11/10
     * @Param [x]
     * @return void
     **/
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //第一步
        //将x的右子节点指向y的左子节点（ly）
        x.right = y.left;
        if(y.left != null){
            //将y的左子节点ly的父节点更新为x
            y.left.parent = x;
        }
        //第二步
        //当x的父节点（不为空时），更新y的父节点为x的父节点，并将x的父节点指定 子树（当前x的子树位置） 指定为y
        if(x.parent != null){
            y.parent = x.parent;
            //x在P的左边还是右边，y就放在P的左边还是右边
            if(x == x.parent.left){
                x.parent.left = y;
            }else{
                x.parent.right = y;
            }
        }else{
            //说明x为根节点，此时需要更新y为根节点
            this.root = y;
            this.root.parent = null;
        }
        //第三步
        //将x的父节点更新为y,将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /**
     * @MethodsName: rightRotate
     * @Description 右旋方法
     * 右旋示意图：右旋y节点
     *       P                P
     *       |                |
     *       y                x
     *      / \              / \
     *     x  ry            lx  y
     *    / \                  / \
     *   lx  ly              ly  ry
     * 1.将y的左子节点指向x的右子节点，并且更新x的右子节点的父节点为y
     * 2.当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点的指定子节点（y当前的位置）为x
     * 3.更新y的父节点为x,更新x的右子节点为y
     *
     * @Author zhouxiaojie
     * @Date 19:43 2021/11/10
     * @Param [x]
     * @return void
     **/
    private void rightRotate(RBNode y){
        RBNode x = y.left;
        //第一步
        //将y的左子节点指向x的右子节点
        y.left = x.right;
        if(x.right != null){
            //更新x的右子节点的父节点为y
            x.right.parent = y;
        }
        //第二步
        //当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点的指定子节点（y当前的位置）为x
        if(y.parent != null){
            x.parent = y.parent;
            if(y == y.parent.right){
                y.parent.right = x;
            }else{
                y.parent.left = x;
            }
        }else{
            //说明y为根节点，此时需要更新x为根节点
            this.root = x;
            this.root.parent = null;
        }
        //第三步
        //更新y的父节点为x,更新x的右子节点为y
        y.parent = x;
        x.right = y;
    }

    /**
     * @MethodsName: insert
     * @Description 公开插入方法
     * @Author zhouxiaojie
     * @Date 20:12 2021/11/10
     * @Param [key, value]
     * @return void
     **/
    public void insert(K key, V value){
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        //新节点一定是红色的！！！
        node.setColor(RED);
        insert(node);
    }

    private void insert(RBNode node){
        //第一步：查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;

        while (x != null){
            parent = x;
            //cmp > 0 说明node.key > x.key 需要到x的右子树查找
            //cmp = 0 说明node.key = x.key 说明需要进行替换值操作
            //cmp < 0 说明node.key < x.key 需要到x的左子树查找
            int cmp = node.key.compareTo(x.key);
            if(cmp > 0){
                x = x.right;
            }else if(cmp == 0){
                x.setValue(node.getValue());
                return;
            }else{
                x = x.left;
            }
        }

        node.parent = parent;

        if(parent != null){
            //判断node与parent的key谁大
            int cmp = node.key.compareTo(parent.key);
            if(cmp > 0){
                //当前node的key比parent的key大，需要把node放入parent的右子节点
                parent.right = node;
            }else{
                //当亲的node的key比parent的key小，需要把node放入parent的左子节点
                parent.left = node;
            }
        }else{
            //第一次插入的时候
            this.root = node;
        }

        //需要调用修复红黑树平衡的方法
        insertFixup(node);
    }

    /**
     * @MethodsName: insertFixup
     * @Description 插入后修复红黑树平衡的方法
     * 情景1：红黑树为空树，将根节点染色为黑色
     * 情景2：插入节点的key已经存在，不需要处理，进不到这个方法里，上面等于的情况替换值已经处理了
     * 情景3：插入节点的父节点为黑色，因为你所插入的路径，黑色节点没有变化，所以红黑树依然平衡，所以不需要处理
     * 情景4：插入节点的父节点为红色，需要我们去处理
     *      情景4.1：叔叔节点存在，并且为红色（父-叔 双红），将爸爸和叔叔染色为黑色，将爷爷染色为红色，在以爷爷节点为当前节点进行下一轮处理
     *      情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
     *              情景4.2.1：插入节点为其父节点的左子节点（LL情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋，就好了
     *              情景4.2.2：插入节点为其父节点的右子节点（LR情况），以爸爸节点进行一次左旋，得到LL双红情况（情景4.2.1），然后指定爸爸节点为当前节点进行下一轮处理
     *      情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
     *              情景4.3.1：插入节点为其父节点的右子节点（RR情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点左旋，就好了
     *              情景4.3.2：插入节点为其父节点的左子节点（RL情况），以爸爸节点进行一次右旋，得到RR双红情况（情景4.3.1），然后指定爸爸节点为当前节点进行下一轮处理
     *
     * @Author zhouxiaojie
     * @Date 18:54 2021/11/15
     * @Param []
     * @return void
     **/
    private void insertFixup(RBNode node) {
        this.root.setColor(BLACK);

        //爸爸节点
        RBNode parent = parentOf(node);
        //爷爷节点
        RBNode gParent = parentOf(parent);

        //情景4：插入节点的父节点为红色
        if(parent != null && isRed(parent)){
            //如果父节点是红色，那么一定存在爷爷节点，因为根节点不可能是红色
            RBNode uncle = null;
            if(parent == gParent.left){
                //父节点为爷爷节点的左子树
                uncle = gParent.right;
                //情景4.1：叔叔节点存在，并且为红色（父-叔 双红）将爸爸和叔叔染色为黑色，将爷爷染色为红色，在以爷爷节点为当前节点进行下一轮处理
                if(uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    insertFixup(gParent);
                    return;
                }
                //情景4.2：叔叔节点不存在，或者为黑色
                if(uncle == null || isBlack(uncle)){
                    //情景4.2.1：插入节点为其父节点的左子节点（LL情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋，就好了
                    if(node == parent.left){
                        setBlack(parent);
                        setRed(gParent);
                        rightRotate(gParent);
                        return;
                    }
                    //情景4.2.2：插入节点为其父节点的右子节点（LR情况），以爸爸节点进行一次左旋，得到LL双红情况（情景4.2.1），然后指定爸爸节点为当前节点进行下一轮处理
                    if(node == parent.right){
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }

            }else{
                //父节点为爷爷节点的右子树
                uncle = gParent.left;
                //情景4.1：叔叔节点存在，并且为红色（父-叔 双红）将爸爸和叔叔染色为黑色，将爷爷染色为红色，在以爷爷节点为当前节点进行下一轮处理
                if(uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    insertFixup(gParent);
                    return;
                }
                //情景4.3：叔叔节点不存在，或者为黑色，
                if(uncle == null || isBlack(uncle)){
                    //情景4.3.1：插入节点为其父节点的右子节点（RR情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点左旋，就好了
                    if(node == parent.right){
                        setBlack(parent);
                        setRed(gParent);
                        leftRotate(gParent);
                        return;
                    }
                    //情景4.3.2：插入节点为其父节点的左子节点（RL情况），以爸爸节点进行一次右旋，得到RR双红情况（情景4.3.1），然后指定爸爸节点为当前节点进行下一轮处理
                    if(node == parent.left){
                        rightRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            }
        }
    }

    /**
     * @MethodsName: RBNode
     * @Description RBNode内部类
     * @Author zhouxiaojie
     * @Date 18:15 2021/11/10
     **/
    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
