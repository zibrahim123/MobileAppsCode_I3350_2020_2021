package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class Task_Fragment extends Fragment {
    ListView l;
    ArrayAdapter<String>adapter;
    ArrayList<String> Strings;
    ArrayList<String> Array;
    FragmentTransaction transaction;
    DataBaseHandler db;
    public Task_Fragment() {
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
        return inflater.inflate(R.layout.fragment_task_, container, false);
    }
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);
        l = (ListView) getActivity().findViewById(R.id.list);
        final DataBaseHandler db = new DataBaseHandler(getActivity());
        Strings = db.getAllExpenses();
        Array = new ArrayList<String>();
        for (int i = 0; i < Strings.size(); i++) {
            //if (Strings.get(i).getFinished() == 0) {
            //    Array.add(Strings.get(i).getTitle());
            //}
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Array);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View row, int index, long rowID) {
                Intent intent;

                intent = new Intent(getActivity(), View_Fragment.class);
                //intent.putExtra("title", Strings.get(index).getTitle());
                //intent.putExtra("desc", Strings.get(index).getDescription());
                //intent.putExtra("cat", Strings.get(index).getCategory());
                startActivity(intent);
                View_Fragment fra2 = new View_Fragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1, fra2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        /*
        l.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int i = Strings.get(position).getId();
                db.update(i, Strings.get(position).getTitle(), Strings.get(position).getDescription(), Strings.get(position).getCategory(), 1);
                adapter.remove(adapter.getItem(position));
                return false;
            }

        });*/
    }



}

