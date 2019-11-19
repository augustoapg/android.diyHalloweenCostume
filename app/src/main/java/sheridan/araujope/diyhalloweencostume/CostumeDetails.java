/**
 * Project: DIY Halloween Costume
 * Author: Augusto A P Goncalez
 * Date: Nov. 18, 2019
 */

package sheridan.araujope.diyhalloweencostume;

import androidx.appcompat.app.AppCompatActivity;
import sheridan.araujope.diyhalloweencostume.beans.Costume;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CostumeDetails extends AppCompatActivity {

    private static final String TAG = "detailsDebug";
    private TextView mName, mNotes, mItemsNeeded;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_details);

        Intent intent = getIntent();
        Costume costume = (Costume)intent.getSerializableExtra("costume");
        Log.i(TAG, "onCreate: here");

        mName = findViewById(R.id.txtNameDetails);
        mNotes = findViewById(R.id.txtNotes);
        mItemsNeeded = findViewById(R.id.txtItemsNeeded);
        mImage = findViewById(R.id.imageDetails);

        mName.setText(costume.getName());
        mNotes.setText(costume.getNotes());

        String itemsNeededFormated = "";

        for(int i = 0; i < costume.getItems().size(); i++) {
            if (i < costume.getItems().size() - 1) {
                itemsNeededFormated += costume.getItems().get(i) + " | ";
            } else {
                itemsNeededFormated += costume.getItems().get(i);
            }
        }
        mItemsNeeded.setText(itemsNeededFormated);

        Picasso.with(this).load(costume.getImage()).fit().into(mImage);
    }
}
