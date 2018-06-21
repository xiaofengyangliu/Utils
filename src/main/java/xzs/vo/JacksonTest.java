package xzs.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JacksonTest {
    private String color;

    @JsonProperty(value = "测试", access = JsonProperty.Access.READ_ONLY)
    public String getColor() {
      return color;
    };

    @JsonProperty(value = "color", access = JsonProperty.Access.WRITE_ONLY)
    public void setColor(String color) {
      this.color = color;
    }
}
