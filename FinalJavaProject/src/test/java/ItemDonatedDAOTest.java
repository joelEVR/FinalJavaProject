import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated;
import main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonatedDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ItemDonatedDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    private ItemDonatedDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        dao = new ItemDonatedDAO(connection);
    }

    @Test
    void addItemDonatedReturnsTrueOnSuccess() throws SQLException {
        ItemDonated itemDonated = new ItemDonated();
        itemDonated.setUserId(1);
        itemDonated.setTitle("Food");
        itemDonated.setDescription("Fresh Food");
        itemDonated.setQuantity(10);
        itemDonated.setPickupLocation("Location");
        itemDonated.setExpirationDate("2024-05-01");
        itemDonated.setContactMethod("Email");
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = dao.addItemDonated(itemDonated);

        verify(preparedStatement, times(1)).executeUpdate();
        assertTrue(result, "The addItemDonated method should return true on success.");
    }

    @Test
    void addItemDonatedReturnsFalseOnSQLException() throws SQLException {
        ItemDonated itemDonated = new ItemDonated();
        itemDonated.setUserId(1);

        when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);

        boolean result = dao.addItemDonated(itemDonated);

        assertFalse(result, "The addItemDonated method should return false when SQLException is thrown.");
    }
}
