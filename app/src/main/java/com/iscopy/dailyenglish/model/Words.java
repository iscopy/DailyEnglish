package com.iscopy.dailyenglish.model;

public class Words {

    private int id;
    /**
     * 单词
     */
    private String word;
    /**
     * 发音
     */
    private String pronunciation;

    /**
     * 含义
     */
    private String meaning;

    /**
     * 收藏
     */
    private int collection;

    public Words(){}

    public Words(int id, String word, String pronunciation, String meaning, int collection) {
        this.id = id;
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.collection = collection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }
}
