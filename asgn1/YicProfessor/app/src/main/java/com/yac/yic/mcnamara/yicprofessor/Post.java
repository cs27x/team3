package com.yac.yic.mcnamara.yicprofessor;

import java.security.InvalidParameterException;

/**
 * Created by walton on 9/22/14.
 */
public class Post {
    private String content;
    private int score;

    public Post(String content){
        if (!content.equals("")) {
            this.content = content;
            this.score = 0;
        }
        else{
            throw new InvalidParameterException();
        }
    }

    public String getContent(){
        return content;
    }


}
