package com.example.jano.chatbis;

import java.net.URI;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.EditText;
        import android.widget.TextView;

        import org.java_websocket.client.WebSocketClient;
        import org.java_websocket.handshake.ServerHandshake;
        import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

private WebSocketClient miWebSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://chatnodejs-jano22.c9users.io:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        miWebSocket = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                miWebSocket.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.messages);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        miWebSocket.connect()
}


    public void sendMessage(View view) {
        EditText editText = (EditText)findViewById(R.id.message);
        miWebSocket.send(editText.getText().toString());
        editText.setText("");
    }

}
