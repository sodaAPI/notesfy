
package com.app.notesfy;
public class fireBaseModel {

    private String title;
    private String content;

    public fireBaseModel(){

    }

    public fireBaseModel(String title, String content) {
        this.title =title;
        this.content=content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


