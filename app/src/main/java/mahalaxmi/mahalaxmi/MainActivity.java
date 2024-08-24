package mahalaxmi.mahalaxmi;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    private boolean[] isCheckedSwitch = new boolean[5];

    ArrayList<Fragment> arrayList = new ArrayList<>();

    SharedPreferences mPrefs;
    Deque<String> tabNameStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

      //  isCheckedSwitch[0]= mPrefs.getBoolean(SettingsActivity.PREF_SWITCH1, true);
        isCheckedSwitch[1]= mPrefs.getBoolean(SettingsActivity.PREF_SWITCH2, true);
        isCheckedSwitch[2]= mPrefs.getBoolean(SettingsActivity.PREF_SWITCH3, true);
        isCheckedSwitch[3]= mPrefs.getBoolean(SettingsActivity.PREF_SWITCH4, true);
        isCheckedSwitch[4]= mPrefs.getBoolean(SettingsActivity.PREF_SWITCH5, true);

        arrayList.add(new Fragment_one());
        if(isCheckedSwitch[1]) arrayList.add(new Fragment_two());
        if(isCheckedSwitch[2])arrayList.add(new Fragment_three());
        if(isCheckedSwitch[3])arrayList.add(new Fragment_four());
        if(isCheckedSwitch[4])arrayList.add(new Fragment_five());

        tabNameStack = new ArrayDeque<String>();
        if(isCheckedSwitch[4])tabNameStack.push("BILL");
        if(isCheckedSwitch[3])tabNameStack.push("DENOM");
        if(isCheckedSwitch[2])tabNameStack.push("T-D");
        if(isCheckedSwitch[1])tabNameStack.push("O-I");




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            catch (Exception e){
                showToast(e.toString(), getBaseContext());
            }
        }

        if (id == R.id.about) {
            Intent intent = new Intent(this, About_Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            if (position == 0){
                return arrayList.get(0);}
            else if (position == 1){
               return arrayList.get(1);}
            else if (position == 2){
                return arrayList.get(2);}
            else if (position == 3){
                return arrayList.get(3);}
            else {return arrayList.get(4);}
            //return arrayList.get(0);
        }


        @Override
        public int getCount() {
           return arrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            try {
                switch (position) {
                    case 0:
                        return "I-O";
                    case 1:
                        // return "O-I";
                        return tabNameStack.pop();
                    case 2:
                        // return "T-D";
                        return tabNameStack.pop();
                    case 3:
                        // return "DENOM";
                        return tabNameStack.pop();
                    case 4:
                        //  return "BILL";
                        return tabNameStack.pop();
                }
                return null;
            }
            catch (Exception e){
                MainActivity.showToast(e.toString(), getBaseContext());
                return null;
            }
        }



    }

    public static void showToast(String msg, Context context){
        Toast toast = Toast.makeText(context, "Error\n" + msg, Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }




}
