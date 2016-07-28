package com.xdx.dllo.beautygoodsdemo.pictorial.pictorialdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import org.xml.sax.XMLReader;

import java.io.InputStream;

/**
 * Created by dllo on 16/7/28.
 */
public class HtmlTextView extends TextView {
    public static final String TAG = "HtmlTextView";
    public static final boolean DEBUG = false;

    public HtmlTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlTextView(Context context) {
        super(context);
    }

    /**
     * @param is
     * @return
     */
    static private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        return s.hasNext() ? s.next() : "";
    }

    /**
     * Parses String containing HTML to Android's Spannable format and displays
     * it in this TextView.
     *
     * @param html String containing HTML, for example: "<b>Hello world!</b>"
     */
    public void setHtmlFromString(String html, boolean useLocalDrawables) {
        Html.ImageGetter imgGetter;

        if (useLocalDrawables) {
            imgGetter = new LocalImageGetter(getContext());
        } else {
            imgGetter = new UrlImageGetter();
        }

        // this uses Android's Html class for basic parsing, and HtmlTagHandler
        setText(Html.fromHtml(html, imgGetter, new HtmlTagHandler()));

        // make links work
        setMovementMethod(LinkMovementMethod.getInstance());
//        text.setTextColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));

    }


    private class LocalImageGetter implements Html.ImageGetter {
        public LocalImageGetter(Context context) {
        }

        @Override
        public Drawable getDrawable(String s) {
            return null;
        }
    }

    private class HtmlTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean b, String s, Editable editable, XMLReader xmlReader) {

        }
    }
}
