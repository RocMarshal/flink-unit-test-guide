package self.github.flink.sample.operator.stateless;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.apache.flink.api.common.functions.util.ListCollector;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class MyStatelessFlatMapITest {

  @Test
  public void testFlatMap() throws Exception {
    MyStatelessFlatMap statelessFlatMap = new MyStatelessFlatMap();
    List<String> out = new ArrayList<>();
    ListCollector<String> listCollector = new ListCollector<>(out);
    statelessFlatMap.flatMap("world", listCollector);
    Assert.assertEquals(Lists.newArrayList("hello world"), out);
  }
}
