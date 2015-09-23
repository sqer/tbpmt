package pl.sqer.dao.type;

import javax.ejb.Stateless;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.type.TypeEntity;

/**
 * The TypeDAOImpl.
 */
@Stateless(name = "typeDao")
public class TypeDAOImpl extends BaseDAOImpl<TypeEntity> implements TypeDAO {

	public TypeDAOImpl() {
		super(TypeEntity.class);
	}

}
