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

import com.example.repository.DAO.PlayerDAO;
import com.example.repository.entities.PlayerEntity;
import com.example.service.PlayerService;
import com.example.service.TeamService;
import com.example.service.model.Player;
import com.example.service.model.Team;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    
    @Mock
    private PlayerDAO playerDAO;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private PlayerService playerService;

    private PlayerEntity playerEntity;

    @BeforeEach
    void setup(){
        playerEntity = new PlayerEntity();
        playerEntity.setId(1);
        playerEntity.setTeamId(10);
        playerEntity.setFirstName("LeBron");
        playerEntity.setLastName("James");
        playerEntity.setPosition("SF");
        playerEntity.setCountry("USA");
        playerEntity.setJerseyNumber(23);
    }

    // Happy Path
    @Test
    void getPlayerByName_success() throws SQLException{
        when(playerDAO.findbyName("LeBron", "James")).thenReturn(Optional.of(playerEntity));

        Team team = new Team();
        team.setTeam_id(10);

        when(teamService.getModelById(10)).thenReturn(Optional.of(team));

        Optional<Player> result = playerService.getModelByName("LeBron", "James");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals(10, result.get().getTeam().getTeam_id());
        assertEquals("LeBron", result.get().getFirstName());
        assertEquals("James", result.get().getLastName());
        assertEquals("SF", result.get().getPosition());
        assertEquals("USA", result.get().getCountry());
        assertEquals(23, result.get().getJerseyNumber());

        verify(playerDAO, times(1)).findbyName("LeBron", "James");
    }

    // Negative Result
    @Test
    void getPlayerById_negative_playerNotFound() throws SQLException{
        when(playerDAO.findById(2)).thenReturn(Optional.empty());

        Optional<Player> result = playerService.getModelById(2);

        assertTrue(result.isEmpty());

        verify(playerDAO, times(1)).findById(2);
    }

    // Edge Case
    @Test
    void getAllPlayersOnTeam_emptyList() throws SQLException{
        when(playerDAO.findAllByTeamId(10)).thenReturn(Collections.emptyList());

        List<Player> result = playerService.getAllModelsByTeamId(10);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(playerDAO, times(1)).findAllByTeamId(10);
    }
}
