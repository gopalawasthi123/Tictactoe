package com.example.gopalawasthi.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int size=3;
    public static final int Player_O=1;
    public static final int Player_x=2;
    public static final int No_player=-1;
    LinearLayout rootLayout;
    TTTButton board[][];
    int currentplayer;
    boolean gameover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout=findViewById(R.id.rootLayout);
        initgame();
    }

    private void initgame() {
        currentplayer=Player_O;
        gameover=false;
        setupBoard();
    }


    private void setupBoard() {
        rootLayout.removeAllViews();
        board=new TTTButton[size][size];
        for(int rows=0;rows<size;rows++){
         LinearLayout linearLayout=new LinearLayout(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
             linearLayout.setLayoutParams(params);

            for(int col=0;col<size;col++){
                TTTButton button=new TTTButton(this);
                LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(params1);
                button.setOnClickListener(this);
                linearLayout.addView(button);
                board[rows][col]=button;
            }
            rootLayout.addView(linearLayout);
        }

    }

    @Override
    public void onClick(View view) {
        TTTButton button=(TTTButton)view;
        if(!gameover){
        button.setPlayer(currentplayer);
        isgameover();
        Toggle();
        }

    }
    //check for rows by taking the assumption already true for rows
    private void isgameover() {

        for(int i=0;i<size;i++){
            boolean row=true;
            for(int j=0;j<size;j++){
                TTTButton currentplayer=board[i][j];
                TTTButton firstplayer=board[i][0];
                if(currentplayer.isEmpty() || currentplayer.getplayer()!=firstplayer.getplayer()){
                    row=false;
                    break;
                }
            }
            if(row){
               TTTButton firstplayer1=board[i][0];
               int player=firstplayer1.getplayer();
               ongameover(player);
                for(int k=0;k<size;k++){
                    board[i][k].setBackgroundResource(R.drawable.win);
                }
                return;
            }
        }
        //check for column by taking assumption already true for columns

        for(int i=0;i<size;i++){
            boolean col=true;

            for(int j=0;j<size;j++) {
                TTTButton currentplayer = board[j][i];
                TTTButton firstplayer = board[0][i];
                if (currentplayer.isEmpty() || currentplayer.getplayer() != firstplayer.getplayer()) {
                    col = false;
                    break;
                }
            }
            if(col){
                    TTTButton firstplayer=board[0][i];
                    int player=firstplayer.getplayer();
                            ongameover(player);
                for(int k=0;k<size;k++){
                    board[k][i].setBackgroundResource(R.drawable.win);
                }
                            return;
                }

        }

        //check for the first diagnol by taking the assumption of true

        boolean first_diag = true;
        for(int i=0;i<size;i++) {
            TTTButton currentplayer = board[i][i];
            TTTButton firstplayer = board[0][0];
            if (currentplayer.isEmpty() || currentplayer.getplayer() != firstplayer.getplayer()) {
                first_diag = false;
                break;
            }

        }
            if(first_diag){
                TTTButton firstplayer=board[0][0];
                int player=firstplayer.getplayer();
                ongameover(player);
                for(int k=0;k<size;k++){
                    board[k][k].setBackgroundResource(R.drawable.win);
                }
                return;
            }


        //check for the second diagnol by taking the assumption of true

        boolean sec_diag=true;
        for(int i=0;i<size;i++){

            TTTButton currentplayer=board[i][size-i-1];
            TTTButton firstplayer=board[0][size-1];
            if(currentplayer.isEmpty() || currentplayer.getplayer()!=firstplayer.getplayer()){
                sec_diag=false;
                break;
            }
        }
        if(sec_diag){
            TTTButton firstplayer=board[0][size-1];
            int player=firstplayer.getplayer();
            ongameover(player);
            for(int k=0;k<size;k++){
                board[k][size-k-1].setBackgroundResource(R.drawable.win);
            }
            return;
        }
        //is full

        boolean full=true;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j].isEmpty()){
                    full=false;
                    break;
                }
            }
        }
        if(full){
          ongameover(No_player);
        }

        }

    private void ongameover(int player) {
        if (player == Player_x) {
            Toast.makeText(this, "player_x win", Toast.LENGTH_LONG).show();
        } else if (player == Player_O) {
            Toast.makeText(this, "player_O win", Toast.LENGTH_LONG).show();
        } else if (player ==No_player) {
            Toast.makeText(this,"draw!try again",Toast.LENGTH_LONG).show();
        }
        gameover=true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setEnabled(false);
            }
        }
    }

    private void Toggle() {
        if (currentplayer == Player_O) {
            currentplayer = Player_x;
        } else {
            currentplayer = Player_O;
        }
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.reset){
            initgame();
        }else if(id==R.id.three){
        size=3;
        initgame();
        item.setChecked(true);
        }else if(id==R.id.four){
            size=4;
            initgame();
            item.setChecked(true);
        }else if(id==R.id.five){
            size=5;
            initgame();
            item.setChecked(true);
        }
        return true;
    }
}
