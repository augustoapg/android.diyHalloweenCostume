/**
 * Project: DIY Halloween Costume
 * Author: Augusto A P Goncalez
 * Date: Nov. 18, 2019
 */

package sheridan.araujope.diyhalloweencostume;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sheridan.araujope.diyhalloweencostume.beans.Costume;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> items = new ArrayList<>();
    private String filePath = "";

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

    private void addNewItem() {
        final String itemName = mItemName.getText().toString().trim();

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
                    items.remove(chip.getText().toString());
                }
            });
            mChipGroup.addView(chip);
            items.add(itemName);

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
                filePath = tempFileImage(context,imageBitmap,"imageCostume");
            } catch (Exception e) {
                Log.i(TAG, "onActivityResult: error with input stream");
                Toast.makeText(context, "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveCostume() {
        String name = mCostumeName.getText().toString().trim();
        if (name != null && !name.isEmpty()) {
            String notes = mNotes.getText().toString().trim();

            Costume costume = new Costume(name, filePath, items, notes);
            Intent homeIntent = new Intent();
            homeIntent.putExtra("costume", costume);
            setResult(RESULT_OK, homeIntent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.no_name_error),
                    Toast.LENGTH_SHORT).show();
        }
    }

    //creates a temporary file and return the absolute file path
    //source: https://stackoverflow.com/questions/31426447/how-to-send-large-byte-arrays-between-activities-in-android
    public static String tempFileImage(Context context, Bitmap bitmap, String name) {

        File outputDir = context.getCacheDir();
        File imageFile = new File(outputDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing file", e);
        }

        return imageFile.getAbsolutePath();
    }
}
