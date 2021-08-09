package self.github.flink.sample.operator.stateless;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roc
 * @date 2021/8/9
 * @description
 */
public class MyStatelessMapITest {

  @Test
  public void testMap() throws Exception {
    MyStatelessMap statelessMap = new MyStatelessMap();
    String out = statelessMap.map("world");
    Assert.assertEquals("hello world", out);
  }
}
