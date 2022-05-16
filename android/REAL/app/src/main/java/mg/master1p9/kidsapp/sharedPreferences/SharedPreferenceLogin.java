package mg.master1p9.kidsapp.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceLogin {
    private static SharedPreferenceLogin instance;
    private static Context ctx;

    private static final String TOKEN = "token";
    private static final String KEY_LOGIN = "TokenETU000911P12A";
    private static final String KEY_NAME = "nameETU000911P12A";

    private SharedPreferenceLogin(Context context){
        ctx = context;
    }

    public static synchronized SharedPreferenceLogin getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceLogin(context);
        }
        return instance;
    }

    //Client
    public boolean userLogin(String token,String name){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(TOKEN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAME,name);
        editor.putString(KEY_LOGIN,token);

        editor.apply();
        return true;
    }

    //verification
    public boolean isLogged(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(TOKEN,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_LOGIN,null)!=null){
            return true;
        }
        return false;
    }

    //deconnection
    public boolean logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(TOKEN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    //get Token
    public String getToken(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LOGIN, null);
    }
    //get urilisateur
    public String getUser(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null);
    }
}
