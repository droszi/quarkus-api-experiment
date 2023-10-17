package com.droszi.api.services;

import jakarta.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@ApplicationScoped
@AllArgsConstructor
public class ConnectionService {
  private final DataSource dataSource;

  @SneakyThrows
  public Connection getConnection() {
    return dataSource.getConnection();
  }
}
