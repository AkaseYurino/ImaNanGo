package com.example.imanango.ui.result;

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
import com.example.imanango.data.RiceMode;
import com.example.imanango.ui.count.CountActivity;
import com.example.imanango.ui.top.TopActivity;

public class ResultActivity extends AppCompatActivity {

    //キーの設定
    //RICE_MODE_IDをキーにして値を受け取っている
    private static final String RICE_MODE_ID = "rice_mode_id";
    //countをキーにして値を受け取っている
    private static final String COUNT_TOTAl  = "count_total";

    public static Intent newIntent(Context context, int riceModeId, float countTotal) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(RICE_MODE_ID, riceModeId);
        intent.putExtra(COUNT_TOTAl, countTotal);
        return intent;
    }

    private TextView riceCountView;
    private int riceModeID;
    private double countTotal;
    private TextView waterView;
    private TextView lastRiceText;
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
        countTotal = getIntent().getFloatExtra(COUNT_TOTAl, 0);

        //ModeIDからModeに変換
        //riceModeIDを元にRiceModeを取得
        RiceMode selectRiceMode = RiceMode.toRiceMode(riceModeID);

        //必要な水の量の計算
        switch (selectRiceMode){
            case NORMAL:
                water = (int)(Math.round((defaultWater * 1.1) * countTotal));
                break;
            case MUSENMAI:
                water = (int)(Math.round((defaultWater * 1.2) * countTotal));
                break;
            case GENMAI:
            water = (int)(Math.round((defaultWater * 1.5) * countTotal));
                break;
        }

        //waterを表示
        waterView = findViewById(R.id.water_text);

        //米の合数を表示
        riceCountView = findViewById(R.id.total_rice_text);
        riceCountView.setText(countTotal + "合");

        //米の号数を取得する処理
        lastRiceText = findViewById(R.id.last_rice_text);

        //結果の文章
        waterView.setText(getString(R.string.water_text, water));
        lastRiceText.setText(getString(R.string.last_rice_text, countTotal));

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