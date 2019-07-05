package me.liwan.search.utils;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class TrieNode {

    private Character character;
    private Map<Character, TrieNode> nodeMap;
    private LinkNode<Query> topKQuery;
    private long count;
    private boolean isWord = false;

    public TrieNode(Character character){
        this.character = character;
        this.nodeMap = new HashMap<>();
        this.topKQuery = null;
        this.count = 0;
    }

    public TrieNode addCharacter(Character c){
        TrieNode node = this.nodeMap.get(c);
        if(node == null){
            TrieNode tmp = new TrieNode(c);
            this.nodeMap.put(c, tmp);
            node = tmp;
        }
        //TrieNode node = this.nodeMap.putIfAbsent(c, new TrieNode(c));
        return node;
    }

    public TrieNode updateNode(Query query, int i){
        this.count += query.getCount();
        Character c = query.getQuery().charAt(i);
        return this.addCharacter(c);
    }

    public TrieNode getNodeByChar(Character c){
        return nodeMap.get(c);
    }

    public void setTopKQuery(LinkNode<Query> head){
        this.topKQuery = head;
    }

    public LinkNode<Query> getTopKQuery() {
        return topKQuery;
    }

    public boolean isWord() {
        return this.isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public long getCount(){
        return this.count;
    }
    public void setCount(long count){
        this.count = count;
    }

    public Character getCharacter() {
        return character;
    }

    public Map<Character, TrieNode> getNodeMap(){
        return this.nodeMap;
    }

    public List<Query> getTopKQueries(){
        List<Query> res = new ArrayList<>();
        LinkNode<Query> p = this.getTopKQuery();
        while (p != null) {
            res.add(p.item);
            p = p.next;
        }
        return res;
    }

    public List<String> getTopKQueryStrings(){
        List<String> res = new ArrayList<>();
        LinkNode<Query> p = this.getTopKQuery();
        while (p != null) {
            res.add(p.item.getQuery());
        }
        return res;
    }

}
