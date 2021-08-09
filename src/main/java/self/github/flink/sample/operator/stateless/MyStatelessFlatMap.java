package self.github.flink.sample.operator.stateless;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class MyStatelessFlatMap implements FlatMapFunction<String, String> {

  @Override
  public void flatMap(String s, Collector<String> collector) throws Exception {
    String out = "hello " + s;
    collector.collect(out);
  }
}
