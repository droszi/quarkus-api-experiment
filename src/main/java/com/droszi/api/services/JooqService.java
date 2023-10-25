package com.droszi.api.services;

import jakarta.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;

@ApplicationScoped
@AllArgsConstructor
public class JooqService {
  private final DataSource dataSource;

  public DSLContext getDbContext() {
    return new DefaultDSLContext(
        dataSource,
        SQLDialect.POSTGRES,
        null
    );
  }
}
