package com.aspros.enjoyword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aspros on 16/3/30.
 */
public class WordContent_Adapter extends ArrayAdapter<WordContent> {

    private int resourceId;
    public WordContent_Adapter(Context context, int textViewResourceId, List<WordContent> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WordContent wordContent=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView word_Title= (TextView) view.findViewById(R.id.wordContent_Title);
        TextView word_Tag= (TextView) view.findViewById(R.id.wordContent_Tag);
        TextView word_Content= (TextView) view.findViewById(R.id.wordContent_Content);
        ImageView word_TagImg= (ImageView) view.findViewById(R.id.wordContent_TagImg);

        word_Title.setText(wordContent.getTitle());
        word_Content.setText(wordContent.getContent());
        word_Tag.setText(wordContent.getTag());
        word_TagImg.setImageResource(wordContent.getTagImg());
        return view;
    }
}
