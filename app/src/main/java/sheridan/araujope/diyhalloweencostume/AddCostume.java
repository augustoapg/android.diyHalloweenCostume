package sheridan.araujope.diyhalloweencostume;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class AddCostume extends AppCompatActivity {

    private static final int PICK_PHOTO_FROM_GALERY = 2;
    private static final String TAG = "AddCostumeDebug";
    private ImageView mImgCamera;
    private TextInputEditText mCostumeName;
    private TextInputEditText mItemName;
    private TextInputEditText mNotes;
    private ChipGroup mChipGroup;
    private Bitmap imageBitmap;
    private ImageButton mBtnAddItem;
    private Button mBtnSaveCostume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_costume);
        final Context context = this.getBaseContext();
        
        mImgCamera = findViewById(R.id.imgCamera);
        mCostumeName = findViewById(R.id.txtCostumeName);
        mItemName = findViewById(R.id.txtListItem);
        mNotes = findViewById(R.id.txtNotes);
        mChipGroup = findViewById(R.id.chipGroup);
        mBtnAddItem = findViewById(R.id.btnAddItem);
        mBtnSaveCostume = findViewById(R.id.btnAddCostume);
        
        addImageClickHandler();

        mBtnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItem();
            }
        });
        
        mBtnSaveCostume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCostume();
            }
        });
    }

    private void saveCostume() {
    }

    private void addNewItem() {
        String itemName = mItemName.getText().toString().trim();

        if (itemName != null && !itemName.isEmpty()) {
            final Chip chip = new Chip(AddCostume.this);
            chip.setText(itemName);
            chip.setCloseIconEnabled(true);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setIconStartPadding(10);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChipGroup.removeView(chip);
                }
            });
            mChipGroup.addView(chip);

            mItemName.setText("");
        }
    }

    private void addImageClickHandler() {
        mImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, PICK_PHOTO_FROM_GALERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult: activity result");

        Context context = this.getBaseContext();

        if (requestCode == PICK_PHOTO_FROM_GALERY && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show();
                return;
            }
            
            try {
                Log.i(TAG, "onActivityResult: data was not null");
                InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
                imageBitmap = BitmapFactory.decodeStream(inputStream);
                mImgCamera.setImageBitmap(imageBitmap);
                Toast.makeText(context, "Image selected", Toast.LENGTH_SHORT);
            } catch (Exception e) {
                Log.i(TAG, "onActivityResult: error with input stream");
                Toast.makeText(context, "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
