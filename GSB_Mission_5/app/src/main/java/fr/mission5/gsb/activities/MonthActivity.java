package fr.mission5.gsb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.mission5.gsb.R;

public class MonthActivity extends AppCompatActivity {

    private Button confirmButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        confirmButtonView = findViewById(R.id.month_screen_confirm_button);
        confirmButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowAllActivity.class));
            }
        });
    }
}
