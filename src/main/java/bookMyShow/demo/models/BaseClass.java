package bookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}


/**
 * This is a base class that will be extended by other classes.
 * It contains common fields like id, created_at, and updated_at.
 * The @MappedSuperclass annotation indicates that this class is not an entity itself,
 * but its fields will be mapped to the database table of the subclasses.
 * The @Id annotation indicates that the id field is the primary key of the entity
 */
