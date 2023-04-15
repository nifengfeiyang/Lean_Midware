package com.szm.neo4j.springneo4j.repository;

import com.szm.neo4j.springneo4j.entity.RelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip,Long> {

}
