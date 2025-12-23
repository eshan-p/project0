import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.repository.DAO.BoxScoreDAO;
import com.example.repository.entities.BoxScoreEntity;
import com.example.service.BoxScoreService;
import com.example.service.PlayerService;
import com.example.service.model.BoxScore;
import com.example.service.model.Game;
import com.example.service.model.Player;

@ExtendWith(MockitoExtension.class)
public class BoxScoreServiceTest {
    
    @Mock
    private BoxScoreDAO boxScoreDAO;

    @Mock
    private PlayerService playerService;
 
    private Random random;
    private BoxScoreService boxScoreService;
    private BoxScoreEntity boxScoreEntity;
    private Player player;
    private Game game;

    @BeforeEach
    void setup(){
        random = new Random(1);
        boxScoreService = new BoxScoreService(boxScoreDAO, playerService, random);

        player = new Player();
        player.setId(10);
        player.setFirstName("LeBron");
        player.setLastName("James");

        game = new Game();
        game.setId(101);

        boxScoreEntity = new BoxScoreEntity();
        boxScoreEntity.setId(1);
        boxScoreEntity.setGameId(101);
        boxScoreEntity.setPlayerId(10);
        boxScoreEntity.setMinutesPlayed(36);
        boxScoreEntity.setPoints(30);
        boxScoreEntity.setRebounds(5);
        boxScoreEntity.setAssists(7);
        boxScoreEntity.setSteals(2);
        boxScoreEntity.setBlocks(0);
        boxScoreEntity.setTurnovers(3);
        boxScoreEntity.setFgMade(10);
        boxScoreEntity.setFgAttempted(18);
        boxScoreEntity.setThreesMade(6);
        boxScoreEntity.setThreesAttempted(11);
        boxScoreEntity.setFtMade(4);
        boxScoreEntity.setFtAttempted(4);
    }

    // TDD
    @Test
    void generateBoxScore_validBoxScore(){
        BoxScore boxScore = boxScoreService.generateBoxScore(player);

        assertNotNull(boxScore);
        assertEquals(player, boxScore.getPlayer());
        assertTrue(boxScore.getMinutesPlayed() >= 0);
        assertTrue(boxScore.getMinutesPlayed() <= 48);
    }

    // Happy Case
    @Test
    void convertEntityToModel_success(){
        when(playerService.getModelById(10)).thenReturn(Optional.of(player));
        
        Optional<BoxScore> result = boxScoreService.convertEntityToModel(boxScoreEntity, game);

        assertTrue(result.isPresent());
        assertEquals(36, result.get().getMinutesPlayed());
        assertEquals(30, result.get().getPoints());
        assertEquals(player, result.get().getPlayer());
        assertEquals(game, result.get().getGame());

        verify(playerService, times(1)).getModelById(10);
    }

    // Negative Result


    // Edge Case
}
