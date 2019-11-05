package com.upuphub.dew.community.general.api.bean.po;


import com.upuphub.dew.community.connection.common.JsonHelper;
import lombok.Data;

import java.util.List;

@Data
public class GarbageCategoriesPO {

  private long id;
  private String color;
  private String bgColor;
  private String img;
  private String name;
  private String content;
  private String description;
  private List<String> action;
  public void setAction(String action) {
    this.action = JsonHelper.valueOfList(action,String.class);
  }

}
