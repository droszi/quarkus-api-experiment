package com.droszi.api.model;

public record PushMessageModel(
  int userId,
  String message,
  String title
) {}
