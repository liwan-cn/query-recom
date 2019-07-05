package me.liwan.search.utils;

public class Query {

    private final String query;
    private int count;

    public Query(String query, int count){
        this.query = query;
        this.count = count;
    }

    public String getQuery(){
        return this.query;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
        this.count = count;
    }

    @Override
    public String toString(){
        return "{ \"query\": \"" + this.query + "\", \"count\": " + this.count + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Query) {
            Query query = (Query) obj;
            return this.getQuery().equals(query.getQuery());
        }
        return false;
    }

}
