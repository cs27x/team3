package com.yac.yic.mcnamara.yicprofessor;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Created by walton on 9/22/14.
 */
public class Post {
    private String content;
    private String professor;
    private Date timestamp;

    public Post(String content) {
        this("Dr. White", content);
    }

    public Post(String professor, String content) {
        if (!content.equals("") && !professor.equals(""))  {
            this.content = content;
            this.professor = professor;
            this.timestamp = new Date();
        }
        else{
            throw new InvalidParameterException();
        }
    }


    public String getContent(){
        return content;
    }
    public String getProfessor() { return professor; }
    public Date getTimestamp() { return timestamp; }



}
