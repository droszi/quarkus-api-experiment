package com.droszi.api.repository;

import com.droszi.api.services.JooqService;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jooq.DSLContext;

@AllArgsConstructor
@NoArgsConstructor
abstract public class AbstractDBRepository {
  @Inject
  JooqService jooqService;

  protected DSLContext getQueryBuilder()
  {
    return jooqService.getDbContext();
  }
}
