package pl.sqer.service.comment;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import pl.sqer.dao.comment.CommentDAO;
import pl.sqer.dao.entity.comment.CommentEntity;
import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dao.entity.user.UserEntity;
import pl.sqer.dto.comment.CommentDto;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class CommentServiceImpl.
 */
@Stateless(name = "commentService")
public class CommentServiceImpl implements CommentService {

	/** The comment dao. */
	@EJB(name = "commentDao")
	private CommentDAO commentDAO;

	/** The dozer. */
	@EJB(name = "dozer")
	private Dozer dozer;

	@Override
	public CommentDto addComment(final String comment, final Integer taskId,
			final Integer userId) {

		if (!StringUtils.isEmpty(comment) && taskId != null) {
			final CommentEntity entity = new CommentEntity();
			entity.setContent(comment);
			entity.setTask(new TaskEntity(taskId));
			entity.setCreator(new UserEntity(userId));
			entity.setCreationDate(new Date());
			commentDAO.persist(entity);
			return dozer.map(entity, CommentDto.class);
		} else {
			return null;
		}
	}

	@Override
	public void deleteComment(final Integer id) {
		if (id != null) {
			commentDAO.delete(id);
		}
	}
}
