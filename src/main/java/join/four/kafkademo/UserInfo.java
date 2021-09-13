package join.four.kafkademo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Roc
 * @date 2021/9/13
 * @description
 */
@Data
public class UserInfo implements Serializable {

  private String userName;
  private Integer cityId;
  private Long ts;
}
