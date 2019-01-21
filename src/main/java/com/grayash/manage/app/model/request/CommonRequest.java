package com.grayash.manage.app.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonRequest implements Serializable {

   private AppData appData;
}
