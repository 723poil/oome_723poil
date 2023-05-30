package org.oome.entity.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import org.oome.entity.question.answer.Answer;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Question> {

    private static final long serialVersionUID = 1541737215L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final org.oome.entity.common.QBaseTimeEntity _super = new org.oome.entity.common.QBaseTimeEntity(this);

    public final ListPath<Answer, org.oome.entity.question.answer.QArticleComment> comments = this.<Answer, org.oome.entity.question.answer.QArticleComment>createList("comments", Answer.class, org.oome.entity.question.answer.QArticleComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final org.oome.entity.member.QMember creater;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath title = createString("title");

    public QArticle(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QArticle(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creater = inits.isInitialized("creater") ? new org.oome.entity.member.QMember(forProperty("creater")) : null;
    }

}

