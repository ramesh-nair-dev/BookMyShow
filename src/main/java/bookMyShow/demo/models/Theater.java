package bookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater extends BaseClass{
    private String theatreName;
    private String address;
    @ManyToMany
    private List<Movie> movieList;
    @ManyToOne
    private Region region;

}

/**
 *
 * In the Theater class, we have the following fields:
 *
 * - theatreName: The name of the theater.
 * - address: The address of the theater.
 * - movieList: A list of Movie objects representing the movies available in the theater.
 * - screenList: A list of Screen objects representing the screens in the theater this is optional , because in screen class
 *               we have theater field which is mapped to the theater class. we can pull it from there as well. it while desing
 *               we note the an theater object i need the data of screen it has it that case we can store it in the theater class as well.
 *               otherwise we query it from our database.
 *  - region: A Region object representing the region in which the theater is located.
 *  * This class is used to represent a theater in the system, allowing us to manage and organize theaters along with their associated movies and screens.
 *
 * The @Entity annotation indicates that this class is an entity and will be mapped to a database table.
 * 1. All primitive fields will be mapped to the columns of the table, and for non-primitive fields like List,
 *    we need to use appropriate annotations to map the relationship.
 * 2. Relation between Theater and Region 1 Theater can belong to only 1 region , but 1 region can have multiple theaters.
 *    so this ManyToOne relationship is mapped using the Region class.
 * 3. Relation between Theater and Movie is 1 Theatre can Multiple Movie , but 1 Movie can belong to Multiple Theaters.
 *    so this ManyToMany relationship is mapped using the Movie class.
 *
 *
 *
 */






