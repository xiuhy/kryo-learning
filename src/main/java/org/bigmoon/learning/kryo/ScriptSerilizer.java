package org.bigmoon.learning.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;


/**
 * @author bigmoon
 * @date 2021/9/2
 * @return
 * @see [相关类/方法]（可选）
 * @since
 */
public class ScriptSerilizer {

    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");

        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setReferences(true);
        kryo.addDefaultSerializer(NashornScriptEngine.class,new SelfSerilizer());

        Output output = new Output(1024,-1);
        kryo.writeClassAndObject(output,engine);
        System.out.println("第一次执行完毕");

        Input input = new Input(output.getBuffer(), 0, output.position());
        ScriptEngine scriptEngine=kryo.readObject(input,NashornScriptEngine.class);

        scriptEngine.eval("print('Hello World!');");
    }

    public static class SelfSerilizer extends Serializer<ScriptEngine>{
        @Override
        public void write(Kryo kryo, Output output, ScriptEngine scriptEngine) {
//            OutputStream outputStream=new ByteArrayOutputStream();
//            kryo.writeObject(output,scriptEngine,this);
        }

        @Override
        public ScriptEngine read(Kryo kryo, Input input, Class<? extends ScriptEngine> aClass) {
//            return kryo.readObject(input,aClass,this);
            return new NashornScriptEngineFactory().getScriptEngine();
        }
    }



}
