package generic.adriano.androidhelper.Helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UI {

    // Creates a snack bar - implementation required.
    public static void CreateBasicSnack(String message, Integer length, Integer color, Context context){
        length = length != null ? length : 3000;
        color = color != null ? color :  Color.RED;
        Snackbar.make(((Activity)context).findViewById(android.R.id.content), message, length)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .setActionTextColor(color)
                .show();
    }

    // Hides virtual keyboard.
    public static void HideKeyboard(View view, Context context){
        if(view != null){
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
