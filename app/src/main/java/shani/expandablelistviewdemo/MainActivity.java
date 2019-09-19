package shani.expandablelistviewdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDtaChild;
    ExpandableListSetAdapter expandableListSetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.lvExp);
        //prepare the list for header and child
        prepareListData();
        //Invoking the adapter to transfer data on ExpandableListView
        expandableListSetAdapter = new ExpandableListSetAdapter(this, listDataHeader, listDtaChild);
        expandableListView.setAdapter(expandableListSetAdapter);
        //Set Group Click Listner
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Group Clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Group Clicked");
                alert.setMessage("Clicked");
                alert.show();
                return false;
            }
        });
        //Listview Group ExpandListner
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_SHORT).show();

            }
        });
        //ListView Group collasped Listner
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, "Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupposition, int childPosition, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(listDataHeader.get(groupposition));
                alert.setMessage(listDtaChild.get(listDataHeader.get(groupposition)).get(childPosition));
                alert.setCancelable(true);
                alert.show();
                Toast.makeText(MainActivity.this, "; " + listDtaChild.get(listDataHeader.get(groupposition)).get(childPosition), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDtaChild = new HashMap<>();

        //Adding Child data
        listDataHeader.add("Topics");
        listDataHeader.add("Topics Coverd");
        listDataHeader.add("Topics not Coverd");


        List<String> top250 = new ArrayList<String>();
        top250.add("A");
        top250.add("B");
        top250.add("C");
        top250.add("D");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Android");
        nowShowing.add("Android Arch..");
        nowShowing.add("Android SDK");
        nowShowing.add("Android UI");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Android  Maps ");
        comingSoon.add("Android Bluetooth ");
        comingSoon.add("Android WIFI ");


        listDtaChild.put(listDataHeader.get(0), top250); //Header, Child data
        listDtaChild.put(listDataHeader.get(1), nowShowing);
        listDtaChild.put(listDataHeader.get(2), comingSoon);

    }
}
