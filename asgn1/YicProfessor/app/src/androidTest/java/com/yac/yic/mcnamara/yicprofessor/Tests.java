package com.yac.yic.mcnamara.yicprofessor;

import android.test.AndroidTestCase;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Tests extends AndroidTestCase {

    private Post myPost = new Post("professor", "content");

    public void testGetContent() throws Exception {
        assertEquals(myPost.getContent(), "content");
    }

    public void testGetProfessor() throws Exception {
        assertEquals(myPost.getProfessor(), "professor");
    }

    public void testGetTimestamp() throws Exception{
        Date now = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        assertTrue(fmt.format(myPost.getTimestamp()).equals(fmt.format(now)));
    }

}
