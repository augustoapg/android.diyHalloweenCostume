package sheridan.araujope.diyhalloweencostume;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCostume extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private ImageView mImgCamera;
    private TextInputEditText mCostumeName;
    private TextInputEditText mItemName;
    private TextInputEditText mNotes;
    private ChipGroup mChipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_costume);
        
        mImgCamera = findViewById(R.id.imgCamera);
        mCostumeName = findViewById(R.id.txtCostumeName);
        mItemName = findViewById(R.id.txtListItem);
        mNotes = findViewById(R.id.txtNotes);
        mChipGroup = findViewById(R.id.chipGroup);
        
        addImageClickHandler();
    }

    private void addImageClickHandler() {
        mImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

}
