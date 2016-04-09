package com.aspros.enjoyword;

/**
 * Created by Aspros on 16/3/30.
 */
public class WordContent {

    private int id;
    private String title;
    private String content;
    private String tag;
    private int tagImg;

    public WordContent(int id,String title,String content,String tag,int tagImg)
    {
        this.id=id;
        this.title=title;
        this.content=content;
        this.tag=tag;
        this.tagImg=tagImg;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getTagImg() {
        return tagImg;
    }
}
