package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters.Experts_Adapter;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.HelperClasses.URLs;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ExpertsListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpertFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpertFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View mView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ExpertsListItem> expertsListItems;

    SwipeRefreshLayout swipeRefreshLayout;
    private  String JSON_URL ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private OnFragmentInteractionListener mListener;
    private static ProgressDialog progressDialog;

    public ExpertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpertFragment newInstance(String param1, String param2) {
        ExpertFragment fragment = new ExpertFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);



        progressDialog = new ProgressDialog(mView.getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        /*for (int i = 0; i <=5; i++) {

            ExpertsListItem expertsListItem = new ExpertsListItem("ا.د مجدي سابا", "الجنسية/أمريكي",
                    "من 2018/7/24 الي 2018/7/27",
            "التخصص العام /أمراض القلب", R.drawable.sq_icon_doctors);

            expertsListItems.add(expertsListItem);

        }*/

        adapter = new Experts_Adapter(view.getContext(),expertsListItems);
        recyclerView.setAdapter(adapter);


        JSON_URL = URLs.URL_CLINIC;

        jsonrequest();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                // Toast.makeText(mView.getContext(),"swip",Toast.LENGTH_LONG).show();

                jsonrequest();
            }
        });
    }

    private void jsonrequest() {

        requestQueue = Volley.newRequestQueue(mView.getContext());

        String url = "http://zidan.sobelnaza.com/api/clinics";

     /*   final SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();*/



        ConnectivityManager cm =
                (ConnectivityManager) mView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            progressDialog.show();
        //final JSONObject jsonBody = new JSONObject("{\"type\":\"example\"}");
        request = new JsonArrayRequest(Request.Method.GET, URLs.URL_SPECIALIST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // response
                        //Log.d("Response", response.toString());

                        progressDialog.dismiss();
                        expertsListItems.clear();
                        JSONObject jsonObject;
                        for (int i = 0 ; i < response.length(); i++ ) {

                            try {
                                jsonObject = response.getJSONObject(i) ;

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String fromStr = jsonObject.getString("from");
                                String toStr = jsonObject.getString("to");
                                Log.d("test",toStr);
                                Date from = sdf.parse(fromStr);
                                Date to = sdf.parse(toStr);
                                ExpertsListItem expert = new ExpertsListItem(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("nationality"),jsonObject.getString("public_sp")
                                        ,jsonObject.getString("accurate_sp"),fromStr,toStr);
                                expertsListItems.add(expert);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        progressDialog.dismiss();
                        //Log.d("Error.Response", error.toString());
                        Toast.makeText(mView.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        ;
        requestQueue.add(request);
        } else {
            Toast.makeText(mView.getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_clinic, container, false);
        // Inflate the layout for this fragment
        expertsListItems = new ArrayList<>();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
