package ratnatech.farmerapp.AskQPurchase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ratnatech.farmerapp.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class OverlayScreen extends AppCompatActivity implements View.OnClickListener {
    private TextView textMessage;
    private TextView textHelp;
    private ImageView imgSettings;
    private ImageView imgMenu;
    private RelativeLayout rlOverlay;
    private LinearLayout llSettings;
    private LinearLayout llMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_screen);
        initControls();
    }

    private void initControls() {
        textMessage = (TextView) findViewById(R.id.textMessage);
        textHelp = (TextView) findViewById(R.id.textHelp);
        textHelp.setOnClickListener(this);
        imgSettings = (ImageView) findViewById(R.id.imgSettings);
        imgSettings.setOnClickListener(this);
        imgMenu = (ImageView) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        rlOverlay = (RelativeLayout) findViewById(R.id.rlOverlay);
        llSettings = (LinearLayout) findViewById(R.id.llSettings);
        llMenu = (LinearLayout) findViewById(R.id.llMenu);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSettings:
                Toast.makeText(this, "Settings icon clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.imgMenu:
                Toast.makeText(this, "Menu icon clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.textHelp:
                if (textHelp.getText().toString().equals("Next")) {
                    llSettings.setVisibility(View.GONE);
                    llMenu.setVisibility(View.VISIBLE);
                    textHelp.setText("Got It");
                } else {
                    rlOverlay.setVisibility(View.GONE);
                    textMessage.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
