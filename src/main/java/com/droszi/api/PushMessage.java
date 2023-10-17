package com.droszi.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PushMessage {
  int userId;
  String message;
  String title;
}
