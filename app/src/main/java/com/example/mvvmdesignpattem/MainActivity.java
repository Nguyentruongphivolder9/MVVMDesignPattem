package com.example.mvvmdesignpattem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mBtnClick;
    TextView mTv;
    EditText mEdt;
    MyService myService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myService = new MyService(getLifecycle(), new MyService.CallBack(){
            @Override
            public void onListen(String lifectcle) {
                Log.d("BBB",lifectcle);
            }
        });
        getLifecycle().addObserver(myService);

        mBtnClick = findViewById(R.id.buttonClick);
        mEdt = findViewById(R.id.editText);
        mTv = findViewById(R.id.textView);

        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEdt.getText().toString();

                mTv.setText(text);
            }
        });
    }
}