package com.example.rhp8r.final_project_final_spearow;

/**
 * Created by rhp8r on 12/1/2017.
 */

public class Link {
    String url;
    String label;
    String langname;
    public Link (String url, String label, String lang){
        this.url = url;
        this.label = label;
        this.langname = lang;
    }
    public String getUrl(){
        return this.url;
    }
    public String getLabel(){return this.label;}
    public String getLangname(){
        return this.langname;
    }
}
