package com.example.jano.chatbis;

import java.net.URI;

import android.os.Build;
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

   //Variable necesaria para la conexión mediante websocket
private WebSocketClient miWebSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectarse();

    }


    /*
    *Con este método se establecerá la conexión entre el dispositivo móvil y el servidor
     */
    private void conectarse() {
        URI uri;
        try {
            //Servidor al que vamos a conectarnos
            uri = new URI("ws://chatnodejs-jano22.c9users.io:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        //Inicializamos la variable WebSocket
        miWebSocket = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                miWebSocket.send("HOLILLAAA " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    /*
                    *Este método hará que los mensajes se vayan mostrando por pantalla
                     */
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.mlChat);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Conexión cerrada " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error Fatal!!! CORREEEE " + e.getMessage());
            }
        };
        miWebSocket.connect();
}



    /*
    *Enviará el texto recogido en el EditText y lo enviará a través de la conexión WebSocket
     */
    public void enviaMensaje(View view) {
        EditText editText = (EditText)findViewById(R.id.etMensaje);
        miWebSocket.send(editText.getText().toString());
        editText.setText("");
    }

}
