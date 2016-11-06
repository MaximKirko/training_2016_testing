package com.github.maximkirko.testing.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.util.GenericTypeInfo;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz> implements IQuizDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public QuizDaoImpl() {
		super(Quiz.class);
	}
	
	@Override
	public Long insert(AbstractModel entity) {

		final String INSERT_SQL = String.format("INSERT INTO %s (%s, subject_id) VALUES (%s, %s)", super.tableName,
				GenericTypeInfo.getFields(Quiz.class, entity), GenericTypeInfo.getFieldsValues(Quiz.class, entity),
				((Quiz) entity).getSubject().getId());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				System.out.println(ps.toString());
				return ps;
			}
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

		return entity.getId();
	}
}