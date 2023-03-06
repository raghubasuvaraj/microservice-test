package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;



@Data

@Entity
@Table(name = "mse_master_academic_years")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MasterAcademicYearEntity extends BaseEntity implements Serializable {

//public class MasterAcademicYearEntity implements Serializable {
   // @Serial
    //private static final long serialVersionUID = 5937596444645768818L;

   // @Id
   // @GeneratedValue(generator = "uuid")
   // @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
   // @Column(length = 50)
   // protected String id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_year")
    private String startYear;

    @Column(name = "end_year")
    private String endYear;

}
