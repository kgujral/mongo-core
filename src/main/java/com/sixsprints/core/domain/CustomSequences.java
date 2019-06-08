
package com.sixsprints.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "customSequences")
public class CustomSequences {

  @Id
  private String id;

  private int seq;

}
