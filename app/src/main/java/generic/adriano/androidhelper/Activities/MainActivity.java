package generic.adriano.androidhelper.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import generic.adriano.androidhelper.R;

import static generic.adriano.androidhelper.Helpers.Core.ChangeView;
import static generic.adriano.androidhelper.Helpers.UI.CreateBasicSnack;

public class MainActivity extends AppCompatActivity {

    View vSampleView1, vScannerView, vListview;
    public static LinearLayout lMainLayout;
    public static List<View> views;
    public static Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        // Views Init
        vSampleView1 = getLayoutInflater().inflate(R.layout.layout_sample_view_1, null);
        vScannerView = getLayoutInflater().inflate(R.layout.layout_scanner, null);
        vListview = getLayoutInflater().inflate(R.layout.layout_customlistview, null);
        views = Arrays.asList(vSampleView1, vScannerView, vListview);

        // Items Init
        lMainLayout = findViewById(R.id.lMainLayout);

        // Start main view
        new ScannerActivity(this, vScannerView);
        ChangeView(vScannerView);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOption1:
                CreateBasicSnack("OPTION 1", 1000, Color.GREEN, this);
                return true;
            case R.id.menuOption2:
                CreateBasicSnack("OPTION 2", 5000, Color.WHITE, this);
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }

    public  void ChangeToolbarTitle(String title){
        mainToolbar.setTitle("title");
        setSupportActionBar(mainToolbar);
    }

}
