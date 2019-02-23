package generic.adriano.androidhelper.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import generic.adriano.androidhelper.R;

import static generic.adriano.androidhelper.Helpers.Core.ChangeView;

public class MainActivity extends AppCompatActivity {

    View vSampleView1;
    public static LinearLayout lMainLayout;
    public static List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views Init
        vSampleView1 = getLayoutInflater().inflate(R.layout.layout_sample_view_1, null);
        views = Arrays.asList(vSampleView1);

        // Items Init
        lMainLayout = findViewById(R.id.lMainLayout);

        // Start main view
        new SampleActivity1(this, vSampleView1);
        ChangeView(vSampleView1);
    }
}
