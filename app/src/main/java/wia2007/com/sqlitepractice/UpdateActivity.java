package wia2007.com.sqlitepractice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText etTitle, etAuthor, etPages;
    Button updateBtn, deleteBtn;
    Book book;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etTitle = findViewById(R.id.ET_BookTitleUpdate);
        etAuthor = findViewById(R.id.ET_BookAuthorUpdate);
        etPages = findViewById(R.id.ET_BookPagesUpdate);
        updateBtn = findViewById(R.id.B_Update);
        deleteBtn = findViewById(R.id.B_Delete);
        myDB = new MyDatabaseHelper(UpdateActivity.this);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(book.getTitle());
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.updateData(
                        book.getId(),
                        etTitle.getText().toString().trim(),
                        etAuthor.getText().toString().trim(),
                        Integer.parseInt(etPages.getText().toString().trim())
                );

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("Book")) {
            book = (Book) getIntent().getSerializableExtra("Book");
            etTitle.setText(book.getTitle());
            etAuthor.setText(book.getAuthor());
            etPages.setText(String.valueOf(book.getPages()));
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + book.getTitle() + " ?");
        builder.setMessage("Are you sure want to delete " + book.getTitle() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDB.deleteData(book.getId());

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}