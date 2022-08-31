package ai.ecma.codingbat.projection;

public interface LanguageDTOProjection {

    Integer getId();

    String getTitle();

    String getUrl();

    Integer getSectionCount();

    Integer getProblemCount();

    Long getTryCount();

    Long getSolutionCount();
}
