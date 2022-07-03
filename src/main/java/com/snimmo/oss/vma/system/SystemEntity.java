package com.snimmo.oss.vma.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "System")
@Table(name = "system")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_id")
    private Integer systemId;

    @Column(name = "name")
    @NotEmpty
    private String name;

}
