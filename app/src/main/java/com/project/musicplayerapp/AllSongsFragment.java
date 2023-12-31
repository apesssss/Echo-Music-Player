package com.project.musicplayerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AllSongsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[] songname;
    private String[] artistname;
    private ArrayList<Allsongs> allsongsArrayList;
    private ArrayList<Allsongs> FavouritesArrayList;
    private RecyclerView recyclerview;
    private AllsongsAdapter allsongsAdapter;

    public AllSongsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllSongsFragment newInstance(String param1, String param2) {
        AllSongsFragment fragment = new AllSongsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("All Songs");
        return inflater.inflate(R.layout.fragment_all_songs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        recyclerview = view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);

        AllsongsAdapter allsongsAdapter=new AllsongsAdapter(getContext(),allsongsArrayList,FavouritesArrayList);
        recyclerview.setAdapter(allsongsAdapter);
        allsongsAdapter.notifyDataSetChanged();

    }


     private void dataInitialize() {
        allsongsArrayList = new ArrayList<>();
        songname=new String[]{
                getString(R.string.song_1),
                getString(R.string.song_2),
                getString(R.string.song_3),
                getString(R.string.song_4),
                getString(R.string.song_5),
                getString(R.string.song_6),
                getString(R.string.song_7),
                getString(R.string.song_8),
        };
        artistname=new String[]{
                getString(R.string.artist_1),
                getString(R.string.artist_2),
                getString(R.string.artist_3),
                getString(R.string.artist_4),
                getString(R.string.artist_5),
                getString(R.string.artist_6),
                getString(R.string.artist_7),
                getString(R.string.artist_8),
        };
        int[] songResourceIds = new int[]{
                R.raw.yellow,
                R.raw.cake_by_the_ocean,
                R.raw.poker_face,
                R.raw.jealous,
                R.raw.night_changes,
                R.raw.radio,
                R.raw.die_for_you,
                R.raw.do_i_wanna_know,
        };
        for(int i=0;i< songname.length;i++){
            Allsongs allsongs=new Allsongs(songname[i],artistname[i],songResourceIds[i]) ;
            allsongsArrayList.add(allsongs);
        }
    }
}