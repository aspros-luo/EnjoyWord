package com.aspros.enjoyword;

/**
 * Created by Aspros on 16/3/31.
 */
public class Left_Menu_Content {
    //left image
    private int left_Img;
    //left menu content
    private String left_Content;

    //init left_Menu_Content
    public Left_Menu_Content(int left_Img, String left_Content) {
        this.left_Img = left_Img;
        this.left_Content = left_Content;
    }

    //get left image
    public int getLeft_Img() {
        return left_Img;
    }

    //get left menu content
    public String getLeft_Content() {
        return left_Content;
    }
}
