package generic.adriano.androidhelper.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import generic.adriano.androidhelper.R;

public class Core {

    //region SharedPreferences
    public static String ReadPreference(Context context, String preferenceName){
        if(!CheckPreference(context, preferenceName)){ return null; }
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferenceDestination), Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, "");
    }
    public static void WritePreference(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferenceDestination), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }
    public static void ClearPreference(Context context, String preferenceName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferenceDestination), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, "");
        editor.apply();
    }
    public static boolean CheckPreference(Context context, String preferenceName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.preferenceDestination), Context.MODE_PRIVATE);
        return sharedPreferences.contains(preferenceName);
    }
    //endregion
}
