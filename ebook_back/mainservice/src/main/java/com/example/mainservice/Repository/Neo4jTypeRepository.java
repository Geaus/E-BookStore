package com.example.mainservice.Repository;

import com.example.mainservice.Entity.BookType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Neo4jTypeRepository extends Neo4jRepository<BookType, Long> {

    List<BookType> findBookTypesByTypeLike(String type);
    BookType findBookTypeByTypeIs(String type);

    @Query("MATCH (a:BookType)-[:relateTypes]->(b) " +
            "WHERE a.type = $name " +
            "RETURN b "
    )
    List<BookType> findTypes_1(@Param("name") String name);

    @Query("MATCH (a:BookType)-[:relateTypes]->(b)-[:relateTypes]->(c) " +
            "WHERE a.type = $name " +
            "RETURN c "
    )
    List<BookType> findTypes_2(@Param("name") String name);
}
