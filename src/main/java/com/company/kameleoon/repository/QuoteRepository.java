package com.company.kameleoon.repository;

import com.company.kameleoon.entity.QuoteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
//@EnableJpaRepositories
public interface QuoteRepository extends JpaRepository<QuoteEntity, String> {

    @Query("from QuoteEntity  as quotes " +
            " inner join VotesEntity v on v.quoteId = quotes.id " +
            " where v.status = 'UPVOTE' " +
            " and quotes.id in " +
            " (select t.qId from (select vote.quoteId as qId, " +
            " count(*) as counts" +
            "  from VotesEntity vote" +
            " group by vote.quoteId ) t order by t.counts desc ) ")
    List<QuoteEntity> getTopQuotes(Pageable pageable);

    @Query(" from QuoteEntity  as quote " +
            " inner join VotesEntity v on v.quoteId = quote.id " +
            " where v.status = 'DOWNVOTE' " +
            " and quote.id in " +
            " (select t.qId from (select vote.quoteId as qId," +
            " count(vote.id) as counts" +
            "  from VotesEntity vote" +
            " group by vote.quoteId ) t order by t.counts desc ) " +
            " ")
    List<QuoteEntity> getBadQuotes(Pageable pageable);


}
