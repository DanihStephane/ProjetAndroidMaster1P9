package mg.master1p9.kidsapp.sousMenu.chiffres;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import mg.master1p9.kidsapp.AccueilActivity;
import mg.master1p9.kidsapp.MainActivity;
import mg.master1p9.kidsapp.R;
import mg.master1p9.kidsapp.connectivite.NetWorkChangeListener;

public class ChiffresCroissant extends AppCompatActivity {

    NetWorkChangeListener netWorkChangeListener = new NetWorkChangeListener();
    private ChiffresCroissant activity;
    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffres_croissant);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~TRAITEMENT TOOLBAR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //trano kely makany @ Accueil
    public void ClickHome(View view){
        Intent otherActivity = new Intent(getApplicationContext(), AccueilActivity.class);
        startActivity(otherActivity);
        finish();
    }

    //resaka toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.about){
            main.showToast(activity,(getResources().getString(R.string.about)));
        }else if(id == R.id.exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sortir");
            builder.setMessage("Êtes-vous sûr de vouloir quitter ?");
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishActivity(0);
                    System.exit(0);
                }
            });
            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return true;
    }

    private void goToURL(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
    @Override
    protected void onStart() {
        IntentFilter fliter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangeListener, fliter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(netWorkChangeListener);
        super.onStop();
    }
}
