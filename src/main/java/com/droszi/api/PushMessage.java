package com.droszi.api;

public record PushMessage(
  int userId,
  String message,
  String title
) {}
