package com.example.campusesmt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FormationFragment extends Fragment {
    private ListView listFormation;
    private  String formation , details;
    private  String[] tabFormations, tabDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formation, container, false);
        listFormation = view.findViewById(R.id.listFormation);
        //pour recuperer la liste des formation
        tabFormations = getResources().getStringArray(R.array.tab_formations);
        //por recuperer la detail de la formation
        tabDetails = getResources().getStringArray(R.array.tab_details);
        //pour charger les données dans la liste sous forme de dialogue
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,tabFormations);
        //chargement de données sur la liste
        listFormation.setAdapter(adapter);
        //gerer le click sur un element choisi
        listFormation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                formation = tabFormations[position];
                details = tabDetails[position];
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(formation);
                dialog.setMessage(details);
                dialog.setNegativeButton(getString(R.string.back),null);
                dialog.setPositiveButton(getString(R.string.inscription), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                           getFragmentManager()
                           .beginTransaction()
                           .replace(R.id.nav_host_fragment,new InscriptionFragment())
                           .addToBackStack(null)
                           .commit();
                    }
                });
                dialog.show();
            }
        });
        return  view;

    }
}