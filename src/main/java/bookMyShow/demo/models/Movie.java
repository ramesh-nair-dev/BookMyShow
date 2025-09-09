package bookMyShow.demo.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseClass{
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private double duration;
    @ElementCollection
    private List<String> cast;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Language> languageList;
}

/**
 * Movie class represents a movie in a theater system.
 *
 * Attributes:
 *
 * 1. `title` — The title of the movie.
 *
 * 2. `genre` — The genre of the movie (e.g., Action, Comedy, Drama, etc.).
 *
 * 3. `releaseYear` — The release year of the movie.
 *
 * 4. `director` — The director of the movie.
 *
 * 5. `duration` — The duration of the movie in minutes.
 *
 * 6. `cast` — A list of actors/actresses who played in the movie.
 *
 * 7. `languageList` — A list of languages in which the movie is available.
 *
 *
 * Cardinality and Mapping Details:
 *
 * 1. Movie ↔ Cast
 *    - One Movie can have multiple cast members.
 *    - One cast member can also act in multiple movies.
 *    - This is conceptually a ManyToMany relationship.
 *    - However, since we don’t create a separate `Cast` entity, we simply use `@ElementCollection` to store cast names directly.
 *    - This generates a separate table in the DB named **movie_cast**, with columns `movie_id` and `cast`.
 *
 * 2. Movie ↔ LanguageList
 *    - One Movie can be available in multiple languages.
 *    - One Language can be linked with multiple movies.
 *    - This again is a ManyToMany relationship, but represented via `@ElementCollection` instead of a full entity.
 *
 */
