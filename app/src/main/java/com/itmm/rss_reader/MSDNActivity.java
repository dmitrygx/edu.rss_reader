package com.itmm.rss_reader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itmm.rss_reader.feed_parser.DomFeedParser;
import com.itmm.rss_reader.feed_parser.RetrieveFeedTask;
import com.itmm.rss_reader.post_message.PostMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 11/5/2016.
 */

public class MSDNActivity extends AppCompatActivity implements RetrieveFeedTask.AsyncResponse {

    private final String RSS_MSDN = "http://blogs.msdn.com/b/rudevnews/rss.aspx";
    private final String FILE_MSDN = "Lab5Msdn.xml";

    DomFeedParser feedParser;
    List<PostMessage> listOfFeeds;
    ListView listView;
    RetrieveFeedTask retrieveAsyncTask;

    PostMessage selectedFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politics);
        feedParser = new DomFeedParser(RSS_MSDN, FILE_MSDN);
        listOfFeeds = new ArrayList<PostMessage>();
        retrieveAsyncTask = new RetrieveFeedTask();
        retrieveAsyncTask.setDelegate(this);

        listView = (ListView) findViewById(R.id.listView1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFeed = listOfFeeds.get(position);
            }
        });

        retrieveAsyncTask.execute(feedParser);
    }

    public void onClickRefresh(View view) {
        retrieveAsyncTask = new RetrieveFeedTask();

        retrieveAsyncTask.setDelegate(this);
        retrieveAsyncTask.execute(feedParser);
    }

    @Override
    public void processFinish(List<PostMessage> list) {
        listOfFeeds = list;

        if (listOfFeeds != null) {
            String[] total = new String[listOfFeeds.size()];

            int index = 0;

            for (PostMessage node : listOfFeeds) {
                total[index++] = node.getTitle() + " | " + node.getDate();
            }

            final ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, total);
            listView.setAdapter(adapter);
        }
    }

    public void onClickGoToLink(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedFeed.getLink().toString()));
        startActivity(browserIntent);
    }
}
