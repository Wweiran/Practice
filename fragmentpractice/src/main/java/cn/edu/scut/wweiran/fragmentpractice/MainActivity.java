package cn.edu.scut.wweiran.fragmentpractice;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_LOGIN = 0;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout mFrameLayout;

    private ActionProvider mShareActionProvider;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        MenuItem menuItem = menu.findItem(R.id.main_shared);
        mShareActionProvider = MenuItemCompat.getActionProvider(menuItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.main_shared:


                //分享文本内容
                Intent sharedIntent = new Intent();
                sharedIntent.setAction(Intent.ACTION_SEND);
                sharedIntent.putExtra(Intent.EXTRA_TEXT, "Shared Text");
                sharedIntent.setType("text/plain");
//                if (sharedIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(sharedIntent);
//                }
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> lists = packageManager.queryIntentActivities(sharedIntent, 0);
                if (lists.size() > 0) {
//                    startActivity(Intent.createChooser(sharedIntent, "分享的内容"));
                    startActivity(sharedIntent);
//                    Toast.makeText(this, String.valueOf(lists.size()), Toast.LENGTH_SHORT).show();
                }

                //写文件
                /*try {
                    FileOutputStream fileOutputStream = openFileOutput("test.txt", MODE_PRIVATE);
                    fileOutputStream.write(new byte[]{15,54,66,29,85});
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //分享二进制内容，经常用于发送图片等
                /*Intent binaryIntent = new Intent();
                binaryIntent.setAction(Intent.ACTION_SEND);
                binaryIntent.putExtra(Intent.EXTRA_STREAM, Uri);
                binaryIntent.setType("image/jpeg");
                startActivity(binaryIntent);*/
                break;
            default:
                break;
        }


        return true;
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fragment_main:
                MainFragment mainFragment = MainFragment.newInstance("主页");

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.fragment_add:
                MainFragment addFragment = MainFragment.newInstance("ADD_Fragment");

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, addFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack("add")
                        .commit();
                break;
            case R.id.fragment_1:
                MainFragment mainFragment1 = MainFragment.newInstance("fragment_1");

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, mainFragment1)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.fragment_2:
                Intent newActivityIntent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(newActivityIntent);
                break;
            case R.id.fragment_3:
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(loginIntent, REQUEST_LOGIN);
                break;
            case R.id.activity_1:
                Intent saveDataIntent = new Intent(MainActivity.this, SaveDataActivity.class);
                startActivity(saveDataIntent);
                break;
            case R.id.activity_2:
                Intent implicitIntent = new Intent(MainActivity.this, ImplicitActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.activity_3:
                Intent multimediaIntent = new Intent(MainActivity.this, MultiMediaActivity.class);
                startActivity(multimediaIntent);
                break;
            case R.id.activity_4:
                Intent keyEventIntent = new Intent(MainActivity.this, KeyEventActivity.class);
                startActivity(keyEventIntent);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
