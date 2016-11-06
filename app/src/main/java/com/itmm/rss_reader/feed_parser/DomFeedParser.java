package com.itmm.rss_reader.feed_parser;

import android.os.Environment;

import com.itmm.rss_reader.post_message.PostMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Дмитрий on 11/5/2016.
 */

public class DomFeedParser extends BaseFeedParser {

    final String outputXmlFile;

    public DomFeedParser(String feedUrl, String xmlFile) {
        super(feedUrl);
        outputXmlFile = xmlFile;
    }

    public List<PostMessage> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<PostMessage> messages = new ArrayList<PostMessage>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName(ITEM);

            // Save parsed xml from InputStream in the appropriate xml file
            Result output = new StreamResult(new File(Environment.getExternalStorageDirectory(), outputXmlFile));
            Source input = new DOMSource(dom);
            transformer.transform(input, output);

            for (int i = 0; i < items.getLength(); i++){
                PostMessage message = new PostMessage();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();

                for (int j = 0;j < properties.getLength(); j++){
                    Node property = properties.item(j);
                    String name = property.getNodeName();

                    if (name.equalsIgnoreCase(TITLE)){
                        message.setTitle(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(LINK)){
                        message.setLink(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(DESCRIPTION)){
                        StringBuilder text = new StringBuilder();
                        NodeList chars = property.getChildNodes();

                        for (int k=0;k<chars.getLength();k++){
                            text.append(chars.item(k).getNodeValue());
                        }
                        message.setDescription(text.toString());
                    } else if (name.equalsIgnoreCase(PUB_DATE)){
                        message.setDate(property.getFirstChild().getNodeValue());
                    }
                }
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return messages;
    }
}
