package com.example.gopalawasthi.tictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;

/**
 * Created by Gopal Awasthi on 28-01-2018.
 */


 public class TTTButton extends AppCompatButton {

    private  int player=MainActivity.No_player;
   //constructor
    public TTTButton(Context context){
        super(context);

    }
 public void setPlayer(int player){
       this.player=player;
       if(player==MainActivity.Player_x){
        setText("X");
        setTextSize(40);
       }else if(player==MainActivity.Player_O){
           setText("O");
           setTextSize(40);
       }
       setEnabled(false);

    }
    public boolean isEmpty(){
    return player==MainActivity.No_player;
    }


    public int getplayer() {
    return player;
    }

}
