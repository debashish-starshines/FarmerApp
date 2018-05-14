package ratnatech.farmerapp.GuestUser;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ratnatech.farmerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestCategory extends Fragment {


    public GuestCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_category, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Purchase");
        ImageView agrilproduct=getView().findViewById(R.id.agrilproduct);
        ImageView machinery=getView().findViewById(R.id.machinery);
        ImageView veterinaryitem=getView().findViewById(R.id.veterinaryitem);
        ImageView fishery=getView().findViewById(R.id.fishery);
        ImageView other=getView().findViewById(R.id.other);


        agrilproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Guest_Agril_Product.class);
                startActivity(intent);
            }
        });
        machinery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Guest_Machinery.class);
                startActivity(intent);
            }
        });
        veterinaryitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Guest_VeterinaryItem.class);
                startActivity(intent);
            }
        });

        fishery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Guest_Fishery.class);
                startActivity(intent);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Guest_Other.class);
                startActivity(intent);
            }
        });

    }

}
