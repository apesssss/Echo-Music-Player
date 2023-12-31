package com.project.musicplayerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Allsongs> FavouritesArrayList;
    private FavouritesAdapter favouritesAdapter;
    private String[] songname;
    private String[] artistname;
    private RecyclerView recyclerview;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment FavouritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public void setFavouritesList(ArrayList<Allsongs> favouritesList) {
        this.FavouritesArrayList = favouritesList;
        if (favouritesAdapter != null) {
            favouritesAdapter.setFavourites(favouritesList);
        }
    }

    private void saveFavoritesListToSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String jsonFavoritesList = gson.toJson(FavouritesArrayList);
        editor.putString("FAVORITES_LIST", jsonFavoritesList);
        editor.apply();
    }

    private void retrieveFavoritesListFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String jsonFavoritesList = sharedPreferences.getString("FAVORITES_LIST", null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Allsongs>>() {}.getType();
        FavouritesArrayList = gson.fromJson(jsonFavoritesList, type);

        if (FavouritesArrayList == null) {
            FavouritesArrayList = new ArrayList<>();
        }
    }

    public static FavouritesFragment newInstance(String param1, String param2) {
        FavouritesFragment fragment = new FavouritesFragment();
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
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Favourites");
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        retrieveFavoritesListFromSharedPreferences();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String jsonFavoritesList = sharedPreferences.getString("FAVORITES_LIST", null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Allsongs>>() {}.getType();
        FavouritesArrayList = gson.fromJson(jsonFavoritesList, type);

        if (FavouritesArrayList == null) {
            FavouritesArrayList = new ArrayList<>();
        }

        recyclerview=view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);

        favouritesAdapter=new FavouritesAdapter(getContext(),FavouritesArrayList);
        recyclerview.setAdapter(favouritesAdapter);
        favouritesAdapter.notifyDataSetChanged();

    }
    private void dataInitialize() {
        FavouritesArrayList = new ArrayList<>();
        songname=new String[]{
                getString(R.string.song_1),
                getString(R.string.song_4),
                getString(R.string.song_6),
        };
        artistname=new String[]{
                getString(R.string.artist_1),
                getString(R.string.artist_4),
                getString(R.string.artist_6)
        };

       /* for(int i=0;i< songname.length;i++){
            Favourites favourites=new Favourites(songname[i],artistname[i] ) ;
            FavouritesArrayList.add(favourites);
        } */
    }
}