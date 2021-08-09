package self.github.flink.sample.operator.timedprocess;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.shaded.curator4.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.operators.KeyedProcessOperator;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;
import org.apache.flink.streaming.util.KeyedOneInputStreamOperatorTestHarness;
import org.apache.flink.streaming.util.OneInputStreamOperatorTestHarness;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class MyProcessFunctionITest {

  @Test
  public void testProcessElement() throws Exception {
    MyProcessFunction myProcessFunction = new MyProcessFunction();
    OneInputStreamOperatorTestHarness<String, String> testHarness =
        new KeyedOneInputStreamOperatorTestHarness<>(
            new KeyedProcessOperator<>(myProcessFunction), x -> "1", Types.STRING);

    // Function time is initialized to 0
    testHarness.open();
    testHarness.processElement("world", 10);

    Assert.assertEquals(Lists.newArrayList(new StreamRecord<>("hello world", 10)),
        testHarness.extractOutputStreamRecords());
  }

  @Test
  public void testOnTimer() throws Exception {
    MyProcessFunction myProcessFunction = new MyProcessFunction();
    OneInputStreamOperatorTestHarness<String, String> testHarness =
        new KeyedOneInputStreamOperatorTestHarness<>(new KeyedProcessOperator<>(myProcessFunction), x -> "x",
            Types.STRING);

    testHarness.open();
    testHarness.processElement("world", 10);
    Assert.assertEquals(1, testHarness.numProcessingTimeTimers());

    // Function time is set to 50
    testHarness.setProcessingTime(50);
    Assert.assertEquals(
        Lists.newArrayList(
            new StreamRecord<>("hello world", 10),
            new StreamRecord<>("Timer triggered at timestamp 50")),
        testHarness.extractOutputStreamRecords()
    );
  }
}
