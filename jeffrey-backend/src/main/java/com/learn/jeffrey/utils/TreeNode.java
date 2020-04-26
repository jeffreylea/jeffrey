package com.learn.jeffrey.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:wangjianlin
 * @Description:数节点数据传输对象
 * @Date:Created in 9:56 2019/9/12
 * @Modified By:
 */
@Data
public class TreeNode {
    protected String id;
    protected String parentId;
    protected String label;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
