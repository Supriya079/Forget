package xyz.clutr.forget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.List;

import xyz.clutr.forget.Room.BillEntity;
import xyz.clutr.forget.Room.DataDAO;
import xyz.clutr.forget.Room.DocumentEntity;
import xyz.clutr.forget.Room.ForgetDB;

public class Documents extends AppCompatActivity {

    RecyclerView recyclerViewDocuments;
    View viewBackDocuments;
    FloatingActionButton floatingActionButton;
    DataDAO billDAO;
    ImageView empty_imageview;
    TextView no_data;
    RecyclerAdapter recyclerAdapter;
    Bitmap bmImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        //view binding with id's
        floatingActionButton = findViewById(R.id.fabDocuments);
        viewBackDocuments = findViewById(R.id.viewBackDocuments);
        recyclerViewDocuments = findViewById(R.id.documentsRecyclerview);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        billDAO = ForgetDB.getBillDBInstance(getApplicationContext()).dataDAO();
        recyclerAdapter = new RecyclerAdapter(billDAO.getAllDocuments());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        if (recyclerAdapter.getItemCount() !=0 ){
            recyclerViewDocuments.setLayoutManager(gridLayoutManager);
            recyclerViewDocuments.setAdapter(recyclerAdapter);
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }else {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Form.class);
                startActivity(i);
            }
        });

        viewBackDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    private class RecyclerAdapter extends RecyclerView.Adapter<Documents.RecyclerAdapter.ViewHolder>{

        List<DocumentEntity> data;

        public RecyclerAdapter(List<DocumentEntity> documentEntities) {
            this.data = documentEntities;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_design,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            DocumentEntity documentEntity = data.get(position);
            holder.obj_name.setText(documentEntity.getObject_Name());
            holder.location.setText(documentEntity.getLocation());
            File roomReturnedPath = new File(documentEntity.getDocumentPath());
            if (roomReturnedPath.getName().endsWith(".pdf"))
            {
                bmImg  = AccessStorage.pdfToBitmap(roomReturnedPath);
            }
            else if (roomReturnedPath.getName().endsWith(".jpg")){
                bmImg = BitmapFactory.decodeFile(documentEntity.getDocumentPath());
            }
            holder.imageViewDocument.setImageBitmap(bmImg);

            holder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("name", documentEntity.getObject_Name());
                    intent.putExtra("location", documentEntity.getLocation());
                    intent.putExtra("image", documentEntity.getDocumentPath());
                    intent.putExtra("description",documentEntity.getDescription());
                    intent.putExtra("dob",documentEntity.getDate().toString());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            LinearLayout recyclerLayout;
            ImageView imageViewDocument;
            TextView  obj_name, location;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                location = itemView.findViewById(R.id.item_location);
                obj_name = itemView.findViewById(R.id.item_name);
                imageViewDocument = itemView.findViewById(R.id.grid_image);
                recyclerLayout = itemView.findViewById(R.id.recyclerlayout);
            }
        }

    }

}