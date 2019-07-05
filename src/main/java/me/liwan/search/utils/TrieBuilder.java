package me.liwan.search.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TrieBuilder {

    private TrieNode rootNode;
    private int K;

    public TrieBuilder(){
        this.rootNode = new TrieNode(null);
    }

    public void setK(int K) {
        this.K = K;
    }

    public void updateUp(TrieNode node, Query query, int i){
        //if (node == null) return ;
        if (i >= query.getQuery().length()){
            node.setTopKQuery(new LinkNode<>(query));
        } else {
            updateUp(node.getNodeByChar(query.getQuery().charAt(i)), query, i+1);
            PriorityQueue<LinkNode<Query>> maxHeap = new PriorityQueue<>(
                    (o1, o2) -> o2.item.getCount() - o1.item.getCount()
            );
            for (TrieNode n : node.getNodeMap().values()){
                maxHeap.offer(n.getTopKQuery());
            }

            LinkNode<Query> head0 = new LinkNode<>(null);
            LinkNode<Query> pre = head0;
            int cnt = 0; // 计数
            while (!maxHeap.isEmpty() && cnt ++ < this.K){
                LinkNode<Query> now = maxHeap.poll();
                if (now.next != null) maxHeap.offer(now.next);
                pre.next = new LinkNode<>(now.item);
                pre = pre.next;
            }
            node.setTopKQuery(head0.next);
        }
    }

    public TrieBuilder addQuery(Query query){
        TrieNode node = this.rootNode;
        for(int i = 0, len = query.getQuery().length();i < len;i++){
            node = node.updateNode(query, i);
        }
        node.setIsWord(true);
        node.setCount(node.getCount() + query.getCount());
        query.setCount((int) node.getCount());
        updateUp(this.rootNode, query, 0);
        return this;
    }

    public List<Query> getTopKQueriesByPrefix(String findStr){
        TrieNode findNode = this.rootNode;
        for(int i = 0, len = findStr.length(); i < len; i++){
            Character c = findStr.charAt(i);
            findNode = findNode.getNodeByChar(c);
            if (findNode == null){
                return new ArrayList<>();
            }
        }
        return findNode.getTopKQueries();
    }

    public void buildFromFile(String fileName){
        Logger log = Logger.getLogger("TrieBulider");
        log.info(String.format("start build trie from file [ %s ] . ", fileName));
        int cnt = 0;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "GB2312");
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.trim().split("\t");
                this.addQuery(new Query(lineSplit[0], Integer.valueOf(lineSplit[1])));
                cnt ++;
                if (cnt % 10000 == 0)
                    log.info(String.format("building trie [ %d ] query ...... ", cnt));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("build trie finish, total [ %d ] query . ", cnt));
    }

}
