package com.jocabujat.applestop10freeapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseApplication {
    private static final String TAG = "ParseApplication";
    private ArrayList<FeedEntry> applications;

    ParseApplication() {
        this.applications = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: starting tag for " + tagName);
                        if ("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: end tag for " + tagName);
                        if (inEntry) {
                            if ("entry".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if ("artist".equalsIgnoreCase(tagName)) {
                                currentRecord.setArtist(textValue);
                            } else if ("releaseDate".equalsIgnoreCase(tagName)) {
                                currentRecord.setReleaseDate(textValue);
                            } else if ("summary".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);
                            } else if ("image".equalsIgnoreCase(tagName)) {
                                currentRecord.setImageURL(textValue);
                            }
                        }
                        break;

                    default:
                        // nothing else to do
                }
                eventType = xpp.next();
            }
            // display feed entry details
            for (FeedEntry entry : applications) {
                Log.d(TAG, "*****************************");
                Log.d(TAG, entry.toString());
            }

        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}
