package com.example.imanango.ui.count;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imanango.R;

public class CountActivity extends AppCompatActivity {

    //TODO キーの設定
    private static final String RICE_MODE_ID = "rice_mode_id";

    public static Intent newIntent(Context context, int riceModeId) {
        Intent intent = new Intent(context, CountActivity.class);
        intent.putExtra(RICE_MODE_ID, riceModeId);
        return intent;
    };

    private TextView riceModeText;
    private int riceModeID;


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


    }
}