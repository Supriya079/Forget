package xyz.clutr.forget;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import xyz.clutr.forget.Room.BillDAO;
import xyz.clutr.forget.Room.BillDB;
import xyz.clutr.forget.Room.BillEntity;

public class Form extends AppCompatActivity {

    View viewBackForm;
    AutoCompleteTextView autoCompleteTextView;
    EditText billObjectNameEdittext,billDescriptionEdittext,billLocationEdittext,billImageEdittext;
    ArrayAdapter<String> adapter;
    Button doneBtn;
    String[] strings={"Bills & Invoice","Medical Report","Objects","Documents","My Items"};
    BillDAO billDAO;
    Dialog dialogM;
    Uri uri = null,intentCameraUri=null;
    String galleryImagePath;

    public static final String TIMESTAMP_DATE_FORMAT = "dd MMM yyyy hh:mm:ss a";
    private static final int CAMERA = 9;
    private static final int GALLERY = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //view binding with id's
        viewBackForm = findViewById(R.id.viewFormBack);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        billObjectNameEdittext = findViewById(R.id.editTextBillObjectName);
        billDescriptionEdittext = findViewById(R.id.editTextBillDescription);
        billLocationEdittext = findViewById(R.id.editTextBillLocationDate);
        billImageEdittext =findViewById(R.id.editTextBillImage);

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        doneBtn = findViewById(R.id.button);

        billDAO = BillDB.getBillDBInstance(getApplicationContext()).billDAO();

        doneBtn.setOnClickListener(v -> {
            if(autoCompleteTextView.getText().toString().trim().equals(strings[0])){
                //Bills
                if (billObjectNameEdittext.getText().toString().isEmpty() || billDescriptionEdittext.getText().toString().isEmpty()
                        || billLocationEdittext.getText().toString().isEmpty() || billImageEdittext.getHint().toString().isEmpty()) {
                    Toast.makeText(Form.this,
                            "Data missing",
                            Toast.LENGTH_SHORT).show();
                }else {
                    BillEntity billEntity = new BillEntity();
                    billEntity.setCategory(autoCompleteTextView.getText().toString());
                    billEntity.setObject_Name(billObjectNameEdittext.getText().toString());
                    billEntity.setDescription(billDescriptionEdittext.getText().toString());
                    billEntity.setLocation(billLocationEdittext.getText().toString());
                    billImageEdittext.setHintTextColor(Color.BLACK);
                    billEntity.setImagePath(billImageEdittext.getHint().toString());
                    try {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_DATE_FORMAT);
                        billEntity.setDate(sdf.parse(sdf.format(new Date())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    billDAO.insertBill(billEntity);

                    Toast.makeText(Form.this, "Data Added in: "+(strings[0]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, Bills.class);
                    startActivity(i);
                }
            }
            else if (autoCompleteTextView.getText().toString().trim().equals(strings[1])){
                Toast.makeText(Form.this, "Data Added in: "+(strings[1]), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Form.this, Bills.class);
                startActivity(i);
            }
            else if (autoCompleteTextView.getText().toString().trim().equals(strings[2])){
                Toast.makeText(Form.this, "Data Added in: "+(strings[2]), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Form.this, Bills.class);
                startActivity(i);
            }
            else if (autoCompleteTextView.getText().toString().trim().equals(strings[3])){
                Toast.makeText(Form.this, "Data Added in: "+(strings[3]), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Form.this, Bills.class);
                startActivity(i);
            }
            else if (autoCompleteTextView.getText().toString().trim().equals(strings[4])){
                Toast.makeText(Form.this, "Data Added in: "+(strings[4]), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Form.this, MyStuff.class);
                startActivity(i);
            }
            else {
                autoCompleteTextView.setError("Enter Valid Category.");
            }

        });

        viewBackForm.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });

    }

    @RequiresApi(api = VERSION_CODES.Q)
    @SuppressLint({"ObsoleteSdkInt", "UseCompatLoadingForDrawables", "QueryPermissionsNeeded"})
    public void takeImage(View view) {

        //Create the Dialog
        dialogM = new Dialog(Form.this);
        dialogM.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            dialogM.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        Window window = dialogM.getWindow();
        window.setGravity(Gravity.BOTTOM);

        dialogM.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogM.setCancelable(false); //Optional
        dialogM.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
        dialogM.show();

        ImageView camera = dialogM.findViewById(R.id.cameraOp);
        ImageView gallery = dialogM.findViewById(R.id.galleryOp);
        ImageView files = dialogM.findViewById(R.id.filesOp);

        camera.setOnClickListener(v -> {
            dialogM.dismiss();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager())!= null){
                Uri imagePath = createCameraImage();
                if (imagePath != null){
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imagePath);
                    startActivityForResult(intent,CAMERA);
                    intentCameraUri = intent.getData();
                }
            }

        });

        gallery.setOnClickListener(v -> {
            dialogM.dismiss();
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivityForResult(Intent.createChooser(intent,"Title"),GALLERY);
            }
        });

        files.setOnClickListener(view1 -> {
            Toast.makeText(Form.this, "files", Toast.LENGTH_SHORT).show();
            dialogM.dismiss();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA && resultCode == Activity.RESULT_OK ){
            getImgPath();// it will return the Capture image path
            Toast.makeText(Form.this, "Picture Selected Camera", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == GALLERY && resultCode == Activity.RESULT_OK ){
            assert data != null;
            uri = data.getData();
//          imageDash.setImageURI(gUri);
            Toast.makeText(Form.this, "Picture Selected from Gallery", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitDemo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                saveImageToGallery(bitDemo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(Form.this, "Picture Not Selected ", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = VERSION_CODES.Q)
    private Uri createCameraImage(){
        ContentResolver resolver = getContentResolver();
        if (Build.VERSION.SDK_INT >= VERSION_CODES.Q){
            uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }else {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String imgName = String.valueOf(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME,imgName+".jpg");
        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/"+"Forget/");
        //        billImageEdit.setText(imageUri.toString());
        return resolver.insert(uri,contentValues);
    }

    public void getImgPath() {
        String[] largeFileProjection = { MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA };
        String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
        Cursor myCursor = this.managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                largeFileProjection, null, null, largeFileSort);
        String largeImagePath;
        try {
            myCursor.moveToFirst();
            largeImagePath = myCursor
                    .getString(myCursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
        } finally {
            myCursor.close();
        }
        billImageEdittext.setHint(largeImagePath);
    }

    private void saveImageToGallery(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        Form.this.sendBroadcast(new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri
                .parse("file://" + root)));
        File myDir = new File(root + "/Forget");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fName = "Image-" + n + ".jpg";
        File file = new File(myDir, fName);
        if (file.exists())
            file.delete();
            file.delete();
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            galleryImagePath = file.getPath();
            billImageEdittext.setHint(galleryImagePath);
            //Log.d("TAG",gPath);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

