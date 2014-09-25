package com.yac.yic.mcnamara.yicprofessor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class TextPrompt extends Activity {
    EditText newProfessor;
    EditText newText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_prompt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.text_prompt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //callback to post to the list.
    public void onPost(View view) {
        newProfessor = (EditText)findViewById(R.id.professor);
        newText = (EditText)findViewById(R.id.text);
        // Need to add alert to fill in fields properly
        if(!newProfessor.getText().toString().equals("") &&
                !newText.getText().toString().equals("")) {

            Intent resultIntent = new Intent();
            resultIntent.putExtra("professor", newProfessor.getText().toString());
            resultIntent.putExtra("text", newText.getText().toString());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
