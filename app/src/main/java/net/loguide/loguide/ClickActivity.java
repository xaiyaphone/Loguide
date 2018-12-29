package net.loguide.loguide;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ClickActivity extends AppCompatActivity {

    TextView mTitle;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Loguide Package");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

        mTitle = findViewById(R.id.name_click);
        mImageView = findViewById(R.id.image_view_click);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
