package com.kiran.friendbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {
    private UserModel userModel;
    private EditText etname;
    private Button btnupdate, btndelete;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);

        etname = (EditText) findViewById(R.id.etname);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        etname.setText(userModel.getName());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().length()>0){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            UpdateDeleteActivity.this);
                    alertDialog.setTitle("Confirm Update...");
                    alertDialog.setMessage("Are you sure you want update to "+etname.getText().toString());
                    alertDialog.setIcon(R.drawable.update);
                    alertDialog.setPositiveButton("Update",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int i) {
                                    // Write your code here to execute after dialog
                                    databaseHelper.updateUser(userModel.getId(),etname.getText().toString());
                                    Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UpdateDeleteActivity.this,FriendActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                }
                            });
// Setting Negative "NO" Btn
                    alertDialog.setNegativeButton("cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(UpdateDeleteActivity.this, "Friend's Name is Empty !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                        UpdateDeleteActivity.this);
                alertDialog.setTitle("Confirm Delete ...");
                alertDialog.setMessage("Are you sure you want Delete "+etname.getText().toString());
                alertDialog.setIcon(R.drawable.delete);
                alertDialog.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                // Write your code here to execute after dialog
                                databaseHelper.deleteUSer(userModel.getId());
                                Toast.makeText(UpdateDeleteActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateDeleteActivity.this,FriendActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        });
// Setting Negative "NO" Btn
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });

    }
}