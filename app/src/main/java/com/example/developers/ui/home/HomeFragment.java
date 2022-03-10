package com.example.developers.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.developers.R;
import com.example.developers.RviewAdapter;
import com.example.developers.Data;
import com.example.developers.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static String url ="https://wayhike.com/dsc/demo_app_api.php";

    ArrayList<Data> list;
    RecyclerView recyclerView;
    RequestQueue mQueue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.rView);
        list = new ArrayList<Data>();

        mQueue = Volley.newRequestQueue(getContext());

        //setUserFrom_json();
        //setUserFron_string();
        setUserFron_string1();
        setUserInfo();
        setAdapter();

        return root;
    }

    private void setUserFron_string1() {

        list.add(new Data("Adobe XD Scratchclass - Introduction"));
        list.add(new Data("Adobe XD Scratchclass - Views"));
        list.add(new Data("DeveLup Series - Flutter Zero to Hero"));
        list.add(new Data("DeveLup Series - Getting started with LaTeX"));
        list.add(new Data("DeveLup Series - Graphic Designing - Intermediate"));
        list.add(new Data("DeveLup Series - Introduction to JavaScript"));
        list.add(new Data("DeveLup Series - Kickstart with Firebase"));
        list.add(new Data("DeveLup Series - Machine Learning Novice to Jarvis"));
        list.add(new Data("DeveLup Series - Problem Solving with Design Thinking"));
        list.add(new Data("DeveLup Series - Touring google Cloud"));
        list.add(new Data("DeveLup Series - Unboxing Mixed Reality"));
        list.add(new Data("DeveLup Series Launch"));
        list.add(new Data("Discuss with DSC - Blockchain and Pi cryptocurrency"));
        list.add(new Data("Discuss with DSC - Data Engineering with Spark using Databricks"));
        list.add(new Data("Free Practical Cloud course"));
        list.add(new Data("Latest Innovation and Trends in Flutter"));

    }

    private void setUserFron_string() {

        String str = null;
        List<String> hop = Arrays.asList(str.split(","));
        String ad = hop.get(0);
        ad = ad.substring(18,54);
        //System.out.println(ad);

        list.add(new Data(ad));

        for(int i=1;i< hop.size()-1;i++){
            ad = hop.get(i);
            ad = ad.substring(1,ad.length()-1);
            list.add(new Data(ad));
        }

        ad = hop.get(hop.size()-1);
        ad = ad.substring(1,ad.length()-3);
        list.add(new Data(ad));

    }

    private void setAdapter() {

        RviewAdapter adapter = new RviewAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setUserInfo() {

        list.add(new Data("Adobe XD event"));

    }

    private void setUserFrom_json(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("event_titles");

                    String str = response.toString();
                    List<String> hop = Arrays.asList(str.split(","));
                    String ad = hop.get(0);
                    ad = ad.substring(18,54);
                    //System.out.println(ad);

                    list.add(new Data(ad));

                    for(int i=1;i< hop.size()-1;i++){
                        ad = hop.get(i);
                        ad = ad.substring(1,ad.length()-1);
                        list.add(new Data(ad));
                    }

                    ad = hop.get(hop.size()-1);
                    ad = ad.substring(1,ad.length()-3);
                    list.add(new Data(ad));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void put(List<String> hop){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}