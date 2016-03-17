package cn.com.kjer.practice.collectionitempicker;


import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * Created by kjer on 2016/3/16.
 */
public class ListDataActivity extends BaseActivity {
    CollectionPicker mPicker;
    TextView mTextView;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mPicker = (CollectionPicker) findViewById(R.id.collection_item_picker);
        mPicker.setItems(generateItems());
        mPicker.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Item item, int position) {
                if (item.isSelected) {
                    counter++;
                } else {
                    counter--;
                }
                mTextView.setText(counter + " Items Selected");
            }
        });

        mTextView = (TextView) findViewById(R.id.text);
    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("a", "Friendly Staff"));
        items.add(new Item("b", "Cozy place"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("f", "Books"));
        items.add(new Item("g", "Goods for working"));
        items.add(new Item("h", "Romantic Places"));
        items.add(new Item("f", "Japanese food"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("g", "Goods for working"));
        items.add(new Item("h", "Romantic Places"));
        items.add(new Item("f", "Japanese food"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("g", "Goods for working"));
        items.add(new Item("h", "Romantic Places"));
        items.add(new Item("f", "Japanese food"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        items.add(new Item("g", "Goods for working"));
        items.add(new Item("h", "Romantic Places"));
        items.add(new Item("f", "Japanese food"));
        items.add(new Item("c", "Pizza"));
        items.add(new Item("d", "Burger"));
        items.add(new Item("e", "Ice Cream"));
        return items;
    }


}
