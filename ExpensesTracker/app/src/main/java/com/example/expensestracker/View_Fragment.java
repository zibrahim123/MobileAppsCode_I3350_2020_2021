package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class View_Fragment extends Fragment {
Intent intent;
    String t,d,c;
  TextView ti,de,ca;
    public View_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_, container, false);
    }
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        ti=(TextView) getActivity().findViewById(R.id.T);
        de=(TextView)getActivity().findViewById(R.id.D);
        ca=(TextView)getActivity().findViewById(R.id.C);
        intent=getActivity().getIntent();
       t=intent.getStringExtra("title");
        d=intent.getStringExtra("desc");
        c=intent.getStringExtra("cat");
        ti.setText(t);
        de.setText(d);
       ca.setText(c);


    }

}
