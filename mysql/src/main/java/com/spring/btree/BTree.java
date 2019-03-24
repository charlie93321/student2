package com.spring.btree;

import lombok.Data;

import java.util.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/16  14:49]
 * @DESC:
 *
 * B树又称为 B-树
 * 特点如下: 若果是m阶的B树
 * 1.每个节点有m-1个子节点
 * 3.所有的叶子节点都位于同一层
 */
public class BTree {


    @Data
    static class NodeObject<T>{
        private  Integer key;
        private  List<T>  ts;

    }

    interface NodeObjectKey<T>{
        public Integer getKey(T t);
    }

    @Data
    static class BminusNode<T>{

        private  Integer bnum;

        private List<NodeObject<T>> nodes;

        private Map<Integer,Integer> keyMap=new HashMap<>(bnum);

        private List<BminusNode<T>> child;
        private BminusNode<T> parent;

        public BminusNode(Integer bnum,BminusNode<T> parent) {
            bnum = bnum;
            this.nodes=new ArrayList<>(bnum);
            this.parent=parent;
            this.child=new ArrayList<>(bnum);
        }

        public void add(NodeObject<T> tn){

             putNodeInList(tn);

             if(nodes.size()==bnum){
                 NodeObject<T> rn=nodes.remove((bnum-1)/2+1);

                 if(this.parent==null){


                 }else{
                     this.parent.add(rn);
                 }
             }

        }

        private void putNodeInList(NodeObject<T> tn) {
            if(keyMap.isEmpty()){
                nodes.add(tn);
                keyMap.put(tn.key,nodes.size()-1);
                return;
            }
            if(keyMap.keySet().contains(tn.key)){
                // 索引值相同, 追加 value
                NodeObject<T> vs=nodes.get(keyMap.get(tn.key));
                for (T t : tn.ts) {
                    vs.ts.add(t);
                }
            }else{
                 nodes.add(tn);
                 nodes.sort((o1, o2) -> o2.key-o1.key);
                 rebuildKeyMap();
            }

        }

        private void rebuildKeyMap() {
            keyMap=new HashMap<>(bnum);
            for (int i = 0; i <nodes.size(); i++) {
                 keyMap.put(nodes.get(i).key,i);
            }
        }

        private NodeObject<T> buildNode(Integer key, List<T> ts) {
            NodeObject node=new NodeObject<>();
            node.setKey(key);
            List<T> ls=new ArrayList<>(ts);
            node.setTs(ls);
            return node;
        }


    }






}
