package com.aspros.enjoyword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspros on 16/3/30.
 */
public class Fragment_Content_List extends Fragment implements AdapterView.OnItemClickListener{

    private ListView contentList;
    private View view;
    private List<WordContent> arrayList = new ArrayList<WordContent>();
    private WordContent wordContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content_list, container, false);

        initContent();
        return view;
    }

    private void initContent() {
        contentList = (ListView) view.findViewById(R.id.content_list);
        initData();
        WordContent_Adapter wordContentAdapter = new WordContent_Adapter(view.getContext(), R.layout.content_list_item, arrayList);
        contentList.setAdapter(wordContentAdapter);
        contentList.setOnItemClickListener(this);
    }

    private void initData() {
        arrayList=new ArrayList<WordContent>();
        wordContent = new WordContent(1, "这是一个简单测试标题1", "这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。这是一个简单的测试内容，请不必太过于在意,为了测试截断字符，所以需要添加更多的字符串。", "book", R.drawable.book);
        arrayList.add(wordContent);
        wordContent = new WordContent(2, "这是一个简单测试标题2", "这是一个简单的测试内容，请不必太过于在意", "bug", R.drawable.bug);
        arrayList.add(wordContent);
        wordContent = new WordContent(3, "这是一个简单测试标题3", "这是一个简单的测试内容，请不必太过于在意", "star", R.drawable.star);
        arrayList.add(wordContent);
        wordContent = new WordContent(4, "这是一个简单测试标题4", "这是一个简单的测试内容，请不必太过于在意", "heart", R.drawable.heart);
        arrayList.add(wordContent);
        wordContent = new WordContent(5, "这是一个简单测试标题5", "这是一个简单的测试内容，请不必太过于在意", "book", R.drawable.book);
        arrayList.add(wordContent);
        wordContent = new WordContent(6, "这是一个简单测试标题6", "这是一个简单的测试内容，请不必太过于在意", "book", R.drawable.book);
        arrayList.add(wordContent);
        wordContent = new WordContent(7, "这是一个简单测试标题7", "这是一个简单的测试内容，请不必太过于在意", "book", R.drawable.book);
        arrayList.add(wordContent);
        wordContent = new WordContent(8, "这是一个简单测试标题8", "这是一个简单的测试内容，请不必太过于在意", "book", R.drawable.book);
        arrayList.add(wordContent);
        wordContent = new WordContent(9, "这是一个简单测试标题9", "这是一个简单的测试内容，请不必太过于在意", "book", R.drawable.book);
        arrayList.add(wordContent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WordContent wc = arrayList.get(position);

        Fragment fragment_content_view = new Fragment_Content_View();
        Bundle bundle = new Bundle();
        bundle.putInt("wordContent_id", wc.getId());
        bundle.putString("wordContent_title", wc.getTitle());
        bundle.putString("wordContent_tag", wc.getTag());
        bundle.putInt("wordContent_tagImg", wc.getTagImg());
        bundle.putString("wordContent_content", wc.getContent());
        fragment_content_view.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.frag_layout, fragment_content_view);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
