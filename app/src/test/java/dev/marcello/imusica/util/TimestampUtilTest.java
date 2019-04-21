package dev.marcello.imusica.util;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Marcello
 * 2019
 */

public class TimestampUtilTest {

    private int[] created_utc = {
            1497903395, 1555705461, 1555725059, 1555665621, 1555686880,
            1555702115, 1555679786, 1555695502, 1555705538, 1555703133,
            1555698977, 1555603930, 1555657694, 1555672619, 1555679956,
            1555676786, 1555670386, 1555661100, 1555629139, 1555657774,
            1555636168, 1555641702, 1555640881, 1555606250, 1555644464, 1555621697
    };
    private String[] date;

    @Before
    public void setUp() {
        date = new String[created_utc.length];
        for (int i = 0 ; i < created_utc.length ; i++){
            date[i] = TimestampUtil.convertTime(created_utc[i]);
        }
    }

    @Test
    public void convertTime() {
        //All those timestamp have been converted on the website https://www.epochconverter.com/
        assertThat(date[0], is("19/06/2017"));
        assertThat(date[1], is("19/04/2019"));
        assertThat(date[2], is("20/04/2019"));
        assertThat(date[3], is("19/04/2019"));
        assertThat(date[4], is("19/04/2019"));
        assertThat(date[5], is("19/04/2019"));
        assertThat(date[6], is("19/04/2019"));
        assertThat(date[7], is("19/04/2019"));
        assertThat(date[8], is("19/04/2019"));
        assertThat(date[9], is("19/04/2019"));
        assertThat(date[10], is("19/04/2019"));
        assertThat(date[11], is("18/04/2019"));
        assertThat(date[12], is("19/04/2019"));
        assertThat(date[13], is("19/04/2019"));
        assertThat(date[14], is("19/04/2019"));
        assertThat(date[15], is("19/04/2019"));
        assertThat(date[16], is("19/04/2019"));
        assertThat(date[17], is("19/04/2019"));
        assertThat(date[18], is("18/04/2019"));
        assertThat(date[19], is("19/04/2019"));
        assertThat(date[20], is("19/04/2019"));
        assertThat(date[21], is("19/04/2019"));
        assertThat(date[22], is("19/04/2019"));
        assertThat(date[23], is("18/04/2019"));
        assertThat(date[24], is("19/04/2019"));
        assertThat(date[25], is("18/04/2019"));
    }

}