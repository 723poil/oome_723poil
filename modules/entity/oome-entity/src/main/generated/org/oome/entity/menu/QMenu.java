package org.oome.entity.menu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1037270159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final org.oome.entity.common.QBaseTimeEntity _super = new org.oome.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath menuCode = createString("menuCode");

    public final StringPath menuLink = createString("menuLink");

    public final QMenu parent;

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QMenu(forProperty("parent"), inits.get("parent")) : null;
    }

}

