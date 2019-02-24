package generic.adriano.androidhelper.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

import generic.adriano.androidhelper.R;

public class MainLoader extends AppCompatActivity {

    // Elements
    LottieAnimationView avMainLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loader);

        // Link items
        avMainLoader = findViewById(R.id.avMainLoader);

        // Add events
        avMainLoader.setImageAssetsFolder("assets");
        avMainLoader.setAnimation("loadingAnimation.json");
        avMainLoader.loop(true);
        avMainLoader.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Delayed action in order to show initial animation.
                Intent loginIntent = new Intent(MainLoader.this, MainActivity.class);
                finish();
                startActivity(loginIntent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        }, 1500);
    }
}
