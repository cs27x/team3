package com.yac.yic.mcnamara.yicprofessor;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;


public class NewsFeedActivity extends ListActivity {

    final static int REQUEST = 1;
    Post[] values;
    NewsFeedAdapter adapter;

    private class NewsFeedAdapter extends ArrayAdapter<Post> {
        private final Context context;
        private Post[] values = {};

        public NewsFeedAdapter(Context context, Post[] values) {
            super(context, R.layout.news_feed_list_item, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.news_feed_list_item, parent, false);
            TextView professorView = (TextView)rowView.findViewById(R.id.professor);
            TextView contentView = (TextView)rowView.findViewById(R.id.content);

            professorView.setText(this.getItem(position).getProfessor());
            contentView.setText(this.getItem(position).getContent());
            return rowView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_feed);

        adapter = new NewsFeedAdapter(this, values);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_new) {
            //start the text prompt activity with result callback
            Intent intent = new Intent(this, TextPrompt.class);
            startActivityForResult(intent, REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //callback result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    String prof = data.getStringExtra("professor");
                    String text = data.getStringExtra("text");
                    addPost(prof, text);
                }
                break;
            }
        }
    }

    //add data to the list of posts and update the adapter
    public void addPost(String professor, String text){
        Post post = new Post(professor, text);
        values[values.length] = post;
        adapter.notifyDataSetChanged();
    }
}
