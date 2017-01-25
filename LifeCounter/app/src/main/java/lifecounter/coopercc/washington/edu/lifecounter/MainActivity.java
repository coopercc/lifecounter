package lifecounter.coopercc.washington.edu.lifecounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.util.Log;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
    private int players = 4; //Default value
    private int[] playerHps = {20, 20, 20, 20}; //used to keep track of the healths so I don't have to get the text each time
    private int[][] playerBtns = {
            {R.id.p1hp, R.id.p1min5, R.id.p1min1, R.id.p1plus1, R.id.p1plus5},
            {R.id.p2hp, R.id.p2min5, R.id.p2min1, R.id.p2plus1, R.id.p2plus5},
            {R.id.p3hp, R.id.p3min5, R.id.p3min1, R.id.p3plus1, R.id.p3plus5},
            {R.id.p4hp, R.id.p4min5, R.id.p4min1, R.id.p4plus1, R.id.p4plus5}
    }; //Holds all the buttons for the different players and their health textview reference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calls the onclick for each button and sends the health TextView so the health can be changed
        for(int i = 0; i < playerBtns.length; i++) {
            final TextView health = (TextView) findViewById(playerBtns[i][0]);
            for (int j = 1; j < playerBtns[i].length; j++) {
                Log.i(TAG, "i= " + i + " j= " + j);
                Button temp = (Button) findViewById(playerBtns[i][j]);
                setBtnListener(i, health, temp);
            }
        }

    }

    //Used to create button listeners for all 16 buttons, calls healthChange to do the opertion as specified by button
    private void setBtnListener(final int player, final TextView health, Button btn) {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int op = Integer.parseInt(v.getTag().toString());
                Log.i(TAG, "Player " + player + " health change: " + op + " currHealth=" + health.getText());
                makeHealthChange(player, health, op);
            }
        });
    }


    //Changes the health value for the player that the button was pressed for and saves that information
    //If the player drops at or below 0 health, it changes their health to 0 and says they lose
    private void makeHealthChange(final int player, final TextView health, int op) {
        int newHealth = playerHps[player] + op;
        Log.i(TAG, "new Health = " + newHealth);
        if (newHealth <= 0) {
            newHealth = 0;
            TextView loser = (TextView) findViewById(R.id.loser);
            loser.setText("Player " + (player+1) + " LOSES!");
        }
        playerHps[player] = newHealth;
        health.setText(newHealth + "");

    }
    
}
