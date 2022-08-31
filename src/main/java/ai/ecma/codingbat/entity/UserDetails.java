package ai.ecma.codingbat.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created on 09/August/2021 By Author Eresh, Gorantla
 **/
@Entity
@Table(name = "user_details")
@Getter
@Setter
//@NamedEntityGraph(name = "user_details_entity_graph", attributeNodes = @NamedAttributeNode("addresses"))
public class UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails")
    private List<Address> addresses;


}