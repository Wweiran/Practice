package cn.edu.scut.wweiran.fragmentpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    private static final int REQUEST_LOGIN = 0;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mFrameLayout = (FrameLayout) findViewById(R.id.fragment_container);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mNavigationView.setNavigationItemSelectedListener(this);

        if (mFrameLayout != null) {
            if (savedInstanceState != null) {
                return;
            }
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }


        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fragment_main:
                MainFragment mainFragment = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("MAIN", "主页");
                mainFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .commit();
                break;
            case R.id.fragment_1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                MainFragment mainFragment1 = new MainFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("MAIN", "fragment_1");
                mainFragment1.setArguments(bundle1);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, mainFragment1)
                        .commit();

                break;
            case R.id.fragment_2:
                Intent newActivityIntent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(newActivityIntent);
                break;
            case R.id.fragment_3:
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(loginIntent,REQUEST_LOGIN);
                break;
            case R.id.fragment_4:
                Intent saveDataIntent = new Intent(MainActivity.this, SaveDataActivity.class);
                startActivity(saveDataIntent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
