package ratnatech.farmerapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ratnatech.farmerapp.AskQRepair.RepairMachines;
import ratnatech.farmerapp.AskQService.ServicesAvail;
import ratnatech.farmerapp.Ask_Hire;
import ratnatech.farmerapp.Ask_Purchase;
import ratnatech.farmerapp.Ask_Sale;
import ratnatech.farmerapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseSellHireRepair extends Fragment {


    ImageView img_purchase,img_sale,img_hire;
    TextView txt_repair,txt_service;
    public PurchaseSellHireRepair() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_purchase_sell_hire_repair, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Purchase/Sell/Hire/Repair/Service");

        img_purchase=getView().findViewById(R.id.id_purchase);
        img_sale=getView().findViewById(R.id.id_sale);
        img_hire=getView().findViewById(R.id.id_hire);
        txt_repair=getView().findViewById(R.id.id_repair);
        txt_service=getView().findViewById(R.id.id_service);
        img_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Ask_Purchase.class);
                startActivity(intent);
            }
        });

        img_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), Ask_Sale.class);
                startActivity(intent);
            }
        });

        txt_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RepairMachines.class);
                startActivity(intent);

            }
        });
        img_hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Ask_Hire.class);
                startActivity(intent);

            }
        });
        txt_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ServicesAvail.class);
                startActivity(intent);

            }
        });
    }

}
