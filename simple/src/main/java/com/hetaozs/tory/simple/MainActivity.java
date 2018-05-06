package com.hetaozs.tory.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/ui/main")
public class MainActivity extends AppCompatActivity {

    @Autowired
    String sim;
    @Autowired
    long index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
