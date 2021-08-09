package self.github.flink.sample.operator.stateful;

import com.google.common.collect.Lists;
import java.util.Collections;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.operators.StreamFlatMap;
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
public class StatefulFlatMapITest {

  @Test
  public void testFlatMap() throws Exception {
    // construct
    StatefulFlatMap statefulFlatMap = new StatefulFlatMap();
    OneInputStreamOperatorTestHarness<String, String> testHarness =
        new KeyedOneInputStreamOperatorTestHarness<>(new StreamFlatMap<>(statefulFlatMap), x -> "1", Types.STRING);
    testHarness.open();

    // test first record
    testHarness.processElement("world", 10);
    ValueState<String> previousInput = statefulFlatMap.getRuntimeContext().getState(
        new ValueStateDescriptor<>("previousInput", Types.STRING));
    String stateValue = previousInput.value();
    Assert.assertEquals(Collections.singletonList(new StreamRecord<String>("hello world", 10)),
        testHarness.extractOutputStreamRecords());
    Assert.assertEquals("world", stateValue);

    // test second record
    testHarness.processElement("parallel", 20);
    Assert.assertEquals(
        Lists.newArrayList(new StreamRecord<>("hello world", 10), new StreamRecord<>("hello parallel world", 20)),
        testHarness.extractOutputStreamRecords());

    Assert.assertEquals("parallel", previousInput.value());
  }
}
