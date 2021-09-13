package join.four.kafkademo;

/**
 * @author Roc
 * @date 2021/9/13
 * @description
 */

import java.io.Serializable;
import lombok.Data;

@Data
public class CityInfo implements Serializable {

  private Integer cityId;
  private String cityName;
  private Long ts;
}
