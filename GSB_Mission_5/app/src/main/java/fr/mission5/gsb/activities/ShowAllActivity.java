package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.mission5.gsb.R;

public class ShowAllActivity extends AppCompatActivity {

    private Button detailButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        detailButtonView = findViewById(R.id.show_all_screen_detail_button);
        detailButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowActivity.class));
            }
        });
    }
}
