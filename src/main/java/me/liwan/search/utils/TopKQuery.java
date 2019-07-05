package me.liwan.search.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "build")
public class TopKQuery {
    private String fileName;
    private int K;

    private TrieBuilder trieBuilder;

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TrieBuilder getTrieBuilder(){
        return this.trieBuilder;
    }

    @PostConstruct
    private void init(){
        this.trieBuilder = new TrieBuilder();
        trieBuilder.setK(this.K);
        trieBuilder.buildFromFile(this.fileName);
    }

}
