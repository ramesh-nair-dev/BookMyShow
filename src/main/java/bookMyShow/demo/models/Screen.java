package bookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseClass{
    private String screenName;
    @OneToMany
    private List<Seat> seatList;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> featureList;
    private int dimension;

}

/**
 * Screen Class Overview:
 * ----------------------
 * The Screen entity represents a cinema screen in the system and encapsulates
 * its core attributes and relationships.
 *
 * Fields:
 * 1. screenName:
 *    - Name of the screen (e.g., "IMAX Screen 1").
 *
 * 2. seatList:
 *    - List of Seat objects representing all seats in this screen.
 *    - Relationship: One Screen → Many Seats (OneToMany).
 *      - Each Seat belongs to exactly one Screen.
 *      - Example: Screen 1 has seats 26D, 26E, 26F; each seat belongs solely to Screen 1.
 *
 * 3. featureList:
 *    - List of supported features for this screen (e.g., 2D, 3D, 4K).
 *    - Relationship: Enum collection (not a full entity) stored in the DB.
 *    - Implementation:
 *        - @Enumerated(EnumType.STRING) → store enum values as strings for readability and maintainability.
 *        - @ElementCollection → indicates this is a collection of non-entity types.
 *    - EnumType Options:
 *        - STRING → stores enum as human-readable strings.
 *        - ORDINAL → stores enum as integer (less readable, slightly more efficient storage).
 *    - Database Mapping:
 *        - Creates a separate mapping table named `screen_feature_list`.
 *        - Columns: `screen_id` (foreign key to Screen) and `feature`.
 *
 * 4. dimension:
 *    - Represents the size or scale of the screen (could be width, height, or overall screen rating).
 *
 * Key Design Decisions:
 * - One-to-Many relationship between Screen and Seat ensures seat ownership is unique per screen.
 * - Using Enum + ElementCollection allows flexible storage of multiple screen features
 *   without creating a separate Feature entity.
 * - EnumType.STRING improves database readability and future maintainability.
 *
 * This design ensures scalability, readability, and maintainable DB schema,
 * while following best practices in JPA and Hibernate mapping.
 */
