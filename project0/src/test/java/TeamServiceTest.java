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

import com.example.repository.DAO.TeamDAO;
import com.example.repository.entities.TeamEntity;
import com.example.service.TeamService;
import com.example.service.model.Team;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    
    @Mock
    private TeamDAO teamDAO;

    @InjectMocks
    private TeamService teamService;

    private TeamEntity teamEntity;

    @BeforeEach
    void setup(){
        teamEntity = new TeamEntity();
        teamEntity.setTeam_id(1);
        teamEntity.setCity("San Antonio");
        teamEntity.setMascot("Spurs");
    }

    // Happy Path
    @Test
    void getTeamByMascot_success() throws SQLException{
        when(teamDAO.findByMascot("Spurs")).thenReturn(Optional.of(teamEntity));

        Optional<Team> result = teamService.getModelByTeamMascot("Spurs");

        assertTrue(result.isPresent());
        assertEquals("Spurs", result.get().getMascot());
        assertEquals("San Antonio", result.get().getCity());

        verify(teamDAO, times(1)).findByMascot("Spurs");
    }

    // Negative Result
    @Test
    void getTeamByMascot_negative_invalidMascot() throws SQLException{
        when(teamDAO.findByMascot("Sonics")).thenReturn(Optional.empty());

        Optional<Team> result = teamService.getModelByTeamMascot("Sonics");

        assertTrue(result.isEmpty());

        verify(teamDAO, times(1)).findByMascot("Sonics");
    }

    // Edge Case
    @Test
    void getAllTeams_emptyList() throws SQLException{
        when(teamDAO.findAll()).thenReturn(Collections.emptyList());

        List<Team> result = teamService.getAllModels();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(teamDAO, times(1)).findAll();
    }
}
