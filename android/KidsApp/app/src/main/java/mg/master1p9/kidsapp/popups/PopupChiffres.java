package mg.master1p9.kidsapp.popups;

import android.app.Activity;
import android.app.Dialog;
import android.widget.LinearLayout;


import mg.master1p9.kidsapp.R;

public class PopupChiffres extends Dialog {
    private LinearLayout croissant,decroissant;
    public PopupChiffres(Activity activity){
        super(activity, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_chiffres);

        this.croissant = findViewById(R.id.croissant);
        this.decroissant = findViewById(R.id.decroissant);
    }

    public LinearLayout getCroissant() {
        return croissant;
    }

    public LinearLayout getDecroissant() {
        return decroissant;
    }

    public void build(){
        show();
    }
}
