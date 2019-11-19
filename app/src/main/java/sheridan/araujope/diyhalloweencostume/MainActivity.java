/**
 * Project: DIY Halloween Costume
 * Author: Augusto A P Goncalez
 * Date: Nov. 18, 2019
 */

package sheridan.araujope.diyhalloweencostume;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sheridan.araujope.diyhalloweencostume.beans.Costume;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnCostumeListener {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "MainActivityDebug";
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    FloatingActionButton fab;

    private ArrayList<Costume> mCostumes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCostume.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mAdapter = new RecyclerViewAdapter(this, mCostumes, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Costume costume = (Costume)data.getSerializableExtra("costume");
                System.out.println(costume);

                mCostumes.add(costume);

                mAdapter.notifyItemInserted(mCostumes.size() - 1);
                Toast toast = Toast.makeText(this,
                        costume.getName() + " has been added to the list =D", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public void onCostumeClick(int position) {
        Log.i(TAG, "onCostumeClick: " + position);
        Intent intent = new Intent(MainActivity.this, CostumeDetails.class);
        intent.putExtra("costume", mCostumes.get(position));
        startActivity(intent);
    }
}
