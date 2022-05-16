package mg.master1p9.kidsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import mg.danih.sipembanque.R;
import mg.master1p9.kidsapp.connectivite.NetWorkChangeListener;


public class Accueil extends AppCompatActivity {

    NetWorkChangeListener netWorkChangeListener = new NetWorkChangeListener();

    private LinearLayout chiffres,lettres,couleurs,formes,reclamation,change,logout;
    private Accueil activity;
    private TextView utilisateur;
    MainActivity main = new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.activity = this;

        this.chiffres = (LinearLayout) findViewById(R.id.chiffres);
        this.lettres = findViewById(R.id.lettres);
        this.couleurs = findViewById(R.id.couleurs);
        this.formes = findViewById(R.id.formes);
        this.reclamation = findViewById(R.id.reclamation);
        this.change = findViewById(R.id.change);
        this.logout = findViewById(R.id.logout);




        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Se déconnecter");
                builder.setMessage("Êtes-vous sûr de vouloir vous déconnecter ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //SharedPreferenceLogin.getInstance(activity).logout();
//                        Toast.makeText(getApplicationContext(),"session : "+ SharedPreferenceLogin.getInstance(activity).getToken(),Toast.LENGTH_SHORT).show();
                        activity.finish();
                        startActivity(new Intent(activity, Login.class));
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
    //~~~~~~~~~~~~~~~~~~~~~~~TRAITEMENT TOOLBAR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //trano kely makany @ Accueil
    public void ClickHome(View view){
        Intent otherActivity = new Intent(getApplicationContext(), Accueil.class);
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