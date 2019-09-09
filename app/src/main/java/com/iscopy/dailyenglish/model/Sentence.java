package com.iscopy.dailyenglish.model;

/**
 * 单词造句
 */
public class Sentence {

    /**
     * 单词
     */
    private String word;

    /**
     * 英语语句
     */
    private String sentence;

    /**
     * 语句翻译中文
     */
    private String sentence2;

    public Sentence(){

    }

    public Sentence(String word, String sentence, String sentence2) {
        this.word = word;
        this.sentence = sentence;
        this.sentence2 = sentence2;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence2() {
        return sentence2;
    }

    public void setSentence2(String sentence2) {
        this.sentence2 = sentence2;
    }
}
