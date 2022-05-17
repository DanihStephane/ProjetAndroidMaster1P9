package mg.master1p9.kidsapp.popups;

import android.app.Activity;
import android.app.Dialog;
import android.widget.LinearLayout;

import mg.master1p9.kidsapp.R;

public class PopupLettres extends Dialog {
    private LinearLayout arranger,chercher;

    public PopupLettres(Activity activity){
        super(activity,androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_lettres);

        this.arranger = findViewById(R.id.arranger);
        this.chercher = findViewById(R.id.chercher);
    }

    public LinearLayout getArranger() {
        return arranger;
    }

    public LinearLayout getChercher() {
        return chercher;
    }

    public void build(){
        show();
    }
}
