package mg.master1p9.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mg.danih.sipembanque.R;

public class MainActivity extends AppCompatActivity {
    private String url = "Ecritures";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(Activity activity,String text){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, activity.findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);

        toastText.setText(text);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}