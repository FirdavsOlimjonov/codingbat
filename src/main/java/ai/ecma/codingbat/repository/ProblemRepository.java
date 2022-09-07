package ai.ecma.codingbat.repository;

import ai.ecma.codingbat.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {
    Optional<List<Problem>> getAllBySection_Id(Integer sectionId);

    void  deleteAllBySection_Id(Integer sectionId);

    int countAllBySection_Language_Id(Integer id);

    boolean existsByTitleAndSectionId(String title, Integer section);

    int countAllBySectionId(Integer id);

//    Object countAllBySection_Section_Id(Integer id);
}
