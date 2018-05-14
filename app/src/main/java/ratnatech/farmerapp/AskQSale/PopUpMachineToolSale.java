package ratnatech.farmerapp.AskQSale;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import ratnatech.farmerapp.R;

public class PopUpMachineToolSale extends Activity {

    TextView machineName;String strmname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_pop_up_machine_tool_sale);
        WindowManager.LayoutParams params = getWindow().getAttributes();
    /*    params.x = 0;
        params.height = 1000;
        params.width = 700;
        params.y = 0;

        this.getWindow().setAttributes(params);
*/
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        getWindow().setLayout(width, height);
        setFinishOnTouchOutside(false);// to prevent activity from dismissing on touching outside

        machineName=findViewById(R.id.machineName);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            strmname = bundle.getString("MACHINE_NAME");

            machineName.setText(strmname);
        }




    }
    public void onClickOk(View view)
    {
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
    }
    public void onClickCancel(View view)
    {
        finish();
    }
}
