package com.example.imanango.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imanango.R;
import com.example.imanango.ui.count.CountActivity;
import com.example.imanango.ui.top.TopActivity;

public class ResultActivity extends AppCompatActivity {

    //キーの設定
    //RICE_MODE_IDをキーにして値を受け取っている
    private static final String RICE_MODE_ID = "rice_mode_id";
    //countをキーにして値を受け取っている
    private static final String COUNT = "count";

    public static Intent newIntent(Context context, int riceModeId) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("RICE_MODE_ID", riceModeId);
        return intent;
    }

    private TextView riceCountView;
    private int riceModeID;
    private TextView waterView;
    private int defaultWater = 180;
    private int water = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //前の画面から受け取った値を取得
        //riceModeIDを受け取る
        riceModeID =getIntent().getIntExtra(RICE_MODE_ID, -1);
        //countを受け取る
        float count = getIntent().getFloatExtra(COUNT, 0);

        //必要な水の量の計算
        switch (riceModeID) {
            case 1:
                water = (int)(Math.round((defaultWater * 1.1) * count));
                //整数にしたい
                break;
            case 2:
                water = (int)(Math.round((defaultWater * 1.2) * count));
                break;
            case 3:
                water = (int)(Math.round((defaultWater * 1.5) * count));
                break;
        }

        //waterを表示
        waterView = findViewById(R.id.water_text);


        //トップの戻る処理
        findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TopActivity.newIntent(ResultActivity.this);
                startActivity(intent);
            }
        });

    }
}