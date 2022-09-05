package ai.ecma.codingbat.repository;

import ai.ecma.codingbat.entity.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
   @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
//           value = "user_details_entity_graph",
           attributePaths = "addresses"
   )
   List<UserDetails> findByNameContaining(String text);
}