package self.github.flink.sample.operator.stateful;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class StatefulFlatMap extends RichFlatMapFunction<String, String> {

  ValueState<String> previousInput;

  @Override
  public void open(Configuration parameters) {
    previousInput = getRuntimeContext().getState(new ValueStateDescriptor<String>("previousInput", Types.STRING));
  }

  @Override
  public void flatMap(String s, Collector<String> collector) throws Exception {
    String out = "hello " + s;
    if (previousInput.value() != null) {
      out = out + " " + previousInput.value();
    }
    previousInput.update(s);
    collector.collect(out);
  }
}
