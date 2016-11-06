package com.itmm.rss_reader.feed_parser;

import android.os.AsyncTask;
import com.itmm.rss_reader.post_message.PostMessage;
import java.util.List;


/**
 * Created by Дмитрий on 11/5/2016.
 */

public class RetrieveFeedTask extends AsyncTask<DomFeedParser, Void, List<PostMessage>> {

    private Exception exception;

    DomFeedParser parser;

    public void setDelegate(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    AsyncResponse delegate = null;

    protected List<PostMessage> doInBackground(DomFeedParser... feedParser) {
        try {
            parser = feedParser[0];
            return parser.parse();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(List<PostMessage> listOfFeeds) {
        delegate.processFinish(listOfFeeds);
    }

    public interface AsyncResponse {
        void processFinish(List<PostMessage> list);
    }
}
