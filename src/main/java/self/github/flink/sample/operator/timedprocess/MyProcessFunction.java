package self.github.flink.sample.operator.timedprocess;

import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class MyProcessFunction extends KeyedProcessFunction<String, String, String> {

  @Override
  public void processElement(String in, KeyedProcessFunction<String, String, String>.Context context,
      Collector<String> collector) throws Exception {
    context.timerService().registerProcessingTimeTimer(50);
    String out = "hello " + in;
    collector.collect(out);
  }

  @Override
  public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) {
    out.collect(String.format("Timer triggered at timestamp %d", timestamp));
  }
}
