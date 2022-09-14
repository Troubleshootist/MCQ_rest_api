package ru.sidorov.mcq.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.sidorov.mcq.model.*;
import ru.sidorov.mcq.model.Question_;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;


public class QuestionSpecifications {
    public static Specification<Question> enabled() {
        return new Specification<Question>() {

            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Question_.ENABLED), true );
            }
        };
    }
    public static Specification<Question> checked() {
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Question_.CHECKED), true);
            }
        };
    }
    public static Specification<Question> byTraining(Training training) {
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Question_.TRAINING), training);
            }
        };
    }

    public static Specification<Question> inAtaChapters(List<AtaChapter> ataChapters) {
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                final Path<AtaChapter> ataChapter = root.get("ataChapter");
                return ataChapter.in(ataChapters);
            }
        };
    }

    public static Specification<Question> inExam(long examID) {
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Root<Question> question = root;
                Subquery<Exam> examSubQuery = query.subquery(Exam.class);
                Root<Exam> exam = examSubQuery.from(Exam.class);
                Expression<Collection<Question>> examQuestions = exam.get("questions");
                examSubQuery.select(exam);
                examSubQuery.where(criteriaBuilder.equal(exam.get("id"), examID), criteriaBuilder.isMember(question, examQuestions));
                return criteriaBuilder.exists(examSubQuery);
            }
        };
    }
}
