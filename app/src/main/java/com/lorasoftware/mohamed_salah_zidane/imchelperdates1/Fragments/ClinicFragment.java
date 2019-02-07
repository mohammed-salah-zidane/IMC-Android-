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
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters.ClinicListAdapter;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.HelperClasses.URLs;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ClinicListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClinicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClinicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClinicFragment extends Fragment implements SearchView.OnQueryTextListener{
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
    private List<ClinicListItem> clinicListItems;
    SwipeRefreshLayout swipeRefreshLayout;
    private  String JSON_URL ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private static ProgressDialog progressDialog;




    private OnFragmentInteractionListener mListener;

    public ClinicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClinicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClinicFragment newInstance(String param1, String param2) {
        ClinicFragment fragment = new ClinicFragment();
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


        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.simpleSwipeRefreshLayout);

        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);



        progressDialog = new ProgressDialog(mView.getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adapter = new ClinicListAdapter(clinicListItems,view.getContext());
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
        request = new JsonArrayRequest(Request.Method.GET, URLs.URL_CLINIC, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // response
                        //Log.d("Response", response.toString());

                        progressDialog.dismiss();
                        clinicListItems.clear();
                        JSONObject jsonObject;
                        for (int i = 0 ; i < response.length(); i++ ) {

                            try {

                                jsonObject = response.getJSONObject(i) ;
                                ClinicListItem _clinic = new ClinicListItem() ;
                                _clinic.setClinic_id(jsonObject.getInt("id"));

                                _clinic.setName(jsonObject.getString("name"));

                                _clinic.setFloor_num(jsonObject.getInt("flo_n"));


                                 Log.d("Name",_clinic.getName());

                                clinicListItems.add(_clinic);

                            } catch (JSONException e) {
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.fragment_clinic, container, false);
        // Inflate the layout for this fragment
        clinicListItems = new ArrayList<>();
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
                    + " must implement OnFragmentInteractionListener ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
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