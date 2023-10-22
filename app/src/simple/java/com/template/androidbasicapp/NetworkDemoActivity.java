package com.template.androidbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NetworkDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // プロジェクトの構成上コードを共通化したいためxmlのレイアウトファイルでは、
        // Fragmentを呼び出しています。ここをLinearLayoutなどを使った画面作成に変えていただいて良いです。
        setContentView(R.layout.activity_network_demo);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkDemoActivity.class);
        context.startActivity(starter);
    }
}