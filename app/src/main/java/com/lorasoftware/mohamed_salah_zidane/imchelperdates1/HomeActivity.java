package com.lorasoftware.mohamed_salah_zidane.imchelperdates1;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Fragments.ClinicFragment;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Fragments.ContactFragment;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Fragments.ExpertFragment;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity implements ClinicFragment.OnFragmentInteractionListener,ExpertFragment.OnFragmentInteractionListener,
SearchFragment.OnFragmentInteractionListener,ContactFragment.OnFragmentInteractionListener{



    private BottomNavigationView mMainNav;
    private FrameLayout mMainframe;
    private ClinicFragment clinicFragment;
   /* private DoctorsFragment doctorsFragment;*/
    private ExpertFragment expertFragment;
    private SearchFragment searchFragment;
    private ContactFragment contactFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mMainframe = (FrameLayout)findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);

        clinicFragment = new ClinicFragment();
   /*     doctorsFragment = new DoctorsFragment();*/
        searchFragment = new SearchFragment();
        expertFragment = new ExpertFragment();
        contactFragment = new ContactFragment();



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,clinicFragment);
        fragmentTransaction.commit();


        mMainNav.setSelectedItemId(R.id.clinic);


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.clinic:
                        setFragment(clinicFragment);
                     //  startActivity(new Intent(HomeActivity.this, MainHomeActivity.this ));
                        return true;
                    case R.id.expert:
                        setFragment(expertFragment);
                        return true;
                    case R.id.contactUs:
                        setFragment(contactFragment);
                        return true;
                    default:
                        return false;
                }
            }


        });
    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
