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


import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class NewsFeedActivity extends ListActivity {

    final static int REQUEST = 1;
    public static ArrayList<Post> values = new ArrayList<Post>();
    NewsFeedAdapter adapter;

    private class NewsFeedAdapter extends ArrayAdapter<Post> {
        private final Context context;
        private ArrayList<Post> values = null;

        public NewsFeedAdapter(Context context, ArrayList<Post> values) {
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
            TextView timeView = (TextView)rowView.findViewById(R.id.timestamp);

            // Adds Professors name to View
            professorView.setText(this.getItem(position).getProfessor());
            // Adds Content to View
            contentView.setText(this.getItem(position).getContent());

            // Formats date and adds it to view.
            SimpleDateFormat day = new SimpleDateFormat("EEEE");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
            String datetime = day.format(this.getItem(position).getTimestamp()) + " at " +
                    time.format(this.getItem(position).getTimestamp());

            timeView.setText(datetime);
            return rowView;
        }

        @Override
        public void add(Post object){
            if (object == null)
                throw new InvalidParameterException();
            values.add(object);
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
                    Bundle bundle = data.getExtras();
                    String prof = bundle.getString("professor");
                    String text = bundle.getString("text");
                    addPost(prof, text);
                }
                break;
            }
        }
    }

    //add data to the list of posts and update the adapter
    public void addPost(String professor, String text){
        Post post = new Post(professor, text);
        adapter.add(post);
        adapter.notifyDataSetChanged();

    }
}
