package wia2007.com.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etPages;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.ET_BookTitleUpdate);
        etAuthor = findViewById(R.id.ET_BookAuthorUpdate);
        etPages = findViewById(R.id.ET_BookPagesUpdate);
        addBtn = findViewById(R.id.B_Update);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(etTitle.getText().toString().trim(), etAuthor.getText().toString().trim(), Integer.parseInt(etPages.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}