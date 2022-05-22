package com.jb.copunsysbhp2.beans;
import javax.persistence.*;
import java.sql.Date;
import lombok.*;

@Entity
@Table (name ="coupons" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private int companyID;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @ManyToOne
    @ToString.Exclude
    private Company company;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

}
