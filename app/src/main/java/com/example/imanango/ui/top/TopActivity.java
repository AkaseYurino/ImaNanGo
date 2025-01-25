package com.example.imanango.ui.top;

import static com.example.imanango.data.RiceMode.GENMAI;
import static com.example.imanango.data.RiceMode.MUSENMAI;
import static com.example.imanango.data.RiceMode.NORMAL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imanango.R;
import com.example.imanango.data.RiceMode;
import com.example.imanango.ui.count.CountActivity;
import com.example.imanango.ui.result.ResultActivity;

public class TopActivity extends AppCompatActivity {

    private FrameLayout startButtonNormal;
    private FrameLayout startButtonMusenmai;
    private FrameLayout startButtonGenmai;
    private int riceModeId;

    //ClearActivityから戻ってくる処理
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,TopActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_top);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ふつうのお米モード
        startButtonNormal = findViewById(R.id.start_button_normal);
        //ボタンが押されたときの処理
        startButtonNormal.setOnClickListener(view -> {
            riceModeId = NORMAL.getId();
            Intent intent = CountActivity.newIntent(TopActivity.this, riceModeId);
            startActivity(intent);
        });

        //無洗米モード
        startButtonMusenmai = findViewById(R.id.start_button_musenmai);
        //ボタンが押されたときの処理
        startButtonMusenmai.setOnClickListener(view -> {
            riceModeId = MUSENMAI.getId();
            Intent intent = CountActivity.newIntent(TopActivity.this, riceModeId);
            startActivity(intent);
        });

        //玄米モード
        startButtonGenmai = findViewById(R.id.start_button_gennmai);
        //ボタンが押されたときの処理
        startButtonGenmai.setOnClickListener(view -> {
            riceModeId = GENMAI.getId();
            Intent intent = CountActivity.newIntent(TopActivity.this, riceModeId);
            startActivity(intent);
        });


    }
}