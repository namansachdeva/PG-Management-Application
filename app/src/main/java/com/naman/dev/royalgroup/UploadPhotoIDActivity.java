package com.naman.dev.royalgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadPhotoIDActivity extends AppCompatActivity {
    private FirebaseStorage storage;
    private StorageReference storageRef;

    private Button selectPhotobtn;
    private Button selectIDbtn;
    private Button uploadImgbtn;

    private ImageView photoImageView;
    private ImageView idImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo_id);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://royal-group-72851.appspot.com/");

        selectPhotobtn = (Button) findViewById(R.id.selectphotoImg);
        selectIDbtn = (Button) findViewById(R.id.selectidImg);
        uploadImgbtn = (Button) findViewById(R.id.uploadImages);

        photoImageView = (ImageView) findViewById(R.id.photoimgView);
        idImageView= (ImageView) findViewById(R.id.idimgView);

        selectPhotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
