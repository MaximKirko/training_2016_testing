package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Inject
	private IQuestionDao questionDao;

	@Inject
	private IAnswerService answerService;

	@Inject
	private IQuizToQuestionService quizToQuestionService;

	@Override
	public Question get(Long id) {
		return questionDao.get(id);
	}

	@Override
	public Question getWithAnswers(Long id) {

		Question question = questionDao.getWithAnswers(id);
		return question;
	}

	@Override
	public List<Question> getAll() {
		return questionDao.getAll();
	}

	@Override
	public Long save(Question question) {

		if (question == null) {
			return null;
		}

		if (question.getId() == null) {
			Long id = questionDao.insert(question);
			question.setId(id);
		} else {
			questionDao.update(question);
		}

		List<Answer> answers = question.getAnswers();
		if (answers != null) {
			for (Answer answer : answers) {
				Long id = answerService.save(answer);
				answer.setId(id);
			}
			question.setAnswers(answers);
		}

		return question.getId();
	}

	@Override
	public List<Long> saveAll(List<Question> questions) {

		List<Long> idList = new ArrayList<Long>();

		for (Question question : questions) {
			Long id = save(question);
			question.setId(id);
			idList.add(id);
		}
		return idList;
	}

	@Override
	public void delete(Long id) {

		Question question = getWithAnswers(id);
		if (question == null) {
			return;
		}
		List<Answer> answers = answerService.getByQuestionId(id);

		if (answers != null) {
			for (Answer a : answers) {
				a.setQuestion(null);
				answerService.delete(a.getId());
			}
		}
		quizToQuestionService.deleteByQuestionId(id);
		questionDao.delete(id);
	}

}
