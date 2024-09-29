package plugin.EnemyDownApp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.PlayerScore;

import java.util.List;

@Mapper
public interface PlayerScoreMapper {
    @Select("select * from player_score order by id asc")
    List<PlayerScore> selectPlayerScoreList();
}