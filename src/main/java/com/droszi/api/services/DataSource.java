package com.droszi.api.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.microprofile.config.ConfigProvider;

@Singleton
public class DataSource {
  private static HikariDataSource dataSource;

  static {
    HikariConfig config = new HikariConfig();

    config.setJdbcUrl(ConfigProvider.getConfig().getValue("hikari.jdbcUrl", String.class));
    config.setUsername(ConfigProvider.getConfig().getValue("hikari.user", String.class));
    config.setPassword(ConfigProvider.getConfig().getValue("hikari.password", String.class));
    config.addDataSourceProperty("cachePrepStmts","true" );
    config.addDataSourceProperty("prepStmtCacheSize" , "250" );
    config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048" );

    dataSource = new HikariDataSource(config);
  }

  private DataSource() {}

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
