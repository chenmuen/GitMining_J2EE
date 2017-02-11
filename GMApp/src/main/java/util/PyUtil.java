package util;

import org.python.core.*;
import org.python.util.PythonInterpreter;
import util.enums.AnalyType;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by raychen on 16/6/3.
 */
public class PyUtil {

    public static Anova anova(List<List<Integer>> param){
        PyFunction anovaFunc = getFunction("anova", "analysis");
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        List<Integer> l3 = new LinkedList<>();
        for (int i = 4; i < 10; i++) {
            l1.add(i*4+3);
            l2.add(i*i+6);
            l3.add(i*i*i*i);
        }
        List<List<Integer>> pa = new LinkedList<>();
        pa.add(l1);
        pa.add(l2);
        pa.add(l3);
        PyList pylist = new PyList(pa);
        PyObject ret = anovaFunc.__call__(pylist);
        List list = (List) ret.__tojava__(List.class);
        System.out.println((double)list.get(0));
        return new Anova(0,0);
    }

    private static PyFunction getFunction(String funcName, String fileName){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/resources/python/"+fileName+".py");
        PyFunction func = interpreter.get(funcName, PyFunction.class);
        return func;
    }

    public static void main(String[] args) {
        anova(null);
    }
}
