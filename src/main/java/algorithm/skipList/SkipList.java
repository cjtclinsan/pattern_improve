package algorithm.skipList;

import algorithm.linkedList.SinglyLinkedList;

import java.util.Arrays;

/**
 * @author woshi
 * @date 2020/7/1
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 */
public class SkipList {
    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;
    private Node head = new Node();

    public Node find(int value){
        Node p = head;
        //从最大层开始找，找到前一个节点，通过--i，移动下一层去找
        for( int i = levelCount - 1; i >= 0; --i ){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                //前一个节点
                p = p.forwards[i];
            }
        }

        if( p.forwards[0] != null && p.forwards[0].data == value ){
            return p.forwards[0];
        }else {
            return null;
        }
    }

    public void insert(int value){
        //随机层数
        int level = randomLevel();
        //创建新的节点
        Node newNode = new Node();
        newNode.data = value;
        //从最大层到底层，都要有节点数据
        newNode.maxLevel = level;
        //记录要更新的层数，表示新节点要更新到哪几层
        Node update[] = new Node[level];
        for( int i = 0; i < level; ++i ){
            update[i] = head;
        }

        /**
         *
         * 说明：层是从下到上的，这里最下层编号是0，最上层编号是15
         * 这里没有从已有数据最大层（编号最大）开始找，（而是随机层的最大层）导致有些问题。
         * 如果数据量为1亿，随机level=1 ，那么插入时间复杂度为O（n）
         */
        Node p = head;
        for( int i = level -1; i >= 0; --i ){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            // 这里update[i]表示当前层节点的前一节点，因为要找到前一节点，才好插入数据
            update[i] = p;
        }

        // 将每一层节点和后面节点关联
        for( int i = 0; i < level; i++ ){
            // 记录当前层节点后面节点指针
            newNode.forwards[i] = update[i].forwards[i];
            // 前一个节点的指针，指向当前节点
            update[i].forwards[i] = newNode;
        }

        // 更新层高
        if( levelCount < level ){
            levelCount = level;
        }
    }

    public void delete(int value){
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }
        if( p.forwards[0] != null && p.forwards[0].data == value ){
            for( int i = levelCount - 1; i >= 0; --i ){
                if( update[i].forwards[i] != null && update[i].forwards[i].data == value ){
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null){
            levelCount--;
        }
    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;
        while ( Math.random() < SKIPLIST_P && level < MAX_LEVEL ){
            level += 1;
        }
        return level;
    }

    /**
     * 跳表的节点，每个节点记录了当前节点数据和所在层数数据
     */
    public class Node {
        private int data = -1;
        /**
         * 表示当前节点位置的下一个节点所有层的数据，从上层切换到下层，就是数组下标-1，
         * forwards[3]表示当前节点在第三层的下一个节点。
         */
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
}