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

import ratnatech.farmerapp.AskQGovtSchemes.GSAsk;
import ratnatech.farmerapp.AskQSoilCW.AskQSoilCW;
import ratnatech.farmerapp.Ask_Agriculture;
import ratnatech.farmerapp.Ask_Fishery;
import ratnatech.farmerapp.Ask_Veterinary;
import ratnatech.farmerapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyStory extends Fragment implements View.OnClickListener{


    public MyStory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false);//  fragment_mystory
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("My Story");

        ImageView agriculture=getView().findViewById(R.id.agriculture);
        ImageView veterinary=getView().findViewById(R.id.veterinary);
        ImageView fishery=getView().findViewById(R.id.fishery);
        ImageView govtschemes=getView().findViewById(R.id.govtschemes);
        TextView soilcw=getView().findViewById(R.id.soilcw);

        agriculture.setOnClickListener(this);
        veterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ask_Veterinary.class);
                startActivity(intent);
            }
        });
        fishery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ask_Fishery.class);//
                startActivity(intent);
            }
        });
        govtschemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GSAsk.class);//Ask_GovtSchemes
                startActivity(intent);
            }
        });

        soilcw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AskQSoilCW.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Ask_Agriculture.class);
        startActivity(intent);
    }
}
