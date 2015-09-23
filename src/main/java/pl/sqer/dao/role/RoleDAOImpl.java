package pl.sqer.dao.role;

import javax.ejb.Stateless;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.role.RoleEntity;

/**
 * The RoleDAOImpl.
 */
@Stateless(name = "roleDao")
public class RoleDAOImpl extends BaseDAOImpl<RoleEntity> implements RoleDAO {

	public RoleDAOImpl() {
		super(RoleEntity.class);
	}

}
