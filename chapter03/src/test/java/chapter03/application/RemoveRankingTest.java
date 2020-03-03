package chapter03.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void removeRanking() {
        service.addRanking("R1", "R2", "RS1", 8);
        assertEquals(service.getRankingFor("R1", "RS1"), 8);
        service.removeRanking("R1", "R2", "RS1");
        assertEquals(service.getRankingFor("R1", "RS1"), 0);
    }

    @Test
    public void removeNonexistentRanking() {
        service.removeRanking("R3", "R4", "RS2");
    }
}
