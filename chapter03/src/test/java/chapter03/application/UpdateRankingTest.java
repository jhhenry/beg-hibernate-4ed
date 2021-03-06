package chapter03.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UpdateRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void updateExistingRanking() {
        service.addRanking("Gene Showrama", "Scottball Most", "Ceylon", 6);
        assertEquals(service.getRankingFor("Gene Showrama", "Ceylon"), 6);
        service.updateRanking("Gene Showrama", "Scottball Most", "Ceylon", 7);
        assertEquals(service.getRankingFor("Gene Showrama", "Ceylon"), 7);
    }

    @Test
    public void updateNonexistentRanking() {
        assertEquals(service.getRankingFor("Scottball Most", "Ceylon"), 0);
        service.updateRanking("Scottball Most", "Gene Showrama", "Ceylon", 7);
        assertEquals(service.getRankingFor("Scottball Most", "Ceylon"), 7);
    }
}
