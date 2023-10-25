package com.droszi.api.services;

import static com.droszi.api.services.DataSource.*;

import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;

@ApplicationScoped
public class JooqService {
  public DSLContext getDbContext() {
    try {
      return new DefaultDSLContext(
          getConnection(),
          SQLDialect.POSTGRES,
          null
      );
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
