package com.seizonia.spm.api.tag;

public class TagReplacer {

    private String tag;
    private Replacer replacer;

    public TagReplacer(String tag, Replacer replacer){
        this.tag = tag;
        this.replacer = replacer;
    }

    public String getTag() { return tag; }

    public Replacer getReplacer() { return replacer; }
}
