package com.upuphub.dew.community.general.api.bean.po;


import cc.itsc.utils.common.JsonHelper;

import java.util.List;

public class GarbageCategoriesPO {

  private long id;
  private String color;
  private String bgColor;
  private String img;
  private String name;
  private String content;
  private String description;
  private List<String> action;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public String getBgColor() {
    return bgColor;
  }

  public void setBgColor(String bgColor) {
    this.bgColor = bgColor;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public List<String> getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = JsonHelper.valueOfList(action,String.class);
  }

}
