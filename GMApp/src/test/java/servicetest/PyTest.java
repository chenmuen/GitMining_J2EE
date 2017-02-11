package servicetest;

import base.BaseTest;
import org.junit.Test;
import org.python.util.PythonInterpreter;

/**
 * Created by raychen on 16/6/3.
 */
public class PyTest extends BaseTest {

    @Test
    public void testPy() throws Exception {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("");
    }
}
