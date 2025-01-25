package com.example.imanango.ui.count;

import static com.example.imanango.data.RiceMode.GENMAI;
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
import com.example.imanango.data.RiceMode;
import com.example.imanango.ui.result.ResultActivity;
import com.example.imanango.ui.top.TopActivity;

public class CountActivity extends AppCompatActivity {

    //キーの設定
    //RICE_MODE_IDをキーにして値を受け取っている
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
    private float count = 0;
    private TextView riceCountView;


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

        //前の画面から受け取った値を取得
        riceModeID =getIntent().getIntExtra(RICE_MODE_ID, -1);
        //IDを元にRiceModeを取得
        RiceMode selectRiceMode = RiceMode.toRiceMode(riceModeID);
        //Idを取得して、ストリング型タイトルに変換している
        String title = getString(selectRiceMode.getTitleResId());
        //RiceModeの名前を表示するテキストビューを取得
        riceModeTextView = findViewById(R.id.rice_mode_text);
        //RicceModeの名前をテキストビューにセットする
        riceModeTextView.setText(getString(R.string.rice_mode_name, title));

        //レイアウトのIDと紐づける
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        getTotal = findViewById(R.id.get_total);

        //riceCountの表示
        riceCountView = findViewById(R.id.rice_count);
        riceCountView.setText(getGoText(count));

        //btn1を押したときの処理
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //カウントアップ処理
                count += 1;
                //テキストに表示する処理
                riceCountView.setText(getGoText(count));
            }
        });

        //btn2を押したときの処理
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0.5ずつのカウントアップ処理
                count += 0.5;
                //テキストに表示する処理
                riceCountView.setText(getGoText(count));
            }
        });

        //getTotalを押したときの処理(今なんGO!?ボタンを押したときの処理)
        getTotal = findViewById(R.id.get_total);
        //ボタンが押されたときの処理
        getTotal.setOnClickListener(view -> {

            Intent intent = ResultActivity.newIntent(CountActivity.this, riceModeID, count);
            startActivity(intent);
        });

    }

    private String getGoText(float count) {
        return count + "GO!!";
    }
}