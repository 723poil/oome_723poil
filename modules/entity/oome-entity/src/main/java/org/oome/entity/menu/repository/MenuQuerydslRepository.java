package org.oome.entity.menu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.oome.entity.menu.Menu;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.oome.entity.menu.QMenu.menu;

@Repository
public class MenuQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory factory;

    public MenuQuerydslRepository(JPAQueryFactory factory) {
        super(Menu.class);
        this.factory = factory;
    }

    public List<Menu> findAllMenu() {
        return factory.selectFrom(menu)
                .fetch();
    }
}
