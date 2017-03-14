package com.example.jano.chatbis;

/**
 * Created by Jano on 14/03/2017.
 */

import java.net.URI;
import java.util.Random;

import android.webkit.WebView;

public class WebSocketFactory {



    /** The app view. */
    WebView appView;

    /**
     * Instantiates a new web socket factory.
     *
     * @param appView
     *            the app view
     */
    public WebSocketFactory(WebView appView) {
        this.appView = appView;
    }

    public WebSocket getInstance(String url) {
        // use Draft75 by default
        return getInstance(url, WebSocket.Draft.DRAFT75);
    }

    public WebSocket getInstance(String url, WebSocket.Draft draft) {
        WebSocket socket = null;
        Thread th = null;
        try {
            socket = new WebSocket(appView, new URI(url), draft, getRandonUniqueId());
            th = socket.connect();
            return socket;
        } catch (Exception e) {
            //Log.v("websocket", e.toString());
            if(th != null) {
                th.interrupt();
            }
        }
        return null;
    }

    /**
     * Generates random unique ids for WebSocket instances
     *
     * @return String
     */
    private String getRandonUniqueId() {
        return "WEBSOCKET." + new Random().nextInt(100);
    }
}
