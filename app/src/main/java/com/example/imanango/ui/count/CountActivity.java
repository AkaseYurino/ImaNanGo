package com.example.imanango.ui.count;

import static com.example.imanango.data.RiceMode.MUSENMAI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imanango.R;
import com.example.imanango.ui.top.TopActivity;

public class CountActivity extends AppCompatActivity {

    //キーの設定
    private static final String RICE_MODE_ID = "rice_mode_id";

    public static Intent newIntent(Context context, int riceModeId) {
        Intent intent = new Intent(context, CountActivity.class);
        intent.putExtra(RICE_MODE_ID, riceModeId);
        return intent;
    };

    private TextView riceModeTextView;
    private int riceModeID;
    private FrameLayout btn1;
    private FrameLayout btn2;
    private FrameLayout getTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_count);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        riceModeID =getIntent().getIntExtra(RICE_MODE_ID, -1);

        riceModeTextView = findViewById(R.id.rice_mode_text);
        riceModeTextView.setText(getString(R.string.rice_mode_name, riceModeID));

        //レイアウトのIDと紐づける
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        getTotal = findViewById(R.id.get_total);

        //今なんGO!?ボタンを押したときの処理


    }
}