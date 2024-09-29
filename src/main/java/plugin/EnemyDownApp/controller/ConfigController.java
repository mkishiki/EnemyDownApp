package plugin.EnemyDownApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;
import plugin.EnemyDownApp.service.ConfigService;
import plugin.EnemyDownApp.service.DuplicateConfigException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class ConfigController {

    private final ConfigService service;

    public ConfigController(ConfigService service) {
        this.service = service;
    }

    @GetMapping(value = "/configList")
    public List<GameConfig> searchConfigList() {
        return service.searchConfigList();
    }

    @GetMapping(value = "/config")
    public GameConfig searchConfig(@RequestParam String difficulty) {
        return service.searchConfig(difficulty);
    }

    @GetMapping(value = "/spawnEnemyList")
    public List<SpawnEnemy> spawnEnemyList(@RequestParam String difficulty) {
        return service.selectSpawnEnemyList(difficulty);
    }

    @PostMapping(value = "/config")
    public ResponseEntity<GameConfig> registerConfig(@RequestBody GameConfig config) throws Exception {
        GameConfig registerConfig = service.registerConfig(config);
        return new ResponseEntity<>(registerConfig, HttpStatus.OK);
    }

    @PostMapping(value = "/updateEnemyScore")
    public ResponseEntity<List<SpawnEnemy>> updateEnemyScore(@RequestBody SpawnEnemy enemy) {
        List<SpawnEnemy> updatedSpawnEnemyList = service.updateEnemyScore(enemy);
        return new ResponseEntity<>(updatedSpawnEnemyList, HttpStatus.OK);
    }

    @ExceptionHandler(value = DuplicateConfigException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateConfig(
            DuplicateConfigException e, HttpServletRequest request) { //例外の情報e（今回はエラー文言）と、呼び出しもとからのリクエストを引数
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
