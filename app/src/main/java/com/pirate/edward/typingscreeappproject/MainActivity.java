package com.pirate.edward.typingscreeappproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText bottomBar;
    TextView typedView;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //edit text at bottom with "Send" button next to it
        //text view updated on press of send button from top to bottom basically the whole screen
            //could be including a scroll view instead plus a settings menu at the top

        //to be used in the assistance of dynamic programming
        bottomBar = (EditText) findViewById(R.id.bottomTextBar);   //needs to have the right side dynamically set and have a good  text size found
        typedView = (TextView) findViewById(R.id.typedView);       //if possible is scheduled to be replaced with scroll view
        sendButton  = (Button) findViewById(R.id.sent);              //dynamically sized to some portion of the screens width

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //temporary method used to send data from the edit text to the server then update the scroll view
    public void sendText(View v){
        typedView = (TextView) findViewById(R.id.typedView);
        bottomBar = (EditText) findViewById(R.id.bottomTextBar);
        typedView.setText(bottomBar.getText().toString());
        bottomBar.setText("");
        System.out.println("Button Works");
    }

}
