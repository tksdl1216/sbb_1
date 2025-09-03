package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;


@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;


    @Test
    void QuestionInsertTest() {
        Question question = new Question();
        question.setSubject("sbb가 무엇인가요?");
        question.setContent("sbb에 대해 알고싶어요");
        question.setCreatedDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question);

        Question question2 = new Question();
        question2.setSubject("스프링부트 모델 질문입니다.");
        question2.setContent("id는 자동 생성 되나요?");
        question2.setCreatedDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question2);
    }

    @Test
    void questionFindAllTest() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(4, all.size());

        Question q = all.getFirst();
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void questionFindByIdTest() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }
    @Test
    void testJpa() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해 알고싶어요");
        assertEquals(1, q.getId());


    }
}
