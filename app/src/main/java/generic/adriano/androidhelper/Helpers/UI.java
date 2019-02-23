package generic.adriano.androidhelper.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

    // Open dialog box
    public static void OpenDialogBox(String title, String message, Integer icon, Context context){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Function confirm
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Function cancel
                    }
                })
                .setIcon(icon)
                .show();
    }


}
