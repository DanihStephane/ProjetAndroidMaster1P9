package mg.master1p9.kidsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mg.danih.sipembanque.R;
import mg.master1p9.kidsapp.connectivite.NetWorkChangeListener;
import mg.master1p9.kidsapp.sharedPreferences.SharedPreferenceLogin;

public class Login extends AppCompatActivity {

    NetWorkChangeListener netWorkChangeListener = new NetWorkChangeListener();
    private LinearLayout valider;

    //Traitement
    private EditText identifiant;
    private String postMdp;

    //requete
    private RequestQueue requestQueue;

    private JsonArrayRequest arrayRequest;

    private String url = "Login";

    //fenetre
    private Login activity;

    private ProgressDialog progressDialog;

    //Traitement refresh
    private SwipeRefreshLayout refresh;

    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //raha ohatra ka mbola connecter
        if(SharedPreferenceLogin.getInstance(this).isLogged()){
            finish();
            startActivity(new Intent(this,Accueil.class));
            return;
        }

        //popup de chargement
        progressDialog = new ProgressDialog(this);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.background_light);
        progressDialog.setMessage(getResources().getString(R.string.loading));

        this.identifiant = (EditText) findViewById(R.id.identifiant);

        this.activity = this;


        this.valider = findViewById(R.id.valider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String log = identifiant.getText().toString();
                String pass = postMdp;
                submit(log,pass);

//                provisoir
//                progressDialog.dismiss();
//                startActivity(new Intent(getApplicationContext(),Accueil.class));
//                activity.finish();
            }
        });
    }


    private void submit(String log, String pass) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    //Reponse res = new Reponse();
                    //res.setStatus(jsonObject.getBoolean("status"));
                    //res.setMesssage(jsonObject.getString("message"));
                    //verificationLogin(res,jsonObject);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    main.showToast(activity,e.toString());
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                main.showToast(activity,"probelme de connexion ");
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("username",log);
                params.put("mdp",pass);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
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