package plugin.EnemyDownApp.service;

import org.springframework.stereotype.Service;
import plugin.EnemyDownApp.mapper.GameConfigMapper;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;

import java.util.List;

@Service
public class ConfigService {

    public final GameConfigMapper mapper;

    public ConfigService(GameConfigMapper mapper) {
        this.mapper = mapper;
    }

    public List<GameConfig> searchConfigList() {    //DBから設定情報を探してリスト表示
        return mapper.selectConfigList();
    }

    public GameConfig searchConfig(String difficulty) {    //リクエストパラメーターで指定した難易度の情報を表示
        return mapper.selectConfig(difficulty);
    }

    public List<SpawnEnemy> selectSpawnEnemyList(String difficulty) {
        return mapper.selectSpawnEnemyList(difficulty);
    }

    public GameConfig registerConfig(GameConfig config) throws Exception {    //ゲーム設定の登録
        GameConfig existsConfig = searchConfig(config.getDifficulty());
        if (existsConfig != null){  //例外処理
            throw new DuplicateConfigException("Duplicate Config Error!");
        }
        mapper.insertConfig(config);
        return mapper.selectConfig(config.getDifficulty());
    }

    public List<SpawnEnemy> updateEnemyScore(SpawnEnemy enemy) {    //敵リストの上書き
        mapper.updateEnemyScore(enemy);
        return mapper.selectSpawnEnemyList(enemy.getDifficulty());
    }


}
