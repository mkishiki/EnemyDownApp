package plugin.EnemyDownApp.service;

import org.springframework.stereotype.Service;
import plugin.EnemyDownApp.mapper.GameConfigMapper;
import plugin.EnemyDownApp.mapper.PlayerScoreMapper;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.PlayerScore;

import java.util.List;

@Service
public class PlayerScoreService {

    public final PlayerScoreMapper mapper;

    public PlayerScoreService(PlayerScoreMapper mapper) {
        this.mapper = mapper;
    }

    public List<PlayerScore> searchPlayerScoreList() {    //DBから設定情報を探してリスト表示
        return mapper.selectPlayerScoreList();
    }
}
