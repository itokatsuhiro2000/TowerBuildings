package com.jp.rami.towerbuildings.game.line;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import static mockit.Deencapsulation.getField;
import static mockit.Deencapsulation.invoke;
import static mockit.Deencapsulation.setField;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by PCUser on 2015/03/15.<br>
 * LineTaskのテストクラス
 */
@RunWith(JMockit.class)
public class LineTaskTest {

    private LineTask target;
    private Line[] lines = new Line[3];

    @Before
    public void testBefore() throws Exception {
        new NonStrictExpectations(LineTask.class) {{
            new LineTask(null);
        }};
        target = new LineTask(null);
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(i);
        }
        setField(target, "mLines", lines);
    }

    @Test
    public void testGetNextLine() throws Exception {
        Line result = invoke(target, "getNextLine", 0);
        assertThat((int) getField(result, "num"), is(2));

        result = invoke(target, "getNextLine", 1);
        assertThat((int) getField(result, "num"), is(3));

        result = invoke(target, "getNextLine", 2);
        assertThat((int) getField(result, "num"), is(1));
    }

    @Test
    public void testGetBeforeLine() throws Exception {
        Line result = invoke(target, "getBeforeLine", 0);
        assertThat((int) getField(result, "num"), is(3));

        result = invoke(target, "getBeforeLine", 1);
        assertThat((int) getField(result, "num"), is(1));

        result = invoke(target, "getBeforeLine", 2);
        assertThat((int) getField(result, "num"), is(2));
    }
}
