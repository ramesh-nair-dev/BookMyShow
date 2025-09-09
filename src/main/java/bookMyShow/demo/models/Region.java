package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter

@Entity
public class Region extends BaseClass{
    private String name;
    @OneToMany
    private List<Theater> theaterList;
}

/**
 * =========================
 *  Region Entity Overview
 * =========================
 *
 * The `Region` class represents a **geographical grouping of theaters**,
 * enabling structured management and logical categorization of theaters
 * across different areas (e.g., cities, districts, or zones).
 *
 * -------------------------
 *  Field Descriptions
 * -------------------------
 * 1. `name`:
 *    - A human-readable identifier for the region (e.g., "Bangalore North").
 *
 * 2. `theaterList`:
 *    - A collection of `Theater` entities associated with this region.
 *    - Defines a **One-to-Many relationship**:
 *      • One Region ⟶ Multiple Theaters
 *      • Each Theater ⟶ Belongs to exactly one Region
 *
 * -------------------------
 *  Database Mapping
 * -------------------------
 * • `@Entity`: Marks `Region` as a JPA entity, ensuring a corresponding table
 *   is created in the database.
 * • Primitive fields (like `name`) map directly to table columns.
 * • Collections (like `theaterList`) require explicit relationship annotations
 *   (`@OneToMany`) to define how they are persisted.
 *
 * -------------------------
 *  Design Considerations
 * -------------------------
 * • This model enforces **strong data consistency** by ensuring a Theater
 *   cannot exist without belonging to a Region.
 * • Improves scalability for queries:
 *   - Example: "Fetch all theaters in Region X" becomes highly efficient.
 * • Provides extensibility for features like:
 *   - Region-based search
 *   - Regional pricing policies
 *   - Location-aware recommendations
 *
 * -------------------------
 *  Cardinality
 * -------------------------
 * Region ⟶ Theater:
 * - One Region: multiple Theaters
 * - One Theater: exactly one Region
 *
 */
