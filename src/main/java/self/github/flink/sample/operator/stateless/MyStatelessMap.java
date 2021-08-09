package self.github.flink.sample.operator.stateless;

import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author Roc
 * @date 2021/8/9
 * @description stateless operator
 */
public class MyStatelessMap implements MapFunction<String, String> {

  @Override
  public String map(String s) throws Exception {
    return "hello " + s;
  }
}
