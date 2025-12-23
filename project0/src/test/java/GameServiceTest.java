import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.repository.DAO.GameDAO;
import com.example.repository.entities.GameEntity;
import com.example.service.GameService;
import com.example.service.TeamService;
import com.example.service.model.Game;
import com.example.service.model.Team;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    
    @Mock
    private GameDAO gameDAO;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private GameService gameService;

    private GameEntity gameEntity;

    @BeforeEach
    void setup(){
        gameEntity = new GameEntity();
        gameEntity.setId(1);
        gameEntity.setHomeTeamId(10);
        gameEntity.setAwayTeamId(20);
        gameEntity.setHomeTeamScore(102);
        gameEntity.setAwayTeamScore(98);
        gameEntity.setOvertime(false);
    }

    // Happy Path
    @Test
    void getGameById_success() throws SQLException{
        when(gameDAO.findById(1)).thenReturn(Optional.of(gameEntity));

        Team homeTeam = new Team();
        homeTeam.setTeam_id(10);

        Team awayTeam = new Team();
        awayTeam.setTeam_id(20);

        when(teamService.getModelById(10)).thenReturn(Optional.of(homeTeam));
        when(teamService.getModelById(20)).thenReturn(Optional.of(awayTeam));

        Optional<Game> result = gameService.getModelById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(10, result.get().getHomeTeam().getTeam_id());
        assertEquals(20, result.get().getAwayTeam().getTeam_id());
        assertEquals(102, result.get().getHomeTeamScore());
        assertEquals(98, result.get().getAwayTeamScore());
        assertEquals(false, result.get().isOvertime());

        verify(gameDAO, times(1)).findById(1);
    }

    // Negative Result
    @Test
    void getGameById_negative_gameNotFound() throws SQLException{
        when(gameDAO.findById(14)).thenReturn(Optional.empty());

        Optional<Game> result = gameService.getModelById(14);

        assertTrue(result.isEmpty());

        verify(gameDAO, times(1)).findById(14);
    }

    // Edge Case
    @Test
    void getAllGamesForTeam_emptyList() throws SQLException{
        when(gameDAO.findAllByTeamId(10)).thenReturn(Collections.emptyList());

        List<Game> result = gameService.getAllModelsByTeamId(10);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(gameDAO, times(1)).findAllByTeamId(10);
    }
}
