package ru.sidorov.mcq.repository.specifications;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Question_;
import ru.sidorov.mcq.model.Training;


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
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Question_.CHECKED), true);
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

    public static Specification<Question> inExam(Exam examToCreate) {
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                Root<Question> question = root;
                Subquery<Exam> examSubQuery = query.subquery(Exam.class);
                Root<Exam> exam = examSubQuery.from(Exam.class);
                Expression<Collection<Question>> examQuestions = exam.get("questions");
                examSubQuery.select(exam);
                examSubQuery.where(criteriaBuilder.equal(exam, examToCreate), criteriaBuilder.isMember(question, examQuestions));
                return criteriaBuilder.exists(examSubQuery);
            }
        };
    }



}
