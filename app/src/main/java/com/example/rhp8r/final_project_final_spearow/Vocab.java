package com.example.rhp8r.final_project_final_spearow;

/**
 * Created by rhp8r on 11/28/2017.
 */

public class Vocab implements Comparable<T>{
    private String word;
    private String def;
    private int rank;
    private String lang;

    public Vocab (String word, String def, int rank, String lang){
        this.word = word;
        this.def = def;
        this.rank = rank;
        this.lang = lang;
    }
    public Vocab (String word, String def, String lang){
        this.word = word;
        this.def = def;
        this.rank = 0;
        this.lang = lang;
    }
    public String getLang(){
        return this.lang;
    }
    public String getWord(){
        return this.word;
    }
    public String getDef(){
        return this.def;
    }
    public int getRank(){
        return this.rank;
    }
    public void setRank(int rank){this.rank = rank;}

    @Override
    

}
