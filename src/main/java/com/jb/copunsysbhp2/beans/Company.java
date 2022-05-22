package com.jb.copunsysbhp2.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    @Singular
    @JsonIgnore
    private List<Coupon> coupons=new ArrayList<>();
}
