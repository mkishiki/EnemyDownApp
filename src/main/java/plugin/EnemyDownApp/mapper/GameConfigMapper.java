package plugin.EnemyDownApp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.PlayerScore;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;

import java.util.List;

@Mapper
public interface GameConfigMapper {
    @Select("select * from game_config")
    List<GameConfig> selectConfigList();

    @Select("select * from game_config where difficulty = #{difficulty} order by id asc")
    GameConfig selectConfig(String difficulty);

    @Select("select * from spawn_enemy inner join game_config on spawn_enemy.difficulty = game_config.difficulty where spawn_enemy.difficulty = #{difficulty} order by spawn_enemy.id asc")
    List<SpawnEnemy> selectSpawnEnemyList(String difficulty);

    @Insert("insert game_config(game_time, difficulty) values(#{gameTime}, #{difficulty})")
    int insertConfig(GameConfig config);

    //敵名、難易度が一致する列のスコアを登録
    @Update("update spawn_enemy set score = #{score} where enemy_name = #{enemyName} and difficulty = #{difficulty}")
    int updateEnemyScore(SpawnEnemy enemy);
}
