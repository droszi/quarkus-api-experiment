package com.droszi.api.services;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;

@ApplicationScoped
@AllArgsConstructor
public class JooqService {
  private final ConnectionService connectionService;

  public DSLContext getDbContext() {
    return new DefaultDSLContext(connectionService.getConnection(), SQLDialect.POSTGRES, null);
  }
}
