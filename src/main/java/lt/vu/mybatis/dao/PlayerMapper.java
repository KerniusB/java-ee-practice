package lt.vu.mybatis.dao;

import java.util.List;


import lt.vu.mybatis.model.Player;
import org.mybatis.cdi.Mapper;


@Mapper
public interface PlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Player record);

    Player selectByPrimaryKey(Integer id);

    List<Player> selectAll();

    int updateByPrimaryKey(Player record);
}