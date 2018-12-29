package com.tr4n.superstar2018;

/**
 * Created by MyPC on 21/01/2018.
 */

public class Box extends  Settings{
    public int ImageViewId = 0;
    public int Information = 0; // 0 : Empty ; 1 : Character ; 2 : Food ;
    public int Value = 0 ;


    public Box(int Information){
        this.Information = Information;
        this.Value = Information == 2 ? 1 : 0;
        if(Information == 0){
            this.ImageViewId = 0;
        }else if(Information == 1){
            this.ImageViewId = ID_OF_CHARACTER[ID_OF_CHARACTER[NUMBER_OF_CHARACTERS]];
        }
    }


}
