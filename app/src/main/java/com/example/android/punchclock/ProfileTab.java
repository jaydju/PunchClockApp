package com.example.android.punchclock;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ProfileTab extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Contact> lstContact;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.activity_profile_tab, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.places_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter (getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
        lstContact.add(new Contact("Super-Fun Friday Gala", "5/7/2019", R.drawable.on_time, "On Time!"));
        lstContact.add(new Contact("Emily's Birthday Party", "5/3/2019", R.drawable.late, "36 min late"));
        lstContact.add(new Contact("Nigel Frazier's Java Class", "Every Tuesday", R.drawable.late, "Always Late?!"));
        lstContact.add(new Contact("Super-Fun Friday Gala", "5/7/2019", R.drawable.on_time, "On Time!"));
        lstContact.add(new Contact("Emily's Birthday Party", "5/3/2019", R.drawable.late, "36 min late"));
        lstContact.add(new Contact("Nigel Frazier's Java Class", "Every Tuesday", R.drawable.late, "Always Late?!"));
        lstContact.add(new Contact("Super-Fun Friday Gala", "5/7/2019", R.drawable.on_time, "On Time!"));
        lstContact.add(new Contact("Emily's Birthday Party", "5/3/2019", R.drawable.late, "36 min late"));
        lstContact.add(new Contact("Nigel Frazier's Java Class", "Every Tuesday", R.drawable.late, "Always Late?!"));
        lstContact.add(new Contact("Super-Fun Friday Gala", "5/7/2019", R.drawable.on_time, "On Time!"));
        lstContact.add(new Contact("Emily's Birthday Party", "5/3/2019", R.drawable.late, "36 min late"));
        lstContact.add(new Contact("Nigel Frazier's Java Class", "Every Tuesday", R.drawable.late, "Always Late?!"));
        lstContact.add(new Contact("Super-Fun Friday Gala", "5/7/2019", R.drawable.on_time, "On Time!"));
        lstContact.add(new Contact("Emily's Birthday Party", "5/3/2019", R.drawable.late, "36 min late"));
        lstContact.add(new Contact("Nigel Frazier's Java Class", "Every Tuesday", R.drawable.late, "Always Late?!"));
    }

}
