package com.bytedance.asignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEt;
    private TextView mTv;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEt = findViewById(R.id.et);
        mTv = findViewById(R.id.tv);
        mLv = findViewById(R.id.lv);
        mLv.setAdapter(new ListViewAdapter(MainActivity.this));
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"第" + (position + 1) + "行被点击！",Toast.LENGTH_LONG).show();
            }
        });

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt.setText("");
            }
        });

        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int temp = 0;

                if(s.length() == 0){
                    mLv.setSelection(0);//输入栏为空的时候跳转回第一行
                }

                for(int i = s.length() - 1;i >= 0;i--){
                    if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                        int power = s.length() - i - 1;
                        int digit = (int)(s.charAt(i) - '0');
                        temp += (int)Math.pow(10,power) * digit;
                    }
                    else{
                        break;
                    }
                }

                if(temp <= 100){
                    mLv.setSelection(temp - 1);//实现跳转
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}