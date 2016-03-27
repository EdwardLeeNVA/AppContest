package com.pirate.edward.typingscreeappproject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends Activity {

    EditText bottomBar;
    TextView typedView;
    Button sendButton;
    ScrollView mainView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //edit text at bottom with "Send" button next to it
        //text view updated on press of send button from top to bottom basically the whole screen
            //could be including a scroll view instead plus a settings menu at the top
        setContentView(R.layout.activity_main);
        //to be used in the assistance of dynamic programming
        mainView = (ScrollView) findViewById(R.id.mainScroll);
        bottomBar = (EditText) findViewById(R.id.bottomTextBar);   //needs to have the right side dynamically set and have a good  text size found
        typedView = (TextView) findViewById(R.id.typedView);       //if possible is scheduled to be replaced with scroll view
        sendButton  = (Button) findViewById(R.id.sent);              //dynamically sized to some portion of the screens width
        linearLayout = (LinearLayout) findViewById(R.id.insideScroll);
//        mainView.setBackgroundColor(Color.GREEN);
//        linearLayout.setBackgroundColor(Color.BLUE);
            //bring in the height of the screen then subtract the height of the bottom bar
                //then set that to the mainView height
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int scrollHeight = (size.y-bottomBar.getHeight());//doesn't like the lack of height can be fixed when size of the bar is set more dynamically until then it will be 10dp or so
        Log.w("System", "Theoretical scroll height" + scrollHeight);
        System.out.print("Theoretical scroll height" + scrollHeight);
//        mainView.setMinimumHeight(scrollHeight);
        mainView.setBottom(bottomBar.getTop());
        Log.w("System", "Actual Height" + mainView.getHeight());
        linearLayout.setMinimumHeight(mainView.getMinimumHeight());
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
    public void setDisplay(View v){
        sendMessage(getMessage());
    }
    public String getMessage(){
        bottomBar = (EditText) findViewById(R.id.bottomTextBar);
        String message = bottomBar.getText().toString();
        return message;
    }
    public void sendMessage(String s){
        typedView = (TextView) findViewById(R.id.typedView);
        bottomBar = (EditText) findViewById(R.id.bottomTextBar);
        linearLayout = (LinearLayout) findViewById(R.id.insideScroll);
        mainView = (ScrollView) findViewById(R.id.mainScroll);

        ///makes a new view for adding onto the screen
        TextView newViews = new TextView(this);
        //adds the message from get message ass teh textviews text and sets it to the standard size
        newViews.setText(s);
        newViews.setTextSize(16);

        //adds the new text view to the linear view so it is viewable
        linearLayout.addView(newViews);
        //scroll to the bottom of the view
        mainView.fullScroll(View.FOCUS_DOWN);
        //clears the edit text
        bottomBar.setText("");
    }

}
